package com.running.business.mapper;

import com.running.business.pojo.RunDeliveryBalance;
import com.running.business.pojo.RunDeliveryBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunDeliveryBalanceMapper {
    int countByExample(RunDeliveryBalanceExample example);

    int deleteByExample(RunDeliveryBalanceExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(RunDeliveryBalance record);

    int insertSelective(RunDeliveryBalance record);

    List<RunDeliveryBalance> selectByExample(RunDeliveryBalanceExample example);

    RunDeliveryBalance selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") RunDeliveryBalance record, @Param("example") RunDeliveryBalanceExample example);

    int updateByExample(@Param("record") RunDeliveryBalance record, @Param("example") RunDeliveryBalanceExample example);

    int updateByPrimaryKeySelective(RunDeliveryBalance record);

    int updateByPrimaryKey(RunDeliveryBalance record);
}