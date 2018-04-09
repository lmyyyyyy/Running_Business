package com.running.business.controller;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunInfo;
import com.running.business.pojo.RunPushInfo;
import com.running.business.service.RunInfoService;
import com.running.business.service.RunPushInfoService;
import com.running.business.util.FileUploadUtil;
import com.running.business.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-29 上午11:59
 */
@RestController
@RequestMapping("/push")
@Api(value = "消息推送模块", tags = {"消息推送模块"})
public class RunInfoController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunInfoController.class);

    private static final String LOG_PREFIX = "【消息推送模块】 ";

    @Autowired
    private RunInfoService runInfoService;

    @Autowired
    private RunPushInfoService runPushInfoService;

    @Autowired
    private RequestUtil requestUtil;

    /**
     * 滚动播放消息
     *
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/rolling/info", method = RequestMethod.GET)
    @ApiOperation(value = "滚动播放消息(刘明宇)", notes = "滚动播放消息", response = BaseResult.class)
    public BaseResult rollInfo(HttpServletRequest request) throws AppException {
        LOGGER.info("{} 滚动播放消息", LOG_PREFIX);
        List<RunInfo> list;
        try {
            list = runInfoService.rollingInfo();
        } catch (AppException ae) {
            LOGGER.error("{} 滚动播放消息失败 error = {}", LOG_PREFIX, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(list);
    }

    /**
     * 根据id查询滚动信息
     *
     * @param id
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询滚动信息(刘明宇)", notes = "根据id查询滚动信息", response = BaseResult.class)
    public BaseResult queryInfoById(@PathVariable("id") Integer id, HttpServletRequest request) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("{} 根据id查询滚动信息 id = {}", LOG_PREFIX, id);
        RunInfo info = null;
        try {
            info = runInfoService.getRunInfoByID(id);
        } catch (AppException ae) {
            LOGGER.error("{} 根据id查询滚动信息失败 id = {}, error = {}", LOG_PREFIX, id, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(info);
    }


    /**
     * 分页获取滚动信息
     *
     * @param keyword
     * @param size
     * @param page
     * @param beginTime
     * @param endTime
     * @param orderField
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/info/keyword", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取滚动信息(刘明宇)", notes = "分页获取滚动信息", response = BaseResult.class)
    public BaseResult pageInfos(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(value = "beginTime", required = false, defaultValue = "0") Long beginTime,
                                @RequestParam(value = "endTime", required = false, defaultValue = "0") Long endTime,
                                @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                HttpServletRequest request) throws AppException {
        LOGGER.info("{} 分页获取滚动信息 keyword = {}, size = {}, page = {}", LOG_PREFIX, keyword, size, page);
        PageInfo<RunInfo> pageInfo;
        if (keyword == null || "".equals(keyword)) {
            pageInfo = runInfoService.pageRunInfo(size, page, beginTime, endTime, orderField, orderType);
        } else {
            pageInfo = runInfoService.pageRunInfoBykeyword(keyword, size, page, beginTime, endTime, orderField, orderType);
        }
        return BaseResult.success(pageInfo);
    }

    /**
     * 添加滚动信息
     *
     * @param info
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ApiOperation(value = "添加滚动信息(刘明宇)", notes = "添加滚动信息", response = BaseResult.class)
    public BaseResult saveInfo(@RequestBody RunInfo info, HttpServletRequest request) throws AppException {
        if (info == null) {
            throw new AppException(ResultEnum.INFO_IS_EMPTY);
        }
        LOGGER.info("{} 添加滚动信息 info = {}", LOG_PREFIX, info);
        try {
            runInfoService.saveRunInfo(info);
        } catch (AppException ae) {
            LOGGER.error("{} 添加滚动信息失败 info = {}", LOG_PREFIX, info);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 更新滚动信息
     *
     * @param info
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    @ApiOperation(value = "更新滚动信息(刘明宇)", notes = "更新滚动信息", response = BaseResult.class)
    public BaseResult updateInfo(@RequestBody RunInfo info, HttpServletRequest request) throws AppException {
        if (info == null) {
            throw new AppException(ResultEnum.INFO_IS_EMPTY);
        }
        LOGGER.info("{} 更新滚动信息 info = {}", LOG_PREFIX, info);
        try {
            runInfoService.updateRunInfo(info);
        } catch (AppException ae) {
            LOGGER.error("{} 更新滚动信息 info = {}", LOG_PREFIX, info);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 根据ID删除滚动信息
     *
     * @param id
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据ID删除滚动信息(刘明宇)", notes = "根据ID删除滚动信息", response = BaseResult.class)
    public BaseResult deleteInfo(@PathVariable("id") Integer id, HttpServletRequest request) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("{} 根据ID删除滚动信息 id = {}", LOG_PREFIX, id);
        try {
            runInfoService.deleteRunInfoByID(id);
        } catch (AppException ae) {
            LOGGER.error("{} 根据ID删除滚动信息失败 id = {}", LOG_PREFIX, id);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 删除所有滚动信息
     *
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/info", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除所有滚动信息(刘明宇)", notes = "删除所有滚动信息", response = BaseResult.class)
    public BaseResult deleteAllInfo(HttpServletRequest request) throws AppException {
        LOGGER.info("{} 删除所有滚动信息", LOG_PREFIX);
        try {
            runInfoService.deleteAllRunInfo();
        } catch (AppException ae) {
            LOGGER.error("{} 删除所有滚动信息失败", LOG_PREFIX);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 添加推送信息
     *
     * @param pushInfo
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加推送信息(刘明宇)", notes = "添加推送信息", response = BaseResult.class)
    public BaseResult save(@RequestBody RunPushInfo pushInfo, HttpServletRequest request) throws AppException {
        if (pushInfo == null) {
            return BaseResult.fail(ResultEnum.INFO_IS_EMPTY);
        }
        Integer id = requestUtil.getAdminId(request);
        LOGGER.info("{} 添加推送信息 id = {}", LOG_PREFIX, id);
        try {
            pushInfo.setOperator(id);
            //todo 添加推送图片
            //String filePath = FileUploadUtil.uploadFile(file, "pushPhoto");
            //pushInfo.setPushPhoto(filePath);
            runPushInfoService.saveRunPushInfo(pushInfo);
        } catch (AppException ae) {
            LOGGER.error("{} 添加推送信息失败 id = {}, info = {}, error = {}", LOG_PREFIX, id, pushInfo, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 更新推送信息
     *
     * @param pushInfo
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "更新推送信息(刘明宇)", notes = "更新推送信息", response = BaseResult.class)
    public BaseResult update(@RequestBody RunPushInfo pushInfo, HttpServletRequest request) throws AppException {
        if (pushInfo == null) {
            return BaseResult.fail(ResultEnum.INFO_IS_EMPTY);
        }
        Integer id = requestUtil.getAdminId(request);
        LOGGER.info("{} 更新推送信息 id = {}", LOG_PREFIX, id);
        try {
            pushInfo.setOperator(id);
            runPushInfoService.updatePushInfo(pushInfo);
        } catch (AppException ae) {
            LOGGER.error("{} 更新推送信息失败 id = {}, info = {}, error = {}", LOG_PREFIX, id, pushInfo, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 推送信息
     *
     * @param id
     * @param status
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "推送信息(刘明宇)", notes = "推送信息", response = BaseResult.class)
    public BaseResult push(@PathVariable("id") Integer id, @RequestParam(value = "status", required = true) Integer status, HttpServletRequest request) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.INFO_ID_IS_ERROR);
        }
        if (status == null || status < 0) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        Integer adminId = requestUtil.getAdminId(request);
        LOGGER.info("{} 推送信息 id = {}, adminId = {}", LOG_PREFIX, id, adminId);
        runPushInfoService.updateStatus(id, status, adminId);
        return BaseResult.success();
    }

    /**
     * 删除推送信息
     *
     * @param id
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除推送信息(刘明宇)", notes = "删除推送信息", response = BaseResult.class)
    public BaseResult deleteById(@PathVariable("id") Integer id, HttpServletRequest request) throws AppException {
        if (id == null || id <= 0) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        Integer adminId = requestUtil.getAdminId(request);
        LOGGER.info("{} 删除推送信息 id = {}, operatorId = {}", LOG_PREFIX, id, adminId);
        try {
            runPushInfoService.deletePushInfoByID(id);
        } catch (AppException ae) {
            LOGGER.error("{} 删除推送信息失败 id = {}, operatorId = {}, error = {}", LOG_PREFIX, id, adminId, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 根据id查询推送信息
     *
     * @param id
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询推送信息(刘明宇)", notes = "根据id查询推送信息", response = BaseResult.class)
    public BaseResult queryById(@PathVariable("id") Integer id, HttpServletRequest request) throws AppException {
        if (id == null || id <= 0) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        LOGGER.info("{} 根据id查询推送信息 id = {}", LOG_PREFIX, id);
        RunPushInfo pushInfo;
        try {
            pushInfo = runPushInfoService.getRunPushInfoByID(id);
        } catch (AppException ae) {
            LOGGER.error("{} 根据id查询推送信息失败 id = {}", LOG_PREFIX, id);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(pushInfo);
    }

    /**
     * 分页模糊查询推送信息
     *
     * @param keyword
     * @param page
     * @param size
     * @param beginTime
     * @param endTime
     * @param orderField
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    @ApiOperation(value = "分页模糊查询推送信息(刘明宇)", notes = "分页模糊查询推送信息", response = BaseResult.class)
    public BaseResult pageByKeyword(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                    @RequestParam(value = "status", required = false, defaultValue = "0") Integer status,
                                    @RequestParam(value = "beginTime", required = false, defaultValue = "0") Long beginTime,
                                    @RequestParam(value = "endTime", required = false, defaultValue = "0") Long endTime,
                                    @RequestParam(value = "orderField", required = false, defaultValue = "push_time") String orderField,
                                    @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                    HttpServletRequest request) throws AppException {
        LOGGER.info("{} 分页模糊查询推送信息 keyword = {}, page = {}, size = {}", LOG_PREFIX, keyword, page, size);
        PageInfo<RunPushInfo> pageInfo = runPushInfoService.pagePushInfoByKeyword(keyword, page, size, status, beginTime, endTime, orderField, orderType);
        return BaseResult.success(pageInfo);
    }
}
