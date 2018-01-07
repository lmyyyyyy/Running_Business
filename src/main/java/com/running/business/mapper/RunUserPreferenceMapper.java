package com.running.business.mapper;

import java.util.List;

import com.running.business.pojo.RunUserPreference;
import com.running.business.pojo.RunUserPreferenceExample;
import org.apache.ibatis.annotations.Param;

public interface RunUserPreferenceMapper {
    int countByExample(RunUserPreferenceExample example);

    int deleteByExample(RunUserPreferenceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunUserPreference record);

    int insertSelective(RunUserPreference record);

    List<RunUserPreference> selectByExample(RunUserPreferenceExample example);

    RunUserPreference selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunUserPreference record, @Param("example") RunUserPreferenceExample example);

    int updateByExample(@Param("record") RunUserPreference record, @Param("example") RunUserPreferenceExample example);

    int updateByPrimaryKeySelective(RunUserPreference record);

    int updateByPrimaryKey(RunUserPreference record);
}