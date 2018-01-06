package com.running.business.mapper;

import com.running.business.pojo.RunAdminInfo;
import com.running.business.pojo.RunAdminInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunAdminInfoMapper {
    int countByExample(RunAdminInfoExample example);

    int deleteByExample(RunAdminInfoExample example);

    int deleteByPrimaryKey(Integer adminId);

    int insert(RunAdminInfo record);

    int insertSelective(RunAdminInfo record);

    List<RunAdminInfo> selectByExample(RunAdminInfoExample example);

    RunAdminInfo selectByPrimaryKey(Integer adminId);

    int updateByExampleSelective(@Param("record") RunAdminInfo record, @Param("example") RunAdminInfoExample example);

    int updateByExample(@Param("record") RunAdminInfo record, @Param("example") RunAdminInfoExample example);

    int updateByPrimaryKeySelective(RunAdminInfo record);

    int updateByPrimaryKey(RunAdminInfo record);
}