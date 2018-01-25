package com.running.business.service.impl;

import com.running.business.common.ResultEnum;
import com.running.business.dto.UserDTO;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunAdminInfoMapper;
import com.running.business.mapper.RunAdminMapper;
import com.running.business.mapper.RunDeliveryInfoMapper;
import com.running.business.mapper.RunDeliveryuserMapper;
import com.running.business.mapper.RunUserInfoMapper;
import com.running.business.mapper.RunUserMapper;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunAdminExample;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunDeliveryuser;
import com.running.business.pojo.RunDeliveryuserExample;
import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserExample;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.SSOService;
import com.running.business.util.RequestUtil;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-21 下午7:17
 */
@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private RunUserMapper runUserMapper;

    @Autowired
    private RunUserInfoMapper runUserInfoMapper;

    @Autowired
    private RunDeliveryuserMapper runDeliveryuserMapper;

    @Autowired
    private RunDeliveryInfoMapper runDeliveryInfoMapper;

    @Autowired
    private RunAdminMapper runAdminMapper;

    @Autowired
    private RunAdminInfoMapper runAdminInfoMapper;

    @Autowired
    private RequestUtil requestUtil;
    /**
     * 获取UserDTO
     *
     * @param request
     * @return
     * @throws AppException
     */
    @Override
    public UserDTO getUserDTO(HttpServletRequest request) throws AppException {
        String token = requestUtil.getTokenStr(request);
        UserDTO userDTO;
        String userType = token.substring(0, 2);
        switch (userType) {
            case "RU":
                userDTO = getRunUser(request);
                //更新用户登录状态
                updateUserStatus(userDTO.getId());
                break;
            case "RD":
                userDTO = getRunDelivery(request);
                //更新配送员登录状态
                updateDeliveryStatus(userDTO.getId());
                break;
            case "RA":
                userDTO = getRunAdmin(request);
                //更新管理员登录状态
                updateAdminStatus(userDTO.getId());
                break;
            default:
                userDTO = null;
                break;
        }
        return userDTO;
    }

    /**
     * 获取用户UserDTO
     *
     * @param request
     * @return
     * @throws AppException
     */
    @Override
    public UserDTO getRunUser(HttpServletRequest request) throws AppException {
        Integer uid = requestUtil.getUserId(request);
        if (checkUserIsDel(uid)) {
            throw new AppException(ResultEnum.USER_IS_DELETE);
        }
        RunUser user = runUserMapper.selectByPrimaryKey(uid);
        if (user == null) {
            throw new AppException(ResultEnum.USER_INFO_ISEMPTY);
        }
        RunUserInfo info = runUserInfoMapper.selectByPrimaryKey(uid);
        if (info == null) {
            throw new AppException(ResultEnum.USER_INFO_ISEMPTY);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(uid);
        userDTO.setName(info.getUserName());
        userDTO.setPhone(user.getUserphone());
        userDTO.setStatus(user.getUserStatus());
        userDTO.setUserType(UserTypeEnum.USER.getCode());
        return userDTO;
    }

    /**
     * 获取配送员UserDTO
     *
     * @param request
     * @return
     * @throws AppException
     */
    @Override
    public UserDTO getRunDelivery(HttpServletRequest request) throws AppException {
        Integer did = requestUtil.getDeliveryId(request);
        if (checkDeliveryIsDel(did)) {
            throw new AppException(ResultEnum.DELIVERY_IS_DELETE);
        }
        RunDeliveryuser deliveryuser = runDeliveryuserMapper.selectByPrimaryKey(did);
        if (deliveryuser == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        RunDeliveryInfo runDeliveryInfo = runDeliveryInfoMapper.selectByPrimaryKey(did);
        if (runDeliveryInfo == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(did);
        userDTO.setName(runDeliveryInfo.getName());
        userDTO.setPhone(deliveryuser.getUserphone());
        userDTO.setStatus(deliveryuser.getStatus());
        userDTO.setUserType(UserTypeEnum.DELIVERY.getCode());
        return userDTO;
    }

    /**
     * 获取管理员UserDTO
     *
     * @param request
     * @return
     * @throws AppException
     */
    @Override
    public UserDTO getRunAdmin(HttpServletRequest request) throws AppException {
        Integer id = requestUtil.getAdminId(request);
        if (checkAdminIsDel(id)) {
            throw new AppException(ResultEnum.ADMIN_IS_DELETE);
        }
        if (id == null) {
            throw new AppException(ResultEnum.ADMIN_ID_ERROR);
        }
        RunAdmin runAdmin = runAdminMapper.selectByPrimaryKey(id);
        if (runAdmin == null) {
            throw new AppException(ResultEnum.ADMIN_INFO_IS_EMPTY);
        }
        RunAdminInfo runAdminInfo = runAdminInfoMapper.selectByPrimaryKey(id);
        if (runAdminInfo == null) {
            throw new AppException(ResultEnum.ADMIN_INFO_IS_EMPTY);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName(runAdminInfo.getAdminName());
        userDTO.setPhone(runAdminInfo.getAdminPhone());
        userDTO.setStatus(runAdmin.getStatus());
        userDTO.setUserType(UserTypeEnum.ADMIN.getCode());
        return userDTO;
    }

    /**
     * 更新用户状态
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void updateUserStatus(Integer id) throws AppException {
        RunUserExample example = new RunUserExample();
        RunUserExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(id);
        criteria.andUserStatusEqualTo(true);
        criteria.andIsDeleteEqualTo(false);
        List<RunUser> list = runUserMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return;
        }
        RunUser user = new RunUser();
        user.setUid(id);
        user.setUserStatus(true);
        runUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 更新用户状态
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void updateDeliveryStatus(Integer id) throws AppException {
        RunDeliveryuserExample example = new RunDeliveryuserExample();
        RunDeliveryuserExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(id);
        criteria.andStatusEqualTo(true);
        criteria.andIsDeleteEqualTo(false);
        List<RunDeliveryuser> list = runDeliveryuserMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return;
        }
        RunDeliveryuser deliveryuser = new RunDeliveryuser();
        deliveryuser.setDid(id);
        deliveryuser.setStatus(true);
        runDeliveryuserMapper.updateByPrimaryKeySelective(deliveryuser);
    }

    /**
     * 更新用户状态
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void updateAdminStatus(Integer id) throws AppException {
        RunAdminExample example = new RunAdminExample();
        RunAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminIdEqualTo(id);
        criteria.andStatusEqualTo(true);
        criteria.andIsDeleteEqualTo(false);
        List<RunAdmin> list = runAdminMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return;
        }
        RunAdmin admin = new RunAdmin();
        admin.setAdminId(id);
        admin.setStatus(true);
        runAdminMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * 检查用户是否已被删除 true：已删，false：未删
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkUserIsDel(Integer id) throws AppException {
        RunUserExample example = new RunUserExample();
        RunUserExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(id);
        criteria.andIsDeleteEqualTo(true);
        List<RunUser> list = runUserMapper.selectByExample(example);
        return ValidateUtil.isValid(list);
    }
    /**
     * 检查配送员是否已被删除 true：已删，false：未删
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkDeliveryIsDel(Integer id) throws AppException {
        RunDeliveryuserExample example = new RunDeliveryuserExample();
        RunDeliveryuserExample.Criteria criteria = example.createCriteria();
        criteria.andDidEqualTo(id);
        criteria.andIsDeleteEqualTo(true);
        List<RunDeliveryuser> list = runDeliveryuserMapper.selectByExample(example);
        return ValidateUtil.isValid(list);
    }
    /**
     * 检查管理员是否已被删除 true：已删，false：未删
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkAdminIsDel(Integer id) throws AppException {
        RunAdminExample example = new RunAdminExample();
        RunAdminExample.Criteria criteria = example.createCriteria();
        criteria.andAdminIdEqualTo(id);
        criteria.andIsDeleteEqualTo(true);
        List<RunAdmin> list = runAdminMapper.selectByExample(example);
        return ValidateUtil.isValid(list);
    }
}
