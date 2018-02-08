package com.running.business.service;

import com.running.business.dto.UserDTO;
import com.running.business.exception.AppException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liumingyu
 * @create 2018-01-21 下午7:17
 */
public interface SSOService {

    /**
     * 根据request获取UsertDTO
     *
     * @param request
     * @return
     * @throws AppException
     */
    UserDTO getUserDTO(HttpServletRequest request) throws AppException;

    /**
     * 获取用户UserDTO
     *
     * @param request
     * @return
     * @throws AppException
     */
    UserDTO getRunUser(HttpServletRequest request) throws AppException;

    /**
     * 获取配送员UserDTO
     *
     * @param request
     * @return
     * @throws AppException
     */
    UserDTO getRunDelivery(HttpServletRequest request) throws AppException;

    /**
     * 获取管理员UserDTO
     *
     * @param request
     * @return
     * @throws AppException
     */
    UserDTO getRunAdmin(HttpServletRequest request) throws AppException;

    /**
     * 更新用户状态
     *
     * @param id
     * @throws AppException
     */
    void updateUserStatus(Integer id) throws AppException;

    /**
     * 更新用户状态
     *
     * @param id
     * @throws AppException
     */
    void updateDeliveryStatus(Integer id) throws AppException;

    /**
     * 更新用户状态
     *
     * @param id
     * @throws AppException
     */
    void updateAdminStatus(Integer id) throws AppException;

    /**
     * 检查用户是否已被删除 true：已删，false：未删
     *
     * @param id
     * @return
     * @throws AppException
     */
    boolean checkUserIsDel(Integer id) throws AppException;

    /**
     * 检查配送员是否已被删除 true：已删，false：未删
     *
     * @param id
     * @return
     * @throws AppException
     */
    boolean checkDeliveryIsDel(Integer id) throws AppException;

    /**
     * 检查管理员是否已被删除 true：已删，false：未删
     *
     * @param id
     * @return
     * @throws AppException
     */
    boolean checkAdminIsDel(Integer id) throws AppException;
}
