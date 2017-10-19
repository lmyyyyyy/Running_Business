package com.running.business.mapper;

import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunDeliveryAddressMapper {
    int countByExample(RunDeliveryAddressExample example);

    int deleteByExample(RunDeliveryAddressExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(RunDeliveryAddress record);

    int insertSelective(RunDeliveryAddress record);

    List<RunDeliveryAddress> selectByExample(RunDeliveryAddressExample example);

    RunDeliveryAddress selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") RunDeliveryAddress record, @Param("example") RunDeliveryAddressExample example);

    int updateByExample(@Param("record") RunDeliveryAddress record, @Param("example") RunDeliveryAddressExample example);

    int updateByPrimaryKeySelective(RunDeliveryAddress record);

    int updateByPrimaryKey(RunDeliveryAddress record);
}