package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserCoupon;
import com.running.business.vo.CouponVO;

import java.util.List;

public interface RunUserCouponService {

    /**
     * 创建优惠券
     *
     * @param runUserCoupon
     * @throws AppException
     */
    void saveRunUserCoupon(RunUserCoupon runUserCoupon) throws AppException;

    /**
     * 给固定用户列表添加优惠券
     *
     * @param runUserCoupon
     * @throws AppException
     */
    void saveRunUserCouponByUsers(RunUserCoupon runUserCoupon, List<Integer> userIds) throws AppException;

    /**
     * 更新优惠券
     *
     * @param runUserCoupon
     * @throws AppException
     */
    void updateRunUserCoupon(RunUserCoupon runUserCoupon) throws AppException;

    /**
     * 根据ID删除优惠券
     *
     * @param id
     * @throws AppException
     */
    void deleteRunUserCouponByID(Integer id) throws AppException;

    /**
     * 根据用户id删除所有优惠券
     *
     * @param uid
     * @throws AppException
     */
    void deleteAllRunUserCoupon(Integer uid) throws AppException;

    /**
     * 根据id查询优惠券信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    CouponVO queryRunUserCouponByID(Integer id) throws AppException;

    /**
     * 获取该用户下的所有可用的优惠券
     *
     * @param uid
     * @return
     * @throws AppException
     */
    List<CouponVO> queryCouponsByUID(Integer uid) throws AppException;

    /**
     * 根据用户id分页查询优惠券
     *
     * @param uid
     * @param status
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<CouponVO> pageRunUserCouponByUID(Integer uid, Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException;

    /**
     * 根据状态分页查询优惠券
     *
     * @param status
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<CouponVO> pageRunUserCouponByStatus(Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException;
}
