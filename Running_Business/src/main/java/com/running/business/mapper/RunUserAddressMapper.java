package com.running.business.mapper;

import com.running.business.pojo.RunUserAddress;
import com.running.business.pojo.RunUserAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunUserAddressMapper {
    int countByExample(RunUserAddressExample example);

    int deleteByExample(RunUserAddressExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(RunUserAddress record);

    int insertSelective(RunUserAddress record);

    List<RunUserAddress> selectByExample(RunUserAddressExample example);

    RunUserAddress selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") RunUserAddress record, @Param("example") RunUserAddressExample example);

    int updateByExample(@Param("record") RunUserAddress record, @Param("example") RunUserAddressExample example);

    int updateByPrimaryKeySelective(RunUserAddress record);

    int updateByPrimaryKey(RunUserAddress record);
}