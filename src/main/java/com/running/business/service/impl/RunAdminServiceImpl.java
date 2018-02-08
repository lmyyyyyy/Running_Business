package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.Config;
import com.running.business.common.ResultEnum;
import com.running.business.dto.UserDTO;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.JedisClient;
import com.running.business.mapper.RunAdminMapper;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunAdminExample;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.service.RunAdminInfoService;
import com.running.business.service.RunAdminService;
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

    @Autowired
    private RunAdminInfoService runAdminInfoService;
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
    public BaseResult saveRunAdmin(RunAdmin admin) throws AppException {
        if (admin == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        admin.setAdminPassword(Run_StringUtil.MD5(admin.getAdminPassword()));
        admin.setAdminTime(new Date());
        admin.setUpdateTime(new Date());
        admin.setStatus(false);
        admin.setIsDelete(false);
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
    public void updateRunAdminPassword(RunAdmin admin) throws AppException {
        if (admin == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        admin.setAdminPassword(Run_StringUtil.MD5(admin.getAdminPassword()));
        admin.setUpdateTime(new Date());
        runAdminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void updateRunAdmin(RunAdmin admin) throws AppException {
        if (admin == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        admin.setAdminPassword(Run_StringUtil.MD5(admin.getAdminPassword()));
        admin.setUpdateTime(new Date());
        runAdminMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * 根据用户id集合更新管理员登录状态
     *
     * @param userIds
     * @throws AppException
     */
    @Override
    public void updateAdminListStatus(Set<Integer> userIds) throws AppException {
        if (userIds == null || userIds.size() == 0) {
            return;
        }
        for (Integer userId : userIds) {
            RunAdmin user = new RunAdmin();
            user.setAdminId(userId);
            user.setUpdateTime(new Date());
            user.setStatus(false);
            runAdminMapper.updateByPrimaryKeySelective(user);
        }

    }

    @Override
    public BaseResult deleteRunAdminByID(Integer id) throws AppException {
        RunAdmin admin = runAdminMapper.selectByPrimaryKey(id);
        if (admin == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        admin.setIsDelete(true);
        admin.setUpdateTime(new Date());
        runAdminMapper.updateByPrimaryKeySelective(admin);
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
        if (admin == null) {
            throw new AppException(ResultEnum.ADMIN_INFO_IS_EMPTY);
        }

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

        admin.setUpdateTime(new Date());
        admin.setStatus(true);
        runAdminMapper.updateByPrimaryKeySelective(admin);

        RunAdminInfo adminInfo = (RunAdminInfo) runAdminInfoService.getRunAdminInfoByID(admin.getAdminId()).getData();
        if (adminInfo == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(admin.getAdminId());
        userDTO.setName(adminInfo.getAdminName());
        userDTO.setPhone(admin.getAdminUsername());
        userDTO.setStatus(admin.getStatus());
        userDTO.setUserType(UserTypeEnum.DELIVERY.getCode());
        UserUtil.bind(userDTO);
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
        RunAdmin admin = JsonUtils.jsonToPojo(json, RunAdmin.class);
        if (admin == null) {
            throw new AppException(ResultEnum.ADMIN_INFO_IS_EMPTY);
        }
        //登录人员列表中去除当前正常退出人员
        jedisClient.srem(Config.LOGIN_ADMIN_IDS_KEY, String.valueOf(admin.getAdminId()));
        admin.setStatus(false);
        admin.setUpdateTime(new Date());
        runAdminMapper.updateByPrimaryKeySelective(admin);
        return BaseResult.success(json);
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

    /**
     * 根据id集合查询当前在线的管理员集合
     *
     * @param ids
     * @return
     * @throws AppException
     */
    @Override
    public Set<Integer> queryAdminsByIds(Set<Integer> ids) throws AppException {
        if (ids.isEmpty()) {
            return null;
        }
        RunAdminExample example = new RunAdminExample();
        RunAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminIdIn(new ArrayList<>(ids));
        criteria.andStatusEqualTo(true);
        example.setOrderByClause(" update_time desc");
        List<RunAdmin> list = runAdminMapper.selectByExample(example);
        return list.stream().map(user ->user.getAdminId()).collect(Collectors.toSet());
    }

}
