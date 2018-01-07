package com.running.business.mapper;

import com.running.business.pojo.RunDeliveryBalanceRecord;
import com.running.business.pojo.RunDeliveryBalanceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunDeliveryBalanceRecordMapper {
    int countByExample(RunDeliveryBalanceRecordExample example);

    int deleteByExample(RunDeliveryBalanceRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunDeliveryBalanceRecord record);

    int insertSelective(RunDeliveryBalanceRecord record);

    List<RunDeliveryBalanceRecord> selectByExample(RunDeliveryBalanceRecordExample example);

    RunDeliveryBalanceRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunDeliveryBalanceRecord record, @Param("example") RunDeliveryBalanceRecordExample example);

    int updateByExample(@Param("record") RunDeliveryBalanceRecord record, @Param("example") RunDeliveryBalanceRecordExample example);

    int updateByPrimaryKeySelective(RunDeliveryBalanceRecord record);

    int updateByPrimaryKey(RunDeliveryBalanceRecord record);
}