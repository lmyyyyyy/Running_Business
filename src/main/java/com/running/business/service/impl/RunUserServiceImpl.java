package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.Config;
import com.running.business.common.ResultEnum;
import com.running.business.dto.UserDTO;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.JedisClient;
import com.running.business.mapper.RunUserMapper;
import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserExample;
import com.running.business.pojo.RunUserExample.Criteria;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.HeartBeatService;
import com.running.business.service.RunUserInfoService;
import com.running.business.service.RunUserService;
import com.running.business.service.SSOService;
import com.running.business.util.CookieUtils;
import com.running.business.util.JsonUtils;
import com.running.business.util.Run_StringUtil;
import com.running.business.util.UserUtil;
import com.running.business.util.ValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 用户业务逻辑类
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
    @Autowired
    private SSOService ssoService;

    @Autowired
    private RunUserInfoService runUserInfoService;

    @Autowired
    private HeartBeatService heartBeatService;
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
    public BaseResult saveUser(RunUser user) throws AppException {
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
    public BaseResult deleteUser(Integer uid) throws AppException {
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
     * 根据用户id集合更新用户登录状态
     *
     * @param userIds
     * @throws AppException
     */
    @Override
    public void updateUserListStatus(Set<Integer> userIds) throws AppException {
        if (userIds == null || userIds.size() == 0) {
            return;
        }
        for (Integer userId : userIds) {
            RunUser user = new RunUser();
            user.setUid(userId);
            user.setUpdateTime(new Date());
            user.setUserStatus(false);
            runUserMapper.updateByPrimaryKeySelective(user);
        }
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
        criteria.andIsDeleteEqualTo(false);
        List<RunUser> list;
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
        if (user == null) {
            throw new AppException(ResultEnum.USER_INFO_ISEMPTY);
        }
        user.setUserStatus(true);
        runUserMapper.updateByPrimaryKeySelective(user);
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
        RunUserInfo runuserInfo = runUserInfoService.getRunUserInfoById(user.getUid());
        if (runuserInfo == null) {
            throw new AppException(ResultEnum.USER_INFO_ISEMPTY);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUid());
        userDTO.setName(runuserInfo.getUserName());
        userDTO.setPhone(user.getUserphone());
        userDTO.setStatus(user.getUserStatus());
        userDTO.setUserType(UserTypeEnum.USER.getCode());
        UserUtil.bind(userDTO);
        //返回token
        return BaseResult.success(token);
    }

    /**
     * 分页获取所有未被删除的用户列表
     *
     * @return 如果没有数据，返回错误码"1",错误码1001008，错误信息"我也是有底线的"
     * 如果成功返回所有符合条件的信息
     */
    @Override
    public PageInfo<RunUser> pageAllRunUser(Integer page, Integer size, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        RunUserExample example = new RunUserExample();
        List<RunUser> list;
        example.setOrderByClause("update_time " + orderType);
        Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(false);
        PageHelper.startPage(page, size);
        list = runUserMapper.selectByExample(example);
        PageInfo<RunUser> pageInfo = new PageInfo<>(list);

        return pageInfo;
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
        RunUser user = JsonUtils.jsonToPojo(json, RunUser.class);
        if (user == null) {
            throw new AppException(ResultEnum.USER_INFO_ISEMPTY);
        }
        //登录人员列表中去除当前正常退出人员
        jedisClient.srem(Config.LOGIN_USER_IDS_KEY, String.valueOf(user.getUid()));
        user.setUserStatus(false);
        runUserMapper.updateByPrimaryKeySelective(user);
        UserUtil.unbindUser();
        return BaseResult.success(user);
    }

    /**
     * 根据用户状态获取所有未被删除的用户
     *
     * @param status
     * @return
     * @throws AppException
     */
    @Override
    public List<RunUser> getUsersByStatus(boolean status) throws AppException {
        RunUserExample example = new RunUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(false);
        criteria.andUserStatusEqualTo(status);
        example.setOrderByClause(" update_time desc");
        List<RunUser> runUsers = runUserMapper.selectByExample(example);
        return runUsers;
    }

    /**
     * 获取所有未被删除的用户
     *
     * @return
     * @throws AppException
     */
    @Override
    public List<RunUser> queryUsers() throws AppException {
        RunUserExample example = new RunUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(false);
        List<RunUser> runUsers = runUserMapper.selectByExample(example);
        return runUsers;
    }

    /**
     * 根据id集合查询用户集合（去除正常退出的用户）
     *
     * @param ids
     * @return
     * @throws AppException
     */
    @Override
    public Set<Integer> queryUsersByIds(Set<Integer> ids) throws AppException {
        if (ids.isEmpty()) {
            return null;
        }
        RunUserExample example = new RunUserExample();
        RunUserExample.Criteria criteria = example.createCriteria();
        criteria.andUidIn(new ArrayList<>(ids));
        criteria.andUserStatusEqualTo(true);
        example.setOrderByClause(" update_time desc");
        List<RunUser> list = runUserMapper.selectByExample(example);
        return list.stream().map(user ->user.getUid()).collect(Collectors.toSet());
    }
}
