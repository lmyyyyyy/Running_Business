package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserPreference;

public interface RunUserPreferenceService {
	
	BaseResult<Object> addRunUserPreference(RunUserPreference userpre);
	BaseResult<Object> updateRunUserPreference(RunUserPreference userpre);
	BaseResult<Object> delRunUserPreferenceByID(Integer id);
	BaseResult<Object> delAllRunUserPreferenceByUID(Integer uid);
	
	BaseResult<Object> getRunUserPreferenceByID(Integer id);
	BaseResult<Object> getAllUserPreferenceByUID(Integer uid);
	BaseResult<Object> getAllUserPreferenceByUIDAndType(Integer uid, String type);
}
