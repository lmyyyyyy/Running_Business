package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunAdmin;

public interface RunAdminService {

	BaseResult addRunAdmin(RunAdmin admin);
	BaseResult updateRunAdmin(RunAdmin admin);
	BaseResult delRunAdminByID(Integer id);
	
	BaseResult getRunAdminByID(Integer id);
	BaseResult getAllRunAdmin();
	
}
