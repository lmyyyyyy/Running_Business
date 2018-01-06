package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.JedisClient;
import com.running.business.mapper.RunAdminMapper;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunAdminExample;
import com.running.business.service.RunAdminService;
import com.running.business.util.CookieUtils;
import com.running.business.util.JsonUtils;
import com.running.business.util.Run_StringUtil;
import com.running.business.util.TimeUtil;
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
 * 管理员Service
 */
@Service
public class RunAdminServiceImpl implements RunAdminService {

    @Autowired
    private RunAdminMapper runAdminMapper;

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
     * 添加管理员
     *
     * @param admin
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult insertRunAdmin(RunAdmin admin) throws AppException {
        if (admin == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        admin.setAdminPassword(Run_StringUtil.MD5(admin.getAdminPassword()));
        admin.setAdminTime(TimeUtil.getCurrDate_yyyy_MM_dd());
        admin.setUpdateTime(new Date());
        Integer adminId = runAdminMapper.insert(admin);
        return BaseResult.success(adminId);
    }

    /**
     * 更新管理员密码
     *
     * @param admin
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult updateRunAdmin(RunAdmin admin) throws AppException {
        if (admin == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        admin.setAdminPassword(Run_StringUtil.MD5(admin.getAdminPassword()));
        admin.setUpdateTime(new Date());
        runAdminMapper.updateByPrimaryKeySelective(admin);
        return BaseResult.success(admin);
    }

    @Override
    public BaseResult delRunAdminByID(Integer id) throws AppException {
        RunAdmin admin = runAdminMapper.selectByPrimaryKey(id);
        if (admin == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runAdminMapper.deleteByPrimaryKey(id);
        return BaseResult.success(admin);
    }

    /**
     * 根据id获取管理员
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult getRunAdminByID(Integer id) throws AppException {
        RunAdmin admin = runAdminMapper.selectByPrimaryKey(id);
        if (admin == null) {
            throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
        }
        return BaseResult.success(admin);
    }

    /**
     * 获取所有管理员
     *
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult getAllRunAdmin() throws AppException {
        RunAdminExample example = new RunAdminExample();
        List<RunAdmin> list = runAdminMapper.selectByExample(example);
        if (list == null) {
            throw new AppException(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }
        return BaseResult.success(list);
    }

    /**
     * 检查账号是否存在
     *
     * @param userName
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult checkAdmin(String userName) throws AppException {
        if (userName == null || userName.trim().equals("")) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunAdminExample example = new RunAdminExample();
        RunAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminUsernameEqualTo(userName);
        List<RunAdmin> runAdmins = runAdminMapper.selectByExample(example);
        if (ValidateUtil.isValid(runAdmins)) {
            return BaseResult.fail(ResultEnum.TELTPHONE_USED);
        }
        return BaseResult.success();
    }

    /**
     * 管理员登录
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws AppException {
        RunAdminExample example = new RunAdminExample();
        RunAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminUsernameEqualTo(username);
        List<RunAdmin> list = null;
        list = runAdminMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.TELTPHONE_NOT_REG.getCode(), ResultEnum.TELTPHONE_NOT_REG.getMsg());
        }
        RunAdminExample example1 = new RunAdminExample();
        RunAdminExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andAdminUsernameEqualTo(username);
        criteria1.andAdminPasswordEqualTo(Run_StringUtil.MD5(password));
        list = runAdminMapper.selectByExample(example1);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.PWD_ERROR.getCode(), ResultEnum.PWD_ERROR.getMsg());
        }
        RunAdmin admin = list.get(0);
        //生成token
        String token = "RA" + UUID.randomUUID().toString();
        //保存用户之前，将用户对象中的密码清空
        admin.setAdminPassword(null);
        //把用户信息写入redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(admin));
        //设置sesison的生命周期
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        //添加cookie
        CookieUtils.setCookie(request, response, "RUN_TOKEN", token);
        //返回token
        return BaseResult.success(token);
    }

    /**
     * 根据token获取管理员
     *
     * @param token
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult getAdminByToken(String token) throws AppException {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);

        //判断是否为空
        if (StringUtils.isBlank(json)) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME.getCode(), ResultEnum.SESSION_IS_OUT_TIME.getMsg());
        }
        //更新生命周期
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        return BaseResult.success(JsonUtils.jsonToPojo(json, RunAdmin.class));
    }

    /**
     * 注销
     *
     * @param token
     * @return
     * @throws AppException
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
        return BaseResult.success(JsonUtils.jsonToPojo(json, RunAdmin.class));
    }

    /**
     * 检查旧密码是否匹配
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
        RunAdminExample example = new RunAdminExample();
        RunAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminPasswordEqualTo(Run_StringUtil.MD5(password));
        criteria.andAdminUsernameEqualTo(username);
        List<RunAdmin> list = runAdminMapper.selectByExample(example);
        if (ValidateUtil.isValid(list)) {
            return true;
        }
        return false;
    }

}
