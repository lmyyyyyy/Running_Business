package com.running.business.service.impl;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunUserPreferenceMapper;
import com.running.business.pojo.RunUserPreference;
import com.running.business.pojo.RunUserPreferenceExample;
import com.running.business.pojo.RunUserPreferenceExample.Criteria;
import com.running.business.service.RunUserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunUserPreferenceServiceImpl implements RunUserPreferenceService {

    @Autowired
    private RunUserPreferenceMapper runUserPreferenceMapper;

    /**
     * 保存用户偏好
     *
     * @param userpre
     * @throws AppException
     */
    @Override
    public void saveRunUserPreference(RunUserPreference userpre) throws AppException {
        if (userpre == null) {
            throw new AppException(ResultEnum.USER_PREFERENCE_INFO_EMTPY);
        }
        runUserPreferenceMapper.insert(userpre);
    }

    /**
     * 更新用户偏好
     *
     * @param userpre
     * @throws AppException
     */
    @Override
    public void updateRunUserPreference(RunUserPreference userpre) throws AppException {
        if (userpre == null) {
            throw new AppException(ResultEnum.USER_PREFERENCE_INFO_EMTPY);
        }
        runUserPreferenceMapper.updateByPrimaryKeySelective(userpre);
    }

    /**
     * 根据id删除偏好
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void deleteRunUserPreferenceByID(Integer id) throws AppException {
        RunUserPreference userpre = runUserPreferenceMapper.selectByPrimaryKey(id);
        if (userpre == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runUserPreferenceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据用户id删除偏好
     *
     * @param uid
     * @throws AppException
     */
    @Override
    public void deleteAllRunUserPreferenceByUID(Integer uid) throws AppException {
        RunUserPreferenceExample example = new RunUserPreferenceExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        runUserPreferenceMapper.deleteByExample(example);
    }

    /**
     * 根据id获取偏好
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public RunUserPreference getRunUserPreferenceByID(Integer id) throws AppException {
        RunUserPreference userpre = runUserPreferenceMapper.selectByPrimaryKey(id);
        if (userpre == null) {
            throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
        }
        return userpre;
    }

    /**
     * 根据用户id获取所有偏好
     *
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public List<RunUserPreference> getAllUserPreferenceByUID(Integer uid) throws AppException {
        RunUserPreferenceExample example = new RunUserPreferenceExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        List<RunUserPreference> list = runUserPreferenceMapper.selectByExample(example);
        return list;
    }

    /**
     * 根据用户id和类型获取所有偏好
     *
     * @param uid
     * @param type
     * @return
     * @throws AppException
     */
    @Override
    public List<RunUserPreference> getAllUserPreferenceByUIDAndType(Integer uid, String type) throws AppException {
        RunUserPreferenceExample example = new RunUserPreferenceExample();
        Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        criteria.andUserGoodstypeEqualTo(type);
        List<RunUserPreference> list = runUserPreferenceMapper.selectByExample(example);
        return list;
    }

}
