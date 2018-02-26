package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;


public interface RunUserService {

    /**
     * 保存用户
     *
     * @param user
     * @return
     * @throws AppException
     */
    BaseResult saveUser(RunUser user) throws AppException;

    /**
     * 根据id删除用户
     *
     * @param uid
     * @return
     * @throws AppException
     */
    BaseResult deleteUser(Integer uid) throws AppException;

    /**
     * 更新用户
     *
     * @param user
     * @return
     * @throws AppException
     */
    BaseResult updateUser(RunUser user) throws AppException;

    /**
     * 根据用户id集合更新用户登录状态
     *
     * @param userIds
     * @throws AppException
     */
    void updateUserListStatus(Set<Integer> userIds) throws AppException;

    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return
     * @throws AppException
     */
    BaseResult checkUser(String username) throws AppException;

    /**
     * 验证用户名和密码是否匹配
     *
     * @param username
     * @param password
     * @return
     * @throws AppException
     */
    boolean checkPwd(String username, String password) throws AppException;

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     * @throws AppException
     */
    BaseResult getRunUser(Integer id) throws AppException;

    /**
     * 用户登录
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
     * 分页获取未被删除的用户列表
     *
     * @param page
     * @param size
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RunUser> pageAllRunUser(Integer page, Integer size, String orderType) throws AppException;

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     * @throws AppException
     */
    BaseResult getUserByToken(String token) throws AppException;

    /**
     * 登出
     *
     * @param token
     * @return
     * @throws AppException
     */
    BaseResult logout(String token) throws AppException;

    /**
     * 根据用户状态查询所有未被删除的用户
     *
     * @param status
     * @return
     * @throws AppException
     */
    List<RunUser> getUsersByStatus(boolean status) throws AppException;

    /**
     * 获取所有未被删除的用户
     *
     * @return
     * @throws AppException
     */
    List<RunUser> queryUsers() throws AppException;

    /**
     * 根据id集合查询当前在线的用户集合
     *
     * @param ids
     * @return
     * @throws AppException
     */
    Set<Integer> queryUsersByIds(Set<Integer> ids) throws AppException;

}
