package com.running.business.mapper;

import com.running.business.pojo.RunUserBalanceRecord;
import com.running.business.pojo.RunUserBalanceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunUserBalanceRecordMapper {
    int countByExample(RunUserBalanceRecordExample example);

    int deleteByExample(RunUserBalanceRecordExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(RunUserBalanceRecord record);

    int insertSelective(RunUserBalanceRecord record);

    List<RunUserBalanceRecord> selectByExample(RunUserBalanceRecordExample example);

    RunUserBalanceRecord selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") RunUserBalanceRecord record, @Param("example") RunUserBalanceRecordExample example);

    int updateByExample(@Param("record") RunUserBalanceRecord record, @Param("example") RunUserBalanceRecordExample example);

    int updateByPrimaryKeySelective(RunUserBalanceRecord record);

    int updateByPrimaryKey(RunUserBalanceRecord record);
}