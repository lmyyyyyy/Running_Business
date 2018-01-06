package com.running.business.mapper;

import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunUserMapper {
    int countByExample(RunUserExample example);

    int deleteByExample(RunUserExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(RunUser record);

    int insertSelective(RunUser record);

    List<RunUser> selectByExample(RunUserExample example);

    RunUser selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") RunUser record, @Param("example") RunUserExample example);

    int updateByExample(@Param("record") RunUser record, @Param("example") RunUserExample example);

    int updateByPrimaryKeySelective(RunUser record);

    int updateByPrimaryKey(RunUser record);
}