package com.running.business.mapper;

import java.util.List;

import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryAddressExample;
import org.apache.ibatis.annotations.Param;

public interface RunDeliveryAddressMapper {
    int countByExample(RunDeliveryAddressExample example);

    int deleteByExample(RunDeliveryAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunDeliveryAddress record);

    int insertSelective(RunDeliveryAddress record);

    List<RunDeliveryAddress> selectByExample(RunDeliveryAddressExample example);

    RunDeliveryAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunDeliveryAddress record, @Param("example") RunDeliveryAddressExample example);

    int updateByExample(@Param("record") RunDeliveryAddress record, @Param("example") RunDeliveryAddressExample example);

    int updateByPrimaryKeySelective(RunDeliveryAddress record);

    int updateByPrimaryKey(RunDeliveryAddress record);
}