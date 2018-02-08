package com.running.business.mapper;

import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunOrderMapper {
    int countByExample(RunOrderExample example);

    int deleteByExample(RunOrderExample example);

    int deleteByPrimaryKey(String orderid);

    int insert(RunOrder record);

    int insertSelective(RunOrder record);

    List<RunOrder> selectByExample(RunOrderExample example);

    RunOrder selectByPrimaryKey(String orderid);

    int updateByExampleSelective(@Param("record") RunOrder record, @Param("example") RunOrderExample example);

    int updateByExample(@Param("record") RunOrder record, @Param("example") RunOrderExample example);

    int updateByPrimaryKeySelective(RunOrder record);

    int updateByPrimaryKey(RunOrder record);

}