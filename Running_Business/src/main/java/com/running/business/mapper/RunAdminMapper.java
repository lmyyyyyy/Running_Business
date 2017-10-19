package com.running.business.mapper;

import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunAdminMapper {
    int countByExample(RunAdminExample example);

    int deleteByExample(RunAdminExample example);

    int deleteByPrimaryKey(Integer adminId);

    int insert(RunAdmin record);

    int insertSelective(RunAdmin record);

    List<RunAdmin> selectByExample(RunAdminExample example);

    RunAdmin selectByPrimaryKey(Integer adminId);

    int updateByExampleSelective(@Param("record") RunAdmin record, @Param("example") RunAdminExample example);

    int updateByExample(@Param("record") RunAdmin record, @Param("example") RunAdminExample example);

    int updateByPrimaryKeySelective(RunAdmin record);

    int updateByPrimaryKey(RunAdmin record);
}