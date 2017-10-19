package com.running.business.mapper;

import com.running.business.pojo.RunDeliveryRecord;
import com.running.business.pojo.RunDeliveryRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunDeliveryRecordMapper {
    int countByExample(RunDeliveryRecordExample example);

    int deleteByExample(RunDeliveryRecordExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(RunDeliveryRecord record);

    int insertSelective(RunDeliveryRecord record);

    List<RunDeliveryRecord> selectByExample(RunDeliveryRecordExample example);

    RunDeliveryRecord selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") RunDeliveryRecord record, @Param("example") RunDeliveryRecordExample example);

    int updateByExample(@Param("record") RunDeliveryRecord record, @Param("example") RunDeliveryRecordExample example);

    int updateByPrimaryKeySelective(RunDeliveryRecord record);

    int updateByPrimaryKey(RunDeliveryRecord record);
}