package com.running.business.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;

/**
 * 对未处理的错误信息做一个统一处理
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    	System.out.println("asdfasdf");
        LOG.error(request.getRequestURI() + "接口发生错误, 错误信息:" + ex.getMessage());
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            BaseResult<String> result = null;
            if (ex instanceof AppException) {
                result = new BaseResult(CodeConstants.CODE_1, new ErrorMsg(((AppException) ex).getErrorCode(), ex.getMessage()));
            } else {
                result = new BaseResult(CodeConstants.CODE_1, new ErrorMsg(ResultEnum.INNER_ERROR.getCode(), ResultEnum.INNER_ERROR.getMsg()));
            }
            LOG.info(request.getRequestURI() + "接口返回：" + JSONObject.toJSONString(result));
            System.out.println(result.getCode());
            System.out.println(result.getError().getErrorCode());
            System.out.println(result.getError().getErrorMsg());
            writer.write(JSON.toJSONString(result));
            writer.flush();
        } catch (Exception e) {
            LOG.error("Exception:", e);
        }
        return null;
    }


}
