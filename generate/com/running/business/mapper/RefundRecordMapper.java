package com.running.business.mapper;

import com.running.business.pojo.RefundRecord;
import com.running.business.pojo.RefundRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefundRecordMapper {
    int countByExample(RefundRecordExample example);

    int deleteByExample(RefundRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RefundRecord record);

    int insertSelective(RefundRecord record);

    List<RefundRecord> selectByExample(RefundRecordExample example);

    RefundRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RefundRecord record, @Param("example") RefundRecordExample example);

    int updateByExample(@Param("record") RefundRecord record, @Param("example") RefundRecordExample example);

    int updateByPrimaryKeySelective(RefundRecord record);

    int updateByPrimaryKey(RefundRecord record);
}