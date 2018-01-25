package com.running.business.pojo;

public class RunServiceLogWithBLOBs extends RunServiceLog {
    private String methodParam;

    private String returnValue;

    private String errorMessage;

    public RunServiceLogWithBLOBs(Integer id, String className, String methodName, Long timeCost, Integer operatorId, String operatorName, Integer invokeStatus, Date addTime, Date updateTime, Integer parentId, String methodParam, String returnValue, String errorMessage) {
        super(id, className, methodName, timeCost, operatorId, operatorName, invokeStatus, addTime, updateTime, parentId);
        this.methodParam = methodParam;
        this.returnValue = returnValue;
        this.errorMessage = errorMessage;
    }

    public RunServiceLogWithBLOBs() {
        super();
    }

    public String getMethodParam() {
        return methodParam;
    }

    public void setMethodParam(String methodParam) {
        this.methodParam = methodParam == null ? null : methodParam.trim();
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue == null ? null : returnValue.trim();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }
}