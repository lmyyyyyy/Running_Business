package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserPreference;

public interface RunUserPreferenceService {
	
	BaseResult addRunUserPreference(RunUserPreference userpre);
	BaseResult updateRunUserPreference(RunUserPreference userpre);
	BaseResult delRunUserPreferenceByID(Integer id);
	BaseResult delAllRunUserPreferenceByUID(Integer uid);
	
	BaseResult getRunUserPreferenceByID(Integer id);
	BaseResult getAllUserPreferenceByUID(Integer uid);
	BaseResult getAllUserPreferenceByUIDAndType(Integer uid, String type);
}
