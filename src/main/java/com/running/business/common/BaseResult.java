package com.running.business.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.running.business.exception.AppException;

import javax.xml.transform.Result;

/**
 * 自定义响应结构体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 4490411721234093609L;

    private String code;

    private Object data;

    private String message;

    public BaseResult() {
    }

    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(String code, Object data) {
        this.code = code;
        this.data = data;

        //校验id返回
        if (data instanceof Long) {
            Map<String, Object> attachment = new HashMap<>();
            attachment.put("id", data);
            this.data = attachment;
        } else {
            this.data = data;
        }
    }

    public BaseResult(String code) {
        this.code = code;
    }

    public BaseResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMsg();
    }

    public BaseResult(PageInfo pageInfo) {
        this.code = ResultEnum.SUCCESS.getCode();
        Map<String, Object> attachment = new HashMap<>();
        attachment.put("items", pageInfo.getList());
        attachment.put("totalCount", pageInfo.getTotal());
        this.data = attachment;
    }

    public static BaseResult success(PageInfo pageInfo) {
        return new BaseResult(pageInfo);
    }

    public static BaseResult  success() {
        return new BaseResult(ResultEnum.SUCCESS.getCode());
    }

    public static BaseResult success(Object obj) {
        return new BaseResult(ResultEnum.SUCCESS.getCode(), obj);
    }

    public static BaseResult fail() {
        return new BaseResult(CodeConstants.CODE_1);
    }

    public static BaseResult fail(ResultEnum resultEnum) {
        return new BaseResult(resultEnum);
    }

    public static BaseResult fail(String code, String message) {
        return new BaseResult(code, message);
    }

    public static BaseResult fail(String message) {
        return new BaseResult(CodeConstants.CODE_1, message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
