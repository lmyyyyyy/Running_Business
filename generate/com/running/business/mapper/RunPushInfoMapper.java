package com.running.business.mapper;

import com.running.business.pojo.RunPushInfo;
import com.running.business.pojo.RunPushInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunPushInfoMapper {
    int countByExample(RunPushInfoExample example);

    int deleteByExample(RunPushInfoExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(RunPushInfo record);

    int insertSelective(RunPushInfo record);

    List<RunPushInfo> selectByExampleWithBLOBs(RunPushInfoExample example);

    List<RunPushInfo> selectByExample(RunPushInfoExample example);

    RunPushInfo selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") RunPushInfo record, @Param("example") RunPushInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") RunPushInfo record, @Param("example") RunPushInfoExample example);

    int updateByExample(@Param("record") RunPushInfo record, @Param("example") RunPushInfoExample example);

    int updateByPrimaryKeySelective(RunPushInfo record);

    int updateByPrimaryKeyWithBLOBs(RunPushInfo record);

    int updateByPrimaryKey(RunPushInfo record);
}