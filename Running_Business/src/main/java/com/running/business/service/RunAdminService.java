package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunAdmin;

public interface RunAdminService {

	BaseResult<Object> addRunAdmin(RunAdmin admin);
	BaseResult<Object> updateRunAdmin(RunAdmin admin);
	BaseResult<Object> delRunAdminByID(Integer id);
	
	BaseResult<Object> getRunAdminByID(Integer id);
	BaseResult<Object> getAllRunAdmin();
	
}
