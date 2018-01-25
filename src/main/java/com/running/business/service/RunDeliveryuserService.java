package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunDeliveryuser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public interface RunDeliveryuserService {

    /**
     * 添加配送员
     *
     * @param user
     * @return
     */
    Integer saveRunDeliveryuser(RunDeliveryuser user) throws AppException;

    /**
     * 根据id删除配送员
     *
     * @param uid
     * @throws AppException
     */
    void deleteRunDeliveryuser(Integer uid) throws AppException;

    /**
     * 修改配送员密码
     *
     * @param user
     * @throws AppException
     */
    void updateRunDeliveryuserPassword(RunDeliveryuser user) throws AppException;

    /**
     * 更新配送员信息
     *
     * @param runDeliveryuser
     * @throws AppException
     */
    void updateRunDeliveryuser(RunDeliveryuser runDeliveryuser) throws AppException;

    /**
     * 根据用户id集合更新配送员登录状态
     *
     * @param userIds
     * @throws AppException
     */
    void updateDeliveryListStatus(Set<Integer> userIds) throws AppException;

    /**
     * 检查账号是否被注册 true:可用／没注册过 false:不可用／注册过
     *
     * @param userphone
     * @return
     * @throws AppException
     */
    boolean checkRunDeliveryuser(String userphone) throws AppException;

    /**
     * 根据id获取配送员账号信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    RunDeliveryuser getRunDeliveryuserById(Integer id) throws AppException;

    /**
     * 配送员登录
     *
     * @param phone
     * @param password
     * @param request
     * @param response
     * @return
     * @throws AppException
     */
    String login(String phone, String password, HttpServletRequest request, HttpServletResponse response) throws AppException;

    /**
     * 分页获取未被删除的配送员列表
     *
     * @param page
     * @param size
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RunDeliveryuser> pageAllRunDeliveryuser(Integer page, Integer size, String orderType) throws AppException;

    /**
     * 检查账号和密码是否匹配 true:匹配 false:不匹配
     *
     * @param phone
     * @param password
     * @return
     * @throws AppException
     */
    boolean checkPhoneAndPwd(String phone, String password) throws AppException;

    /**
     * 根据token从redis中查询配送员信息
     *
     * @param token token码
     * @return 如何为空则返回会话超时，否则更新生命周期，将用户返回
     */
    BaseResult getDeliveryuserByToken(String token) throws AppException;

    /**
     * 注销用户，根据token从redis中查询配送员信息
     *
     * @param token token码
     * @return 如果token为空，说明会话超时，否则将该token删除，返回用户实体
     */
    BaseResult logout(String token) throws AppException;

    /**
     * 根据配送员状态获取所有未被删除未被禁用的配送员
     *
     * @param status
     * @return
     * @throws AppException
     */
    List<RunDeliveryuser> getRunDeliveryuserByStatus(boolean status) throws AppException;

    /**
     * 根据id集合查询当前在线的配送员集合
     *
     * @param ids
     * @return
     * @throws AppException
     */
    Set<Integer> queryDeliverysByIds(Set<Integer> ids) throws AppException;
}
