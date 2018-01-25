package com.running.business.mapper;

import com.running.business.pojo.RunMapperLog;
import com.running.business.pojo.RunMapperLogExample;
import com.running.business.pojo.RunMapperLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunMapperLogMapper {
    int countByExample(RunMapperLogExample example);

    int deleteByExample(RunMapperLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunMapperLogWithBLOBs record);

    int insertSelective(RunMapperLogWithBLOBs record);

    List<RunMapperLogWithBLOBs> selectByExampleWithBLOBs(RunMapperLogExample example);

    List<RunMapperLog> selectByExample(RunMapperLogExample example);

    RunMapperLogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunMapperLogWithBLOBs record, @Param("example") RunMapperLogExample example);

    int updateByExampleWithBLOBs(@Param("record") RunMapperLogWithBLOBs record, @Param("example") RunMapperLogExample example);

    int updateByExample(@Param("record") RunMapperLog record, @Param("example") RunMapperLogExample example);

    int updateByPrimaryKeySelective(RunMapperLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(RunMapperLogWithBLOBs record);

    int updateByPrimaryKey(RunMapperLog record);
}