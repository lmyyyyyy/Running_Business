package com.running.business.mapper;

import java.util.List;

import com.running.business.pojo.RunDeliveryuser;
import com.running.business.pojo.RunDeliveryuserExample;
import org.apache.ibatis.annotations.Param;

public interface RunDeliveryuserMapper {
    int countByExample(RunDeliveryuserExample example);

    int deleteByExample(RunDeliveryuserExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(RunDeliveryuser record);

    int insertSelective(RunDeliveryuser record);

    List<RunDeliveryuser> selectByExample(RunDeliveryuserExample example);

    RunDeliveryuser selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") RunDeliveryuser record, @Param("example") RunDeliveryuserExample example);

    int updateByExample(@Param("record") RunDeliveryuser record, @Param("example") RunDeliveryuserExample example);

    int updateByPrimaryKeySelective(RunDeliveryuser record);

    int updateByPrimaryKey(RunDeliveryuser record);
}