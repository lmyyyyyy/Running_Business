package com.running.business.service;

import java.util.List;

import com.running.business.pojo.RunUser;

public interface RunUserService {
	
	int addUser(RunUser user);
	void delUser(Integer uid);
	void updateUser(RunUser user);
	
	RunUser getRunUser(Integer id);
	RunUser getRunUser(String username, String password);
	List<RunUser> getAllRunUser();
	
}
