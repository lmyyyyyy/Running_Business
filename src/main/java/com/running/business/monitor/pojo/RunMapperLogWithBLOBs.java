package com.running.business.monitor.pojo;

import java.util.Date;

public class RunMapperLogWithBLOBs extends RunMapperLog {
    private String methodName;

    private String methodParam;

    private String sqlStatement;

    private String errorMessage;

    private RunServiceLog parent;

    public RunMapperLogWithBLOBs(Integer id, Integer serivceLogId, String targetTableName, Byte operateType, Integer influenceRow, Byte invokeStatus, Date addTime, Date updateTime, Long timeCost, String methodName, String methodParam, String sqlStatement, String errorMessage) {
        super(id, serivceLogId, targetTableName, operateType, influenceRow, invokeStatus, addTime, updateTime, timeCost);
        this.methodName = methodName;
        this.methodParam = methodParam;
        this.sqlStatement = sqlStatement;
        this.errorMessage = errorMessage;
    }

    public RunMapperLogWithBLOBs() {
        super();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getMethodParam() {
        return methodParam;
    }

    public void setMethodParam(String methodParam) {
        this.methodParam = methodParam == null ? null : methodParam.trim();
    }

    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement == null ? null : sqlStatement.trim();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    public RunServiceLog getParent() {
        return parent;
    }

    public void setParent(RunServiceLog parent) {
        this.parent = parent;
    }
}