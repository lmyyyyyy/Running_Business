package com.running.business.mapper;

import java.util.List;

import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunDeliveryInfoExample;
import org.apache.ibatis.annotations.Param;

public interface RunDeliveryInfoMapper {
    int countByExample(RunDeliveryInfoExample example);

    int deleteByExample(RunDeliveryInfoExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(RunDeliveryInfo record);

    int insertSelective(RunDeliveryInfo record);

    List<RunDeliveryInfo> selectByExample(RunDeliveryInfoExample example);

    RunDeliveryInfo selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") RunDeliveryInfo record, @Param("example") RunDeliveryInfoExample example);

    int updateByExample(@Param("record") RunDeliveryInfo record, @Param("example") RunDeliveryInfoExample example);

    int updateByPrimaryKeySelective(RunDeliveryInfo record);

    int updateByPrimaryKey(RunDeliveryInfo record);
}