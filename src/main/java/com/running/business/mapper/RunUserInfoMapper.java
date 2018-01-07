package com.running.business.mapper;

import java.util.List;

import com.running.business.pojo.RunUserInfo;
import com.running.business.pojo.RunUserInfoExample;
import org.apache.ibatis.annotations.Param;

public interface RunUserInfoMapper {
    int countByExample(RunUserInfoExample example);

    int deleteByExample(RunUserInfoExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(RunUserInfo record);

    int insertSelective(RunUserInfo record);

    List<RunUserInfo> selectByExample(RunUserInfoExample example);

    RunUserInfo selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") RunUserInfo record, @Param("example") RunUserInfoExample example);

    int updateByExample(@Param("record") RunUserInfo record, @Param("example") RunUserInfoExample example);

    int updateByPrimaryKeySelective(RunUserInfo record);

    int updateByPrimaryKey(RunUserInfo record);
}