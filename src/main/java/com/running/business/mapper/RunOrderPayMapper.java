package com.running.business.mapper;

import java.util.List;

import com.running.business.pojo.RunOrderPay;
import com.running.business.pojo.RunOrderPayExample;
import org.apache.ibatis.annotations.Param;

public interface RunOrderPayMapper {
    int countByExample(RunOrderPayExample example);

    int deleteByExample(RunOrderPayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunOrderPay record);

    int insertSelective(RunOrderPay record);

    List<RunOrderPay> selectByExample(RunOrderPayExample example);

    RunOrderPay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunOrderPay record, @Param("example") RunOrderPayExample example);

    int updateByExample(@Param("record") RunOrderPay record, @Param("example") RunOrderPayExample example);

    int updateByPrimaryKeySelective(RunOrderPay record);

    int updateByPrimaryKey(RunOrderPay record);
}