package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.JedisClient;
import com.running.business.mapper.RunUserMapper;
import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserExample;
import com.running.business.pojo.RunUserExample.Criteria;
import com.running.business.service.RunUserService;
import com.running.business.util.CookieUtils;
import com.running.business.util.JsonUtils;
import com.running.business.util.Run_StringUtil;
import com.running.business.util.ValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * show 用户业务逻辑类
 * <p>Description: asdfasdf </p>
 *
 * @author 刘明宇
 * @version 1.0.0
 */
@Service
public class RunUserServiceImpl implements RunUserService {

    /**
     * 用户Mapper
     */
    @Autowired
    private RunUserMapper runUserMapper;
    /**
     * 连接redis
     */
    @Autowired
    private JedisClient jedisClient;
    /**
     * 用户token键
     */
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    /**
     * session生命周期
     */
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    /**
     * 检查用户是否存在
     *
     * @param username 用户账号
     * @return <p>返回状态码"200"，存在：返回状态码"-1"，并返回错误码1001001，错误信息"用户已注册"</p>
     */
    @Override
    public BaseResult checkUser(String username) throws AppException {
        if (username == null || username.trim().equals("")) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunUserExample example = new RunUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserphoneEqualTo(username);
        List<RunUser> list = runUserMapper.selectByExample(example);
        if (ValidateUtil.isValid(list)) {
            return BaseResult.fail(ResultEnum.TELTPHONE_USED);
        }
        return BaseResult.success();
    }

    /**
     * 检查账号和密码是否匹配，匹配返回
     *
     * @param username
     * @param password
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkPwd(String username, String password) throws AppException {
        if (username == null || password == null || username.equals("") || password.equals("")) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunUserExample example = new RunUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andPasswordEqualTo(Run_StringUtil.MD5(password));
        criteria.andUserphoneEqualTo(username);
        List<RunUser> list = runUserMapper.selectByExample(example);
        if (ValidateUtil.isValid(list)) {
            return true;
        }
        return false;
    }

    /**
     * 添加用户,进行MD5加密
     *
     * @param user 用户实体
     * @return <p>添加成功返回状态码"200"</p>
     */
    @Override
    public BaseResult addUser(RunUser user) throws AppException {
        if (user == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        user.setPassword(Run_StringUtil.MD5(user.getPassword()));
        user.setAddTime(new Date());
        user.setUpdateTime(new Date());
        user.setUserStatus(false);
        user.setIsDelete(false);
        int uid = runUserMapper.insert(user);
        return BaseResult.success(uid);
    }

    /**
     * 删除用户
     *
     * @param uid 用户id
     * @return 删除成功返回状态码"200"
     */
    @Override
    public BaseResult delUser(Integer uid) throws AppException {
        RunUser user = runUserMapper.selectByPrimaryKey(uid);
        if (user == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        user.setIsDelete(true);
        user.setUpdateTime(new Date());
        runUserMapper.updateByPrimaryKeySelective(user);
        return BaseResult.success(user);
    }

    /**
     * 修改用户密码,将用户密码进行md5加密
     *
     * @param user 用户实体
     * @return 修改成功返回状态码"200"
     */
    @Override
    public BaseResult updateUser(RunUser user) throws AppException {
        if (user == null || user.getPassword() == null || user.getPassword().equals("")) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        user.setPassword(Run_StringUtil.MD5(user.getPassword()));
        user.setUpdateTime(new Date());
        runUserMapper.updateByPrimaryKeySelective(user);
        return BaseResult.success();
    }

    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return 如果没找到该用户，则返回状态码"-1"，错误码1001004，错误信息"错误的查询",成功后返回状态码"200",并返回该用户实体
     */
    @Override
    public BaseResult getRunUser(Integer id) throws AppException {
        RunUser user = runUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
        }
        return BaseResult.success(user);
    }

    /**
     * 用户登录，生成token，保存用户之前，将用户对象中的密码清空，把用户信息写入redis，设置sesison的生命周期
     * ，添加token到cookie, 名字为："RUN_TOKEN"
     *
     * @param username 用户账号 password 密码 request 请求 response 响应
     * @return 先验证账号是否存在，如果不存在返回错误码1001002，错误信息"账号或密码错误"
     * 如果存在验证账号和密码是否匹配，如果不匹配返回错误码1001003，错误信息"密码错误"
     * 如果匹配则返回状态码"0",并返回token
     */
    @Override
    public BaseResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws AppException {
        RunUserExample example = new RunUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserphoneEqualTo(username);
        List<RunUser> list = null;
        list = runUserMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.TELTPHONE_NOT_REG.getCode(), ResultEnum.TELTPHONE_NOT_REG.getMsg());
        }
        RunUserExample example1 = new RunUserExample();
        Criteria criteria1 = example1.createCriteria();
        criteria1.andUserphoneEqualTo(username);
        criteria1.andPasswordEqualTo(Run_StringUtil.MD5(password));
        list = runUserMapper.selectByExample(example1);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.PWD_ERROR.getCode(), ResultEnum.PWD_ERROR.getMsg());
        }
        RunUser user = list.get(0);
        //生成token
        String token = "RU" + UUID.randomUUID().toString();
        //保存用户之前，将用户对象中的密码清空
        user.setPassword(null);
        //把用户信息写入redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        //设置sesison的生命周期
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        //添加cookie
        CookieUtils.setCookie(request, response, "RUN_TOKEN", token);
        //返回token
        return BaseResult.success(token);
    }

    /**
     * 获取所有用户信息
     *
     * @return 如果没有数据，返回错误码"1",错误码1001008，错误信息"我也是有底线的"
     * 如果成功返回所有符合条件的信息
     */
    @Override
    public BaseResult getAllRunUser() throws AppException {
        RunUserExample example = null;
        List<RunUser> list = null;
        example = new RunUserExample();
        example.setOrderByClause("user_date DESC");
        Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(false);
        list = runUserMapper.selectByExample(example);
        /*if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }*/
        return BaseResult.success(list);
    }

    /**
     * 根据token从redis中查询用户信息
     *
     * @param token token码
     * @return 如何为空则返回会话超时，否则更新生命周期，将用户返回
     */
    @Override
    public BaseResult getUserByToken(String token) throws AppException {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);

        //判断是否为空
        if (StringUtils.isBlank(json)) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME.getCode(), ResultEnum.SESSION_IS_OUT_TIME.getMsg());
        }
        //更新生命周期
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        return BaseResult.success(JsonUtils.jsonToPojo(json, RunUser.class));
    }

    /**
     * 注销用户，根据token从redis中查询用户信息
     *
     * @param token token码
     * @return 如果token为空，说明会话超时，否则将该token删除，返回用户实体
     */
    @Override
    public BaseResult logout(String token) throws AppException {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        //判断是否为空
        if (StringUtils.isBlank(json)) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME.getCode(), ResultEnum.SESSION_IS_OUT_TIME.getMsg());
        }
        //更新生命周期
        jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
        return BaseResult.success(JsonUtils.jsonToPojo(json, RunUser.class));
    }
}
