package com.running.business.monitor.mapper;


import com.running.business.monitor.pojo.RunServiceLog;
import com.running.business.monitor.pojo.RunServiceLogExample;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RunServiceLogMapper {
    int countByExample(RunServiceLogExample example);

    int deleteByExample(RunServiceLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunServiceLogWithBLOBs record);

    int insertSelective(RunServiceLogWithBLOBs record);

    List<RunServiceLogWithBLOBs> selectByExampleWithBLOBs(RunServiceLogExample example);

    List<RunServiceLog> selectByExample(RunServiceLogExample example);

    RunServiceLogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunServiceLogWithBLOBs record, @Param("example") RunServiceLogExample example);

    int updateByExampleWithBLOBs(@Param("record") RunServiceLogWithBLOBs record, @Param("example") RunServiceLogExample example);

    int updateByExample(@Param("record") RunServiceLog record, @Param("example") RunServiceLogExample example);

    int updateByPrimaryKeySelective(RunServiceLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(RunServiceLogWithBLOBs record);

    int updateByPrimaryKey(RunServiceLog record);

    int batchInsert(List<RunServiceLogWithBLOBs> list);

    int batchUpdateParentId(@Param("items")List<RunServiceLogWithBLOBs> items);
}