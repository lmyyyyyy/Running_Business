package com.running.business.mapper;

import com.running.business.pojo.RunServiceLog;
import com.running.business.pojo.RunServiceLogExample;
import com.running.business.pojo.RunServiceLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}