package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface RunUserService {

    BaseResult insertUser(RunUser user) throws AppException;

    BaseResult delUser(Integer uid) throws AppException;

    BaseResult updateUser(RunUser user) throws AppException;


    BaseResult checkUser(String username) throws AppException;

    boolean checkPwd(String username, String password) throws AppException;

    BaseResult getRunUser(Integer id) throws AppException;

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

    BaseResult getUserByToken(String token) throws AppException;

    BaseResult logout(String token) throws AppException;

}
