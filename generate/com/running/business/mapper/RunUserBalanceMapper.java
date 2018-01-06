package com.running.business.mapper;

import com.running.business.pojo.RunUserBalance;
import com.running.business.pojo.RunUserBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunUserBalanceMapper {
    int countByExample(RunUserBalanceExample example);

    int deleteByExample(RunUserBalanceExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(RunUserBalance record);

    int insertSelective(RunUserBalance record);

    List<RunUserBalance> selectByExample(RunUserBalanceExample example);

    RunUserBalance selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") RunUserBalance record, @Param("example") RunUserBalanceExample example);

    int updateByExample(@Param("record") RunUserBalance record, @Param("example") RunUserBalanceExample example);

    int updateByPrimaryKeySelective(RunUserBalance record);

    int updateByPrimaryKey(RunUserBalance record);
}