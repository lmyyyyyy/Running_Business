package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.ResultEnum;
import com.running.business.enums.CouponStatusEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunUserCouponMapper;
import com.running.business.pojo.RunUserCoupon;
import com.running.business.pojo.RunUserCouponExample;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.RunUserCouponService;
import com.running.business.service.RunUserInfoService;
import com.running.business.util.DateUtil;
import com.running.business.vo.CouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RunUserCouponServiceImpl implements RunUserCouponService {

    @Autowired
    private RunUserCouponMapper runUserCouponMapper;

    @Autowired
    private RunUserInfoService runUserInfoService;

    /**
     * 创建优惠券
     *
     * @param runUserCoupon
     * @throws AppException
     */
    @Override
    public void saveRunUserCoupon(RunUserCoupon runUserCoupon) throws AppException {
        if (runUserCoupon == null) {
            throw new AppException(ResultEnum.USER_COUPON_INFO_EMTPY);
        }
        runUserCoupon.setStatus(CouponStatusEnum.AVALIBALE.getCode());
        runUserCouponMapper.insert(runUserCoupon);
    }

    /**
     * 更新优惠券
     *
     * @param runUserCoupon
     * @throws AppException
     */
    @Override
    public void updateRunUserCoupon(RunUserCoupon runUserCoupon) throws AppException {
        if (runUserCoupon == null) {
            throw new AppException(ResultEnum.USER_COUPON_INFO_EMTPY);
        }
        runUserCouponMapper.updateByPrimaryKeySelective(runUserCoupon);
    }

    /**
     * 根据ID删除优惠券
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void deleteRunUserCouponByID(Integer id) throws AppException {
        RunUserCoupon userCoupon = runUserCouponMapper.selectByPrimaryKey(id);
        if (userCoupon == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runUserCouponMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据用户id删除所有优惠券
     *
     * @param uid
     * @throws AppException
     */
    @Override
    public void deleteAllRunUserCoupon(Integer uid) throws AppException {
        RunUserCouponExample example = new RunUserCouponExample();
        RunUserCouponExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        runUserCouponMapper.deleteByExample(example);
    }

    /**
     * 根据id查询优惠券信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public CouponVO queryRunUserCouponByID(Integer id) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.USER_COUPON_ID_IS_ERROR);
        }
        RunUserCoupon coupon = runUserCouponMapper.selectByPrimaryKey(id);
        if (coupon == null) {
            return null;
        }
        return convertCoupon2VO(coupon);
    }

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
    @Override
    public PageInfo<CouponVO> pageRunUserCouponByUID(Integer uid, Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "begin_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        PageHelper.startPage(page, size);
        RunUserCouponExample example = new RunUserCouponExample();
        RunUserCouponExample.Criteria criteria = example.createCriteria();
        if (uid == null || uid < 0) {
            criteria.andUidEqualTo(uid);
        }
        if (status == null || status < 0) {
            criteria.andStatusEqualTo(status);
        }
        Long currentTime = System.currentTimeMillis();
        criteria.andExpiredTimeGreaterThanOrEqualTo(DateUtil.ms2Date(currentTime));
        criteria.andBeginTimeLessThanOrEqualTo(DateUtil.ms2Date(currentTime));
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RunUserCoupon> coupons = runUserCouponMapper.selectByExample(example);
        return new PageInfo<>(convertCoupons2VOs(coupons));
    }

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
    @Override
    public PageInfo<CouponVO> pageRunUserCouponByStatus(Integer status, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "begin_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        PageHelper.startPage(page, size);
        RunUserCouponExample example = new RunUserCouponExample();
        RunUserCouponExample.Criteria criteria = example.createCriteria();
        if (status != null || status > 0) {
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RunUserCoupon> coupons = runUserCouponMapper.selectByExample(example);
        return new PageInfo<>(convertCoupons2VOs(coupons));
    }

    /**
     * 优惠券集合转VO集合
     *
     * @param coupons
     * @return
     * @throws AppException
     */
    public List<CouponVO> convertCoupons2VOs(List<RunUserCoupon> coupons) throws AppException {
        if (coupons == null || coupons.isEmpty()) {
            return new ArrayList<>();
        }
        List<CouponVO> couponVOS = new ArrayList<>(coupons.size());
        for (RunUserCoupon coupon : coupons) {
            if (coupon == null) {
                continue;
            }
            CouponVO vo = convertCoupon2VO(coupon);
            if (vo == null) {
                continue;
            }
            couponVOS.add(vo);
        }
        return couponVOS;
    }

    /**
     * 将优惠券转VO
     *
     * @param coupon
     * @return
     * @throws AppException
     */
    public CouponVO convertCoupon2VO(RunUserCoupon coupon) throws AppException {
        if (coupon == null) {
            return null;
        }
        CouponVO vo = new CouponVO();
        vo.setUid(coupon.getUid());
        vo.setType(coupon.getType());
        vo.setTitle(coupon.getTitle());
        vo.setSubtract(coupon.getSubtract());
        vo.setId(coupon.getId());
        vo.setFull(coupon.getFull());
        vo.setExpiredTime(coupon.getExpiredTime());
        vo.setContent(coupon.getContent());
        vo.setBeginTime(coupon.getBeginTime());
        vo.setStatus(coupon.getStatus());
        vo.setStatusDesc(CouponStatusEnum.getCouponStatusEnum(coupon.getStatus()).getDesc());
        RunUserInfo info = runUserInfoService.getRunUserInfoById(coupon.getUid());
        if (info != null) {
            vo.setUserPhone(info.getUserPhone());
            vo.setUserName(info.getUserName());
        }
        return vo;
    }

}
