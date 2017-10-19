package com.running.business.mapper;

import com.running.business.pojo.RunUserCoupon;
import com.running.business.pojo.RunUserCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunUserCouponMapper {
    int countByExample(RunUserCouponExample example);

    int deleteByExample(RunUserCouponExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(RunUserCoupon record);

    int insertSelective(RunUserCoupon record);

    List<RunUserCoupon> selectByExample(RunUserCouponExample example);

    RunUserCoupon selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") RunUserCoupon record, @Param("example") RunUserCouponExample example);

    int updateByExample(@Param("record") RunUserCoupon record, @Param("example") RunUserCouponExample example);

    int updateByPrimaryKeySelective(RunUserCoupon record);

    int updateByPrimaryKey(RunUserCoupon record);
}