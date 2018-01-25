package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunAdmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 管理员Service接口
 */
public interface RunAdminService {

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     * @throws AppException
     */
    BaseResult saveRunAdmin(RunAdmin admin) throws AppException;

    /**
     * 更新管理员
     *
     * @param admin
     * @throws AppException
     */
    void updateRunAdmin(RunAdmin admin) throws AppException;

    /**
     * 根据用户id集合更新管理员登录状态
     *
     * @param userIds
     * @throws AppException
     */
    void updateAdminListStatus(Set<Integer> userIds) throws AppException;

    /**
     * 修改管理员密码
     *
     * @param admin
     * @throws AppException
     */
    void updateRunAdminPassword(RunAdmin admin) throws AppException;

    /**
     * 删除管理员
     *
     * @param id
     * @return
     * @throws AppException
     */
    BaseResult deleteRunAdminByID(Integer id) throws AppException;

    /**
     * 根据id获取管理员
     *
     * @param id
     * @return
     * @throws AppException
     */
    BaseResult getRunAdminByID(Integer id) throws AppException;

    /**
     * 获取所有管理员
     *
     * @return
     * @throws AppException
     */
    BaseResult getAllRunAdmin() throws AppException;

    /**
     * 检查账号是否存在
     *
     * @param userName
     * @return
     * @throws AppException
     */
    BaseResult checkAdmin(String userName) throws AppException;


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
    BaseResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws AppException;

    /**
     * 根据token获取管理员
     *
     * @param token
     * @return
     * @throws AppException
     */
    BaseResult getAdminByToken(String token) throws AppException;

    /**
     * 注销
     *
     * @param token
     * @return
     * @throws AppException
     */
    BaseResult logout(String token) throws AppException;

    /**
     * 检查旧密码是否匹配
     *
     * @param username
     * @param password
     * @return
     * @throws AppException
     */
    boolean checkPwd(String username, String password) throws AppException;

    /**
     * 根据id集合查询当前在线的管理员集合
     *
     * @param ids
     * @return
     * @throws AppException
     */
    Set<Integer> queryAdminsByIds(Set<Integer> ids) throws AppException;
}
