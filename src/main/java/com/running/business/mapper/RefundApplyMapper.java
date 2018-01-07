package com.running.business.mapper;

import com.running.business.pojo.RefundApply;
import com.running.business.pojo.RefundApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefundApplyMapper {
    int countByExample(RefundApplyExample example);

    int deleteByExample(RefundApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RefundApply record);

    int insertSelective(RefundApply record);

    List<RefundApply> selectByExample(RefundApplyExample example);

    RefundApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RefundApply record, @Param("example") RefundApplyExample example);

    int updateByExample(@Param("record") RefundApply record, @Param("example") RefundApplyExample example);

    int updateByPrimaryKeySelective(RefundApply record);

    int updateByPrimaryKey(RefundApply record);
}