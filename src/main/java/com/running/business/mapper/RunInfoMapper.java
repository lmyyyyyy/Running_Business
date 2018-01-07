package com.running.business.mapper;

import java.util.List;

import com.running.business.pojo.RunInfo;
import com.running.business.pojo.RunInfoExample;
import org.apache.ibatis.annotations.Param;

public interface RunInfoMapper {
    int countByExample(RunInfoExample example);

    int deleteByExample(RunInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunInfo record);

    int insertSelective(RunInfo record);

    List<RunInfo> selectByExample(RunInfoExample example);

    RunInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunInfo record, @Param("example") RunInfoExample example);

    int updateByExample(@Param("record") RunInfo record, @Param("example") RunInfoExample example);

    int updateByPrimaryKeySelective(RunInfo record);

    int updateByPrimaryKey(RunInfo record);
}