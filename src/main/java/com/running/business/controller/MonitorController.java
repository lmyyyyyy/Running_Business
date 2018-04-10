package com.running.business.controller;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.monitor.service.RunMethodLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author liumingyu
 * @create 2018-01-25 下午4:03
 */
@RestController
@RequestMapping("/monitor")
@Api(value = "监控平台模块", tags = {"监控平台模块"})
public class MonitorController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    private static final String LOG_PREFIX = "【监控平台模块】 ";

    @Autowired
    private RunMethodLogService runMethodLogService;

    /**
     * 根据id查询service日志
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询service日志(纪喜文)", notes = "根据id查询service日志", response = BaseResult.class)
    public BaseResult queryServiceLogById(@PathVariable("id") Integer id) throws Exception {
        if (id == null || id <= 0) {
            LOGGER.error(LOG_PREFIX + "查询service日志id输入错误 id = {}", id);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据id查询service日志：" + id);
        return BaseResult.success(runMethodLogService.queryServiceLogById(id));
    }

    /**
     * 根据操作者id查询service日志
     *
     * @param oId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/oid", method = RequestMethod.GET)
    @ApiOperation(value = "根据操作者id查询service日志(纪喜文)", notes = "根据操作者id查询service日志", response = BaseResult.class)
    public BaseResult queryServiceLogByOID(@RequestParam(value = "oId") Integer oId,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                           @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                           @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (oId == null || oId <= 0) {
            LOGGER.error(LOG_PREFIX + "查询service日志oId输入错误 oId = {}", oId);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据操作者id查询service日志：" + oId);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogByOID(oId, page, size, orderField, orderType)));
    }

    /**
     * 根据执行状态查询service日志
     *
     * @param invokerStatus
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/status", method = RequestMethod.GET)
    @ApiOperation(value = "根据执行状态查询service日志(纪喜文)", notes = "根据执行状态查询service日志", response = BaseResult.class)
    public BaseResult queryServiceLogByStatus(@RequestParam(value = "invokerStatus") Integer invokerStatus,
                                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                              @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                              @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                              @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (invokerStatus == null) {
            LOGGER.error(LOG_PREFIX + "查询service日志执行状态参数错误invokerStatus = {}", invokerStatus);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行状态查询service日志：" + invokerStatus);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogByStatus(invokerStatus, page, size, orderField, orderType)));
    }

    /**
     * 根据父id查询service日志
     *
     * @param pId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/pid", method = RequestMethod.GET)
    @ApiOperation(value = "根据父id查询service日志(纪喜文)", notes = "根据父id查询service日志", response = BaseResult.class)
    public BaseResult queryServiceLogByPId(@RequestParam(value = "pId") Integer pId,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                           @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                           @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (pId == null || pId <= 0) {
            LOGGER.error(LOG_PREFIX + "查询service日志pId输入错误 pId = {}", pId);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据父id查询service日志：" + pId);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogByPID(pId, page, size, orderField, orderType)));
    }

    /**
     * 根据类名模糊查询Service日志
     *
     * @param className
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/classname", method = RequestMethod.GET)
    @ApiOperation(value = "根据类名模糊查询Service日志(纪喜文)", notes = "根据类名模糊查询Service日志", response = BaseResult.class)
    public BaseResult queryServiceLogByClassName(@RequestParam(value = "className") String className,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                 @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                 @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (className == null || "".equals(className)) {
            LOGGER.error(LOG_PREFIX + "查询service日志类名输入错误 className = {}", className);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据类名查询service日志：" + className);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogLikeClassName(className, page, size, orderField, orderType)));
    }

    /**
     * 根据方法名模糊查询Service日志
     *
     * @param methodName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/methodname", method = RequestMethod.GET)
    @ApiOperation(value = "根据方法名模糊查询Service日志(纪喜文)", notes = "根据方法名模糊查询Service日志", response = BaseResult.class)
    public BaseResult queryServiceLogByMethodName(@RequestParam(value = "methodName") String methodName,
                                                  @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                  @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                  @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (methodName == null || "".equals(methodName)) {
            LOGGER.error(LOG_PREFIX + "查询service日志方法名输入错误 methodName = {}", methodName);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据方法名查询service日志：" + methodName);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogLikeMethodName(methodName, page, size, orderField, orderType)));
    }

    /**
     * 根据操作者姓名查询Service日志
     *
     * @param operatorName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/operatorname", method = RequestMethod.GET)
    @ApiOperation(value = "根据操作者姓名查询Service日志(纪喜文)", notes = "根据操作者姓名查询Service日志", response = BaseResult.class)
    public BaseResult queryServiceLogByOperatorName(@RequestParam(value = "operatorName") String operatorName,
                                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                    @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                    @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (operatorName == null || "".equals(operatorName)) {
            LOGGER.error(LOG_PREFIX + "查询service日志操作者姓名输入错误 operatorName = {}", operatorName);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据操作者姓名查询Service日志：" + operatorName);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogLikeOperatorName(operatorName, page, size, orderField, orderType)));
    }

    /**
     * 根据方法参数模糊查询Service日志
     *
     * @param methodParam
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/query/serviceLog/methodparam", method = RequestMethod.GET)
    @ApiOperation(value = "根据方法参数模糊查询Service日志(纪喜文)", notes = "根据方法参数模糊查询Service日志", response = BaseResult.class)
    public BaseResult queryServiceLogByMethodParam(@RequestParam(value = "methodParam") String methodParam,
                                                   @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                   @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                   @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (methodParam == null || "".equals(methodParam)) {
            LOGGER.error(LOG_PREFIX + "查询service日志方法参数输入错误 methodParam = {}", methodParam);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据方法参数模糊查询Service日志：" + methodParam);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogLikeMethodParam(methodParam, page, size, orderField, orderType)));
    }

    /**
     * 根据返回值模糊查询Service日志
     *
     * @param returnValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/returnvalue", method = RequestMethod.GET)
    @ApiOperation(value = "根据返回值模糊查询Service日志(纪喜文)", notes = "根据返回值模糊查询Service日志", response = BaseResult.class)
    public BaseResult queryServiceLogByReturnValue(@RequestParam(value = "returnValue") String returnValue,
                                                   @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                   @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                   @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (returnValue == null || "".equals(returnValue)) {
            LOGGER.error(LOG_PREFIX + "查询service日志返回值输入错误 returnValue = {}", returnValue);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据返回值模糊查询Service日志：" + returnValue);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogLikeReturnValue(returnValue, page, size, orderField, orderType)));
    }

    /**
     * 根据错误信息模糊查询Service日志
     *
     * @param errorMessage
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/query/serviceLog/errormessage", method = RequestMethod.GET)
    @ApiOperation(value = "根据错误信息模糊查询Service日志(纪喜文)", notes = "根据错误信息模糊查询Service日志", response = BaseResult.class)
    public BaseResult queryServiceLogByErrorMessage(@RequestParam(value = "errorMessage") String errorMessage,
                                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                    @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                    @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (errorMessage == null || "".equals(errorMessage)) {
            LOGGER.error(LOG_PREFIX + "查询service日志错误信息输入错误 errorMessage = {}", errorMessage);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据错误信息模糊查询Service日志：" + errorMessage);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogLikeErrorMessage(errorMessage, page, size, orderField, orderType)));
    }

    /**
     * 范围查询，根据执行时间查询startTime到endTime中间的日志
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/methodtime", method = RequestMethod.GET)
    @ApiOperation(value = "根据执行时间查询startTime到endTime中间的日志(纪喜文)", notes = "根据执行时间查询startTime到endTime中间的日志", response = BaseResult.class)
    public BaseResult queryServiceLogByMethodTime(@RequestParam(value = "startTime") Long startTime,
                                                  @RequestParam(value = "endTime") Long endTime,
                                                  @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                  @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                  @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (startTime == null || startTime < 0 || endTime < startTime || endTime == null || endTime < 0) {
            LOGGER.error(LOG_PREFIX + "查询service日志时间参数输入错误startTime = {} endTime = {} ", startTime, endTime);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行时间查询startTime到endTime中间的日志startTime = {} endTime = {} ", startTime, endTime);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogByMethodTime(startTime, endTime, page, size, orderField, orderType)));
    }

    /**
     * 范围查询，根据执行时间查询beginTime到endTime中间的日志
     *
     * @param beginTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/executiontime", method = RequestMethod.GET)
    @ApiOperation(value = "根据执行时间查询startTime到endTime中间的日志(纪喜文)", notes = "根据执行时间查询startTime到endTime中间的日志", response = BaseResult.class)
    public BaseResult queryServiceLogByExecutionTime(@RequestParam(value = "beginTime") Date beginTime,
                                                     @RequestParam(value = "endTime") Date endTime,
                                                     @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                     @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                     @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                     @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (beginTime == null || endTime == null) {
            LOGGER.error(LOG_PREFIX + "查询service日志时间参数输入错误beginTime = {} endTime = {} ", beginTime, endTime);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行时间查询startTime到endTime中间的日志beginTime = {} endTime = {} ", beginTime, endTime);
        return BaseResult.success(new PageInfo(runMethodLogService.pageServiceLogByExecutionTime(beginTime, endTime, page, size, orderField, orderType)));
    }

    /**
     * 根据service方法id查询mapper日志
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询mapper日志(巨延生)", notes = "根据id查询mapper日志", response = BaseResult.class)
    public BaseResult queryMapperLogByServiceId(@PathVariable("id") Integer id,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (id == null || id <= 0) {
            LOGGER.error(LOG_PREFIX + "service方法id查询mapper日志输入错误 id = {}", id);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据service方法id查询mapper日志：" + id);
        return BaseResult.success(runMethodLogService.queryMapperLogByID(id));
    }
    /**
     * 根据操作类型查询mapper日志
     *
     * @param OperateType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapper/log/operateType", method = RequestMethod.GET)
    @ApiOperation(value = "根据操作类型查询mapper日志(巨延生)", notes = "根据操作类型查询mapper日志", response = BaseResult.class)
    public BaseResult pageMapperLogByOperateType(@PathVariable(value = "OperateType") Byte OperateType,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                 @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                 @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (OperateType == null || OperateType <= 0) {
            LOGGER.error(LOG_PREFIX + "操作类型查询mapper日志输入错误 OperateType = {}", OperateType);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据操作类型查询mapper日志：" + OperateType);
        return BaseResult.success(runMethodLogService.pageMapperLogByOperateType(OperateType,page,size,orderField,orderType));
    }
    /**
     * 根据影响行数查询mapper日志
     *
     * @param InfluenceRow
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapper/log/influenceRow", method = RequestMethod.GET)
    @ApiOperation(value = "根据影响行数查询mapper日志(巨延生)", notes = "根据影响行数查询mapper日志", response = BaseResult.class)
    public BaseResult pageMapperLogByInfluenceRow(@PathVariable(value = "pageMapperLogByInfluenceRow") Integer InfluenceRow,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                 @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                 @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (InfluenceRow == null || InfluenceRow <= 0) {
            LOGGER.error(LOG_PREFIX + "影响行数查询mapper日志输入错误 InfluenceRow = {}", InfluenceRow);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据影响行数查询mapper日志：" + InfluenceRow);
        return BaseResult.success(runMethodLogService.pageMapperLogByInfluenceRow(InfluenceRow,page,size,orderField,orderType));
    }
    /**
     * 根据方法参数模糊查询mapper日志
     *
     * @param methodParam
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/query/mapper/log/methodparam", method = RequestMethod.GET)
    @ApiOperation(value = "根据方法参数模糊查询mapper日志(巨延生)", notes = "根据方法参数模糊查询mapper日志", response = BaseResult.class)
    public BaseResult querymapperLogByMethodParam(@RequestParam(value = "methodParam") String methodParam,
                                                   @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                   @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                   @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (methodParam == null || "".equals(methodParam)) {
            LOGGER.error(LOG_PREFIX + "查询mapper日志方法参数输入错误 methodParam = {}", methodParam);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据方法参数模糊查询mapper日志：" + methodParam);
        return BaseResult.success(new PageInfo(runMethodLogService.pageMapperLogByMethodParam(methodParam, page, size, orderField, orderType)));
    }
    /**
     * 根据SQL语句模糊查询mapper日志
     *
     * @param SqlStatement
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/query/mapper/log/sqlStatement", method = RequestMethod.GET)
    @ApiOperation(value = "根据SQL语句模糊查询mapper日志(巨延生)", notes = "根据SQL语句模糊查询mapper日志", response = BaseResult.class)
    public BaseResult querymapperLogBySqlStatement(@RequestParam(value = "SqlStatement") String SqlStatement,
                                                   @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                   @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                   @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (SqlStatement == null || "".equals(SqlStatement)) {
            LOGGER.error(LOG_PREFIX + "查询mapper日志SQL语句输入错误 SqlStatement = {}", SqlStatement);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据SQL语句模糊查询mapper日志：" + SqlStatement);
        return BaseResult.success(new PageInfo(runMethodLogService.pageMapperLogBySqlStatement(SqlStatement, page, size, orderField, orderType)));
    }
    /**
     * 根据错误信息模糊查询mapper日志
     *
     * @param errorMessage
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/query/mapper/log/errormessage", method = RequestMethod.GET)
    @ApiOperation(value = "根据错误信息模糊查询mapper日志(巨延生)", notes = "根据错误信息模糊查询mapper日志", response = BaseResult.class)
    public BaseResult pageMapperLogByError(@RequestParam(value = "errorMessage") String errorMessage,
                                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                    @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                    @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (errorMessage == null || "".equals(errorMessage)) {
            LOGGER.error(LOG_PREFIX + "查询mapper日志错误信息输入错误 errorMessage = {}", errorMessage);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据错误信息模糊查询Service日志：" + errorMessage);
        return BaseResult.success(new PageInfo(runMethodLogService.pageMapperLogByError(errorMessage, page, size, orderField, orderType)));
    }

    /**
     * 根据执行状态查询mapper日志
     *
     * @param invokerStatus
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/status", method = RequestMethod.GET)
    @ApiOperation(value = "根据执行状态查询mapper日志(巨延生)", notes = "根据执行状态查询mapper日志", response = BaseResult.class)
    public BaseResult queryMapperLogByOperateType(@RequestParam(value = "invokerStatus") Byte invokerStatus,
                                                  @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                  @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                  @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (invokerStatus == null) {
            LOGGER.error(LOG_PREFIX + "查询mapper日志执行状态参数错误invokerStatus = {}", invokerStatus);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行状态查询mapper日志：" + invokerStatus);
        return BaseResult.success(new PageInfo(runMethodLogService.pageMapperLogByInvokeStatus(invokerStatus, page, size, orderField, orderType)));
    }

    /**
     * 根据方法名模糊查询mapper日志
     *
     * @param methodName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/methodname", method = RequestMethod.GET)
    @ApiOperation(value = "根据方法名模糊查询mapper日志(巨延生)", notes = "根据方法名模糊查询mapper日志", response = BaseResult.class)
    public BaseResult queryMapperLogByMethodName(@RequestParam(value = "methodName") String methodName,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                 @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                 @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (methodName == null || "".equals(methodName)) {
            LOGGER.error(LOG_PREFIX + "查询mapper日志方法名输入错误 methodName = {}", methodName);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据方法名查询mapper日志：" + methodName);
        return BaseResult.success(new PageInfo(runMethodLogService.pageMapperLogByMethodName(methodName, page, size, orderField, orderType)));
    }

    /**
     * 根据表名模糊查询mapper日志
     *
     * @param targetTableName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/TargetTableName", method = RequestMethod.GET)
    @ApiOperation(value = "根据表名模糊查询mapper日志(巨延生)", notes = "根据表名模糊查询mapper日志", response = BaseResult.class)
    public BaseResult queryMapperLogByTargetTableName(@RequestParam(value = "targetTableName") String targetTableName,
                                                      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                      @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                      @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (targetTableName == null || "".equals(targetTableName)) {
            LOGGER.error(LOG_PREFIX + "查询mapper日志方法名输入错误 methodName = {}", targetTableName);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据方法名查询mapper日志：" + targetTableName);
        return BaseResult.success(new PageInfo(runMethodLogService.pageMapperLogByTargetTableName(targetTableName, page, size, orderField, orderType)));
    }

    /**
     * 范围查询，根据执行时间查询beginTime到endTime中间的mapper日志
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/methodtime", method = RequestMethod.GET)
    @ApiOperation(value = "根据执行时间查询startTime到endTime中间的日志(巨延生)", notes = "根据执行时间查询startTime到endTime中间的日志", response = BaseResult.class)
    public BaseResult queryMapperLogByMethodTime(@RequestParam("startTime") Date startTime, @RequestParam(value = "endTime") Date endTime,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                                 @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                                 @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (startTime == null || endTime == null) {
            LOGGER.error(LOG_PREFIX + "查询mapper日志时间参数输入错误startTime = {} endTime = {} ", startTime, endTime);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行时间查询startTime到endTime中间的日志startTime = {} endTime = {} ", startTime, endTime);
        return BaseResult.success(new PageInfo(runMethodLogService.pageMapperLogByMethodTime(startTime, endTime, page, size, orderField, orderType)));
    }

    /**
     * 范围查询，根据方法耗时查询mapper日志
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/timecost", method = RequestMethod.GET)
    @ApiOperation(value = "根据执行时间查询startTime到endTime中间的日志(巨延生)", notes = "根据执行时间查询startTime到endTime中间的日志", response = BaseResult.class)
    public BaseResult queryMapperLogByTimeCost(@RequestParam("startTime") Long startTime, @RequestParam("endTime") Long endTime,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                               @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                               @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType) throws Exception {
        if (startTime == null || endTime == null) {
            LOGGER.error(LOG_PREFIX + "查询service日志时间参数输入错误startTime = {} endTime = {} ", startTime, endTime);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行时间查询startTime到endTime中间的日志startTime = {} endTime = {} ", startTime, endTime);
        return BaseResult.success(new PageInfo(runMethodLogService.pageMapperLogByTimeCost(startTime, endTime, page, size, orderField, orderType)));
    }
}
