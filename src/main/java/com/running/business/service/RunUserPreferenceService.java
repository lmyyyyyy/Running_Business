package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserPreference;

public interface RunUserPreferenceService {
	
	BaseResult saveRunUserPreference(RunUserPreference userpre);
	BaseResult updateRunUserPreference(RunUserPreference userpre);
	BaseResult deleteRunUserPreferenceByID(Integer id);
	BaseResult deleteAllRunUserPreferenceByUID(Integer uid);
	
	BaseResult getRunUserPreferenceByID(Integer id);
	BaseResult getAllUserPreferenceByUID(Integer uid);
	BaseResult getAllUserPreferenceByUIDAndType(Integer uid, String type);
}
