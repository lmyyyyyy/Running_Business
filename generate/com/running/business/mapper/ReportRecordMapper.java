package com.running.business.mapper;

import com.running.business.pojo.ReportRecord;
import com.running.business.pojo.ReportRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportRecordMapper {
    int countByExample(ReportRecordExample example);

    int deleteByExample(ReportRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportRecord record);

    int insertSelective(ReportRecord record);

    List<ReportRecord> selectByExample(ReportRecordExample example);

    ReportRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportRecord record, @Param("example") ReportRecordExample example);

    int updateByExample(@Param("record") ReportRecord record, @Param("example") ReportRecordExample example);

    int updateByPrimaryKeySelective(ReportRecord record);

    int updateByPrimaryKey(ReportRecord record);
}