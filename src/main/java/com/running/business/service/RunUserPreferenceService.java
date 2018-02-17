package com.running.business.service;

import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserPreference;

import java.util.List;

public interface RunUserPreferenceService {

    /**
     * 保存用户偏好
     *
     * @param userpre
     * @throws AppException
     */
    void saveRunUserPreference(RunUserPreference userpre) throws AppException;

    /**
     * 更新用户偏好
     *
     * @param userpre
     * @throws AppException
     */
    void updateRunUserPreference(RunUserPreference userpre) throws AppException;

    /**
     * 根据id删除偏好
     *
     * @param id
     * @throws AppException
     */
    void deleteRunUserPreferenceByID(Integer id) throws AppException;

    /**
     * 根据用户id删除偏好
     *
     * @param uid
     * @throws AppException
     */
    void deleteAllRunUserPreferenceByUID(Integer uid) throws AppException;

    /**
     * 根据id获取偏好
     *
     * @param id
     * @return
     * @throws AppException
     */
    RunUserPreference getRunUserPreferenceByID(Integer id) throws AppException;

    /**
     * 根据用户id获取所有偏好
     *
     * @param uid
     * @return
     * @throws AppException
     */
    List<RunUserPreference> getAllUserPreferenceByUID(Integer uid) throws AppException;

    /**
     * 根据用户id和类型获取所有偏好
     *
     * @param uid
     * @param type
     * @return
     * @throws AppException
     */
    List<RunUserPreference> getAllUserPreferenceByUIDAndType(Integer uid, String type) throws AppException;
}
