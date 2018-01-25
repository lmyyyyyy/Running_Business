package com.running.business.mapper;

import com.running.business.pojo.HeartBeat;
import com.running.business.pojo.HeartBeatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HeartBeatMapper {
    int countByExample(HeartBeatExample example);

    int deleteByExample(HeartBeatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HeartBeat record);

    int insertSelective(HeartBeat record);

    List<HeartBeat> selectByExample(HeartBeatExample example);

    HeartBeat selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HeartBeat record, @Param("example") HeartBeatExample example);

    int updateByExample(@Param("record") HeartBeat record, @Param("example") HeartBeatExample example);

    int updateByPrimaryKeySelective(HeartBeat record);

    int updateByPrimaryKey(HeartBeat record);
}