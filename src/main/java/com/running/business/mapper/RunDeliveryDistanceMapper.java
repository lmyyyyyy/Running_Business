package com.running.business.mapper;

import java.util.List;

import com.running.business.pojo.RunDeliveryDistance;
import com.running.business.pojo.RunDeliveryDistanceExample;
import org.apache.ibatis.annotations.Param;

public interface RunDeliveryDistanceMapper {
    int countByExample(RunDeliveryDistanceExample example);

    int deleteByExample(RunDeliveryDistanceExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(RunDeliveryDistance record);

    int insertSelective(RunDeliveryDistance record);

    List<RunDeliveryDistance> selectByExample(RunDeliveryDistanceExample example);

    RunDeliveryDistance selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") RunDeliveryDistance record, @Param("example") RunDeliveryDistanceExample example);

    int updateByExample(@Param("record") RunDeliveryDistance record, @Param("example") RunDeliveryDistanceExample example);

    int updateByPrimaryKeySelective(RunDeliveryDistance record);

    int updateByPrimaryKey(RunDeliveryDistance record);
}