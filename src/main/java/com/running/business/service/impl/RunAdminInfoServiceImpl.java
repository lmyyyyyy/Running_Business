package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunAdminInfoMapper;
import com.running.business.mapper.RunAdminMapper;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.pojo.RunAdminInfoExample;
import com.running.business.service.RunAdminInfoService;
import com.running.business.service.RunAdminService;
import com.running.business.util.ValidateUtil;
import com.running.business.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunAdminInfoServiceImpl implements RunAdminInfoService {

    @Autowired
    private RunAdminInfoMapper runAdminInfoMapper;

    @Autowired
    private RunAdminMapper runAdminMapper;

    @Autowired
    private RunAdminService runAdminService;

    /**
     * 添加管理员信息
     *
     * @param adminInfo
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult saveRunAdminInfo(RunAdminInfo adminInfo) throws AppException {
        if (adminInfo == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        runAdminInfoMapper.insert(adminInfo);
        return BaseResult.success();
    }

    /**
     * 更新管理员信息
     *
     * @param adminInfo
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult updateRunAdminInfo(RunAdminInfo adminInfo) throws AppException {
        if (adminInfo == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        runAdminInfoMapper.updateByPrimaryKey(adminInfo);
        return BaseResult.success(adminInfo);
    }

    /**
     * 根据id删除管理员信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult deleteRunAdminInfoByID(Integer id) throws AppException {
        RunAdminInfo adminInfo = runAdminInfoMapper.selectByPrimaryKey(id);
        if (adminInfo == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runAdminInfoMapper.deleteByPrimaryKey(id);
        return BaseResult.success(adminInfo);
    }

    /**
     * 根据Id获取管理员信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult getRunAdminInfoByID(Integer id) throws AppException {
        RunAdminInfo adminInfo = runAdminInfoMapper.selectByPrimaryKey(id);

        return BaseResult.success(adminInfo);
    }

    /**
     * 检查姓名是否唯一
     *
     * @param name
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkNameUnique(String name) throws AppException {
        if (name == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunAdminInfoExample example = new RunAdminInfoExample();
        RunAdminInfoExample.Criteria criteria = example.createCriteria();
        criteria.andAdminNameEqualTo("管理员_" + name);
        List<RunAdminInfo> list = runAdminInfoMapper.selectByExample(example);
        if (ValidateUtil.isValid(list)) {
            return false;
        }
        return true;
    }

    /**
     * 根据id获取AdminVO
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public AdminVO getAdminVOById(Integer id) throws AppException {
        return runAdminService.getAdminVOById(id);
    }

}
