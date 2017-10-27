package com.running.business.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.pojo.RunUser;
import com.running.business.service.RunUserService;

@Controller
public class RunUserController {

	private Logger logger = LoggerFactory.getLogger(RunUserController.class);
	
	@Autowired
	private RunUserService runUserService;
	
	@RequestMapping("/check/{username}")
	@ResponseBody
	public Object checkData(@PathVariable String username, String callback) {
		logger.info("验证账号是否可用："+username);
		BaseResult<Object> result = null;
		//校验出错
		try {
			if (StringUtils.isBlank(username)) {
				result = BaseResult.fail(new ErrorMsg(ResultEnum.INPUT_ERROR.getCode(),ResultEnum.INPUT_ERROR.getMsg()));
			}
			if (null != result) {
				if (null != callback) {
					MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
					mappingJacksonValue.setJsonpFunction(callback);
					return mappingJacksonValue;
				} else {
					return result; 
				}
			}
			result = runUserService.checkUser(username);
		} catch (Exception e) {
			logger.info("验证失败");
		}
		if (null != callback) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return result; 
		}
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST, consumes=CodeConstants.AJC_UTF8, produces=CodeConstants.AJC_UTF8)
	@ResponseBody
	public BaseResult<Object> register(RunUser user) {
		logger.info("注册用户，账号为："+user.getUserUsername());
		BaseResult<Object> result = null;
		try {
			result = runUserService.checkUser(user.getUserUsername());
			if (result.getCode().equals("1")) {
				return result;
			}
			result = runUserService.addUser(user);
		} catch (Exception e) {
			logger.info("注册用户失败-" + e);
		}
		return result;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET, produces=CodeConstants.AJC_UTF8)
	@ResponseBody
	public BaseResult<Object> login(RunUser user, HttpServletRequest request, HttpServletResponse response) {
		logger.info("用户登录：" + user.getUserUsername());
		BaseResult<Object> result = null;
		try {
			if (user.getUserUsername() == null || user.getUserUsername().trim().equals("")
					|| user.getUserPassword() == null || user.getUserPassword().trim().equals("")) {
				result = BaseResult.fail(new ErrorMsg(ResultEnum.INPUT_ERROR.getCode(), ResultEnum.INPUT_ERROR.getMsg()));
				return result;
			}
			result = runUserService.login(user.getUserUsername(), user.getUserPassword(), request, response);
		} catch (Exception e) {
			logger.info("登录失败-"+e);
		}
		return result;
	}
	
	@RequestMapping(value = "/token/{token}", method=RequestMethod.POST, consumes=CodeConstants.AJC_UTF8, produces=CodeConstants.AJC_UTF8)
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		logger.info("根据token获取用户信息：",token);
		BaseResult<Object> result = null;
		try {
			result = runUserService.getUserByToken(token);
		} catch (Exception e) {
			logger.info("获取失败", e);
			return BaseResult.fail(new ErrorMsg(ResultEnum.INNER_ERROR.getCode(), ResultEnum.INNER_ERROR.getMsg()));
		}
		//判断是否为jsonp调用
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}
	
	@RequestMapping(value= "/logout/{token}",method=RequestMethod.POST, consumes=CodeConstants.AJC_UTF8, produces=CodeConstants.AJC_UTF8)
	@ResponseBody
	public Object logout(@PathVariable String token, HttpServletResponse response) throws IOException {
		logger.info("用户注销",token);
		String callback = "http://localhost:8080";
		BaseResult<Object> result = null;
		try {
			result = runUserService.logout(token);
		} catch (Exception e) {
			logger.info("用户注销失败",e);
		}
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			response.sendRedirect(callback);
			return null;
		}
	}
}
