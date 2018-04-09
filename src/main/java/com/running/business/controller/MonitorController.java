package com.running.business.controller;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.monitor.service.RunMethodLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.running.business.common.ResultEnum;

import java.util.Date;

/**
 * @author liumingyu
 * @create 2018-01-25 下午4:03
 */
@RestController
@RequestMapping("/monitor")
@Api(value = "监控平台模块", tags = {"监控平台模块"})
public class MonitorController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    private static final String LOG_PREFIX = "【监控平台模块】 ";

    @Autowired
    private RunMethodLogService runMethodLogService;

    /**
     * 根据id查询service日志
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/id" , method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询service日志" , notes = "根据id查询service日志", response = BaseResult.class )
    public BaseResult queryServiceLogById(Integer id) throws Exception {
        if (id == null || id <= 0) {
            LOGGER.error(LOG_PREFIX+"查询service日志id输入错误 id = {}",id);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据id查询service日志："+id);
        return BaseResult.success(runMethodLogService.queryServiceLogById(id));
    }

    /**
     * 根据操作者id查询service日志
     * @param oId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/oid" , method = RequestMethod.GET)
    @ApiOperation(value = "根据操作者id查询service日志" , notes = "根据操作者id查询service日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByOID (Integer oId) throws Exception {
        if (oId == null || oId <= 0) {
            LOGGER.error(LOG_PREFIX+"查询service日志oId输入错误 oId = {}",oId);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据操作者id查询service日志："+oId);
        return BaseResult.success(runMethodLogService.queryServiceLogByOID(oId));
    }

    /**
     * 根据执行状态查询service日志
     * @param invokerStatus
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/oid" , method = RequestMethod.GET)
    @ApiOperation(value = "根据执行状态查询service日志" , notes = "根据执行状态查询service日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByStatus(Integer invokerStatus) throws Exception{
        if (invokerStatus == null ) {
            LOGGER.error(LOG_PREFIX+"查询service日志执行状态参数错误invokerStatus = {}",invokerStatus);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行状态查询service日志："+invokerStatus);
        return BaseResult.success(runMethodLogService.queryServiceLogByStatus(invokerStatus));
    }

    /**
     * 根据父id查询service日志
     * @param pId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/pid" , method = RequestMethod.GET)
    @ApiOperation(value = "根据父id查询service日志" , notes = "根据父id查询service日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByPId(Integer pId) throws Exception{
        if (pId == null || pId <= 0) {
            LOGGER.error(LOG_PREFIX+"查询service日志pId输入错误 pId = {}",pId);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据父id查询service日志："+pId);
        return BaseResult.success(runMethodLogService.queryServiceLogByPID(pId));
    }

    /**
     * 根据类名模糊查询Service日志
     * @param className
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/classname" , method = RequestMethod.GET)
    @ApiOperation(value = "根据类名模糊查询Service日志" , notes = "根据类名模糊查询Service日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByClassName(String className) throws Exception{
        if (className == null || "".equals(className)) {
            LOGGER.error(LOG_PREFIX+"查询service日志类名输入错误 className = {}",className);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据类名查询service日志："+className);
        return BaseResult.success(runMethodLogService.queryServiceLogLikeClassName(className));
    }

    /**
     * 根据方法名模糊查询Service日志
     * @param methodName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/methodname" , method = RequestMethod.GET)
    @ApiOperation(value = "根据方法名模糊查询Service日志" , notes = "根据方法名模糊查询Service日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByMethodName(String methodName) throws Exception{
        if (methodName == null || "".equals(methodName)) {
            LOGGER.error(LOG_PREFIX+"查询service日志方法名输入错误 methodName = {}",methodName);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据方法名查询service日志："+methodName);
        return BaseResult.success(runMethodLogService.queryServiceLogLikeMethodName(methodName));
    }

    /**
     * 根据操作者姓名查询Service日志
     * @param operatorName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/operatorname" , method = RequestMethod.GET)
    @ApiOperation(value = "根据操作者姓名查询Service日志" , notes = "根据操作者姓名查询Service日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByOperatorName(String operatorName) throws Exception{
        if (operatorName == null || "".equals(operatorName)) {
            LOGGER.error(LOG_PREFIX+"查询service日志操作者姓名输入错误 operatorName = {}",operatorName);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据操作者姓名查询Service日志："+operatorName);
        return BaseResult.success(runMethodLogService.queryServiceLogLikeOperatorName(operatorName));
    }

    /**
     * 根据方法参数模糊查询Service日志
     *
     * @param methodParam
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/query/serviceLog/methodparam" , method = RequestMethod.GET)
    @ApiOperation(value = "根据方法参数模糊查询Service日志" , notes = "根据方法参数模糊查询Service日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByMethodParam(String methodParam) throws Exception{
        if (methodParam == null || "".equals(methodParam)) {
            LOGGER.error(LOG_PREFIX+"查询service日志方法参数输入错误 methodParam = {}",methodParam);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据方法参数模糊查询Service日志："+methodParam);
        return BaseResult.success(runMethodLogService.queryServiceLogLikeMethodParam(methodParam));
    }

    /**
     * 根据返回值模糊查询Service日志
     *
     * @param returnValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/returnvalue" , method = RequestMethod.GET)
    @ApiOperation(value = "根据返回值模糊查询Service日志" , notes = "根据返回值模糊查询Service日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByReturnValue(String returnValue) throws Exception{
        if (returnValue == null || "".equals(returnValue)) {
            LOGGER.error(LOG_PREFIX+"查询service日志返回值输入错误 returnValue = {}",returnValue);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据返回值模糊查询Service日志："+returnValue);
        return BaseResult.success(runMethodLogService.queryServiceLogLikeReturnValue(returnValue));
    }

    /**
     * 根据错误信息模糊查询Service日志
     *
     * @param errorMessage
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/query/serviceLog/errormessage" , method = RequestMethod.GET)
    @ApiOperation(value = "根据错误信息模糊查询Service日志" , notes = "根据错误信息模糊查询Service日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByErrorMessage(String errorMessage) throws Exception{
        if (errorMessage == null || "".equals(errorMessage)) {
            LOGGER.error(LOG_PREFIX+"查询service日志错误信息输入错误 errorMessage = {}",errorMessage);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据错误信息模糊查询Service日志："+errorMessage);
        return BaseResult.success(runMethodLogService.queryServiceLogLikeErrorMessage(errorMessage));
    }

    /**
     * 范围查询，根据执行时间查询startTime到endTime中间的日志
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/methodtime" , method = RequestMethod.GET)
    @ApiOperation(value = "根据执行时间查询startTime到endTime中间的日志" , notes = "根据执行时间查询startTime到endTime中间的日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByMethodTime(Long startTime , Long endTime) throws Exception{
        if (startTime == null || startTime < 0 || endTime < startTime || endTime  == null || endTime < 0) {
            LOGGER.error(LOG_PREFIX+"查询service日志时间参数输入错误startTime = {} endTime = {} " , startTime , endTime );
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行时间查询startTime到endTime中间的日志startTime = {} endTime = {} " , startTime , endTime );
        return BaseResult.success(runMethodLogService.queryServiceLogByMethodTime(startTime,endTime));
    }

    /**
     * 范围查询，根据执行时间查询beginTime到endTime中间的日志
     * @param beginTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/serviceLog/executiontime" , method = RequestMethod.GET)
    @ApiOperation(value = "根据执行时间查询startTime到endTime中间的日志" , notes = "根据执行时间查询startTime到endTime中间的日志" , response = BaseResult.class)
    public BaseResult queryServiceLogByExecutionTime(Date beginTime,Date endTime) throws Exception{
        if (beginTime == null || endTime  == null ) {
            LOGGER.error(LOG_PREFIX+"查询service日志时间参数输入错误beginTime = {} endTime = {} " , beginTime , endTime );
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行时间查询startTime到endTime中间的日志beginTime = {} endTime = {} " , beginTime , endTime );
        return BaseResult.success(runMethodLogService.queryServiceLogByExecutionTime(beginTime,endTime));
    }
    /**
     * 根据service方法id查询mapper日志
     * @param serviceLogId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/serviceLogId" , method = RequestMethod.GET)
    @ApiOperation(value = "根据service方法id查询mapper日志" , notes = "根据service方法id查询mapper日志", response = BaseResult.class )
    public BaseResult queryMapperLogByServiceId(Integer serviceLogId) throws Exception {
        if (serviceLogId == null || serviceLogId <= 0) {
            LOGGER.error(LOG_PREFIX+"service方法id查询mapper日志输入错误 id = {}",serviceLogId);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据service方法id查询mapper日志："+serviceLogId);
        return BaseResult.success(runMethodLogService.queryServiceLogById(serviceLogId));
    }
    /**
     * 根据执行状态查询mapper日志
     * @param invokerStatus
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/id" , method = RequestMethod.GET)
    @ApiOperation(value = "根据执行状态查询mapper日志" , notes = "根据执行状态查询mapper日志" , response = BaseResult.class)
    public BaseResult queryMapperLogByOperateType(Byte invokerStatus) throws Exception{
        if (invokerStatus == null ) {
            LOGGER.error(LOG_PREFIX+"查询mapper日志执行状态参数错误invokerStatus = {}",invokerStatus);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行状态查询mapper日志："+invokerStatus);
        return BaseResult.success(runMethodLogService.queryMapperLogByInvokeStatus(invokerStatus));
    }
    /**
     * 根据方法名模糊查询mapper日志
     * @param methodName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/methodname" , method = RequestMethod.GET)
    @ApiOperation(value = "根据方法名模糊查询mapper日志" , notes = "根据方法名模糊查询mapper日志" , response = BaseResult.class)
    public BaseResult queryMapperLogByMethodName(Byte methodName) throws Exception{
        if (methodName == null || "".equals(methodName)) {
            LOGGER.error(LOG_PREFIX+"查询mapper日志方法名输入错误 methodName = {}",methodName);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据方法名查询mapper日志："+methodName);
        return BaseResult.success(runMethodLogService.queryMapperLogByMethodName(methodName));
    }
    /**
     * 根据表名模糊查询mapper日志
     * @param TargetTableName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/TargetTableName" , method = RequestMethod.GET)
    @ApiOperation(value = "根据表名模糊查询mapper日志" , notes = "根据表名模糊查询mapper日志" , response = BaseResult.class)
    public BaseResult queryMapperLogByTargetTableName(String TargetTableName) throws Exception{
        if (TargetTableName == null || "".equals(TargetTableName)) {
            LOGGER.error(LOG_PREFIX+"查询mapper日志方法名输入错误 methodName = {}",TargetTableName);
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据方法名查询mapper日志："+TargetTableName);
        return BaseResult.success(runMethodLogService.queryMapperLogByTargetTableName(TargetTableName));
    }
    /**
     * 范围查询，根据方法耗时查询mapper日志
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/methodtime" , method = RequestMethod.GET)
    @ApiOperation(value = "根据执行时间查询startTime到endTime中间的日志" , notes = "根据执行时间查询startTime到endTime中间的日志" , response = BaseResult.class)
    public BaseResult queryMapperLogByMethodTime(Date startTime , Date endTime) throws Exception{
        if (startTime == null   || endTime  == null ) {
            LOGGER.error(LOG_PREFIX+"查询mapper日志时间参数输入错误startTime = {} endTime = {} " , startTime , endTime );
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行时间查询startTime到endTime中间的日志startTime = {} endTime = {} " , startTime , endTime );
        return BaseResult.success(runMethodLogService.queryMapperLogByMethodTime(startTime,endTime));
    }
    /**
     * 范围查询，根据执行时间查询beginTime到endTime中间的mapper日志
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/query/mapperLog/methodtime" , method = RequestMethod.GET)
    @ApiOperation(value = "根据执行时间查询startTime到endTime中间的日志" , notes = "根据执行时间查询startTime到endTime中间的日志" , response = BaseResult.class)
    public BaseResult queryMapperLogByTimeCost(Long startTime , Long endTime) throws Exception{
        if (startTime == null   || endTime  == null ) {
            LOGGER.error(LOG_PREFIX+"查询service日志时间参数输入错误startTime = {} endTime = {} " , startTime , endTime );
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("根据执行时间查询startTime到endTime中间的日志startTime = {} endTime = {} " , startTime , endTime );
        return BaseResult.success(runMethodLogService.queryMapperLogByTimeCost(startTime,endTime));
    }
}
