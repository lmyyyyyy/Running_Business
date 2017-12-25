package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunAdmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    BaseResult insertRunAdmin(RunAdmin admin) throws AppException;

    BaseResult updateRunAdmin(RunAdmin admin) throws AppException;

    /**
     * 删除管理员
     *
     * @param id
     * @return
     * @throws AppException
     */
    BaseResult delRunAdminByID(Integer id) throws AppException;

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
}
