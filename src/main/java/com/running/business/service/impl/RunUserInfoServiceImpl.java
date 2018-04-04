package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.enums.IsDeleteEnum;
import com.running.business.enums.PersonGenderEnum;
import com.running.business.enums.PersonStatusEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunUserInfoMapper;
import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserAddress;
import com.running.business.pojo.RunUserBalance;
import com.running.business.pojo.RunUserInfo;
import com.running.business.pojo.RunUserInfoExample;
import com.running.business.service.RunUserAddressService;
import com.running.business.service.RunUserBalanceService;
import com.running.business.service.RunUserInfoService;
import com.running.business.service.RunUserService;
import com.running.business.util.FileUploadUtil;
import com.running.business.util.ValidateUtil;
import com.running.business.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RunUserInfoServiceImpl implements RunUserInfoService {

    @Autowired
    private RunUserService runUserService;

    @Autowired
    private RunUserInfoMapper runUserInfoMapper;

    @Autowired
    private RunUserBalanceService runUserBalanceService;

    @Autowired
    private RunUserAddressService runUserAddressService;


    /**
     * 添加用户详细信息
     *
     * @param userInfo
     * @return
     * @throws AppException
     */
    @Override
    public void saveRunUserInfo(RunUserInfo userInfo) throws AppException {
        if (userInfo == null) {
            throw new AppException(ResultEnum.USER_INFO_ISEMPTY);
        }
        runUserInfoMapper.insert(userInfo);
    }

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     * @throws AppException
     */
    @Override
    public void updateRunUserInfo(RunUserInfo userInfo) throws AppException {
        if (userInfo == null) {
            throw new AppException(ResultEnum.USER_INFO_ISEMPTY);
        }
        runUserInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 根据用户id删除用户信息
     *
     * @param uid
     * @throws AppException
     */
    @Override
    public void deleteRunUserInfo(Integer uid) throws AppException {
        runUserInfoMapper.deleteByPrimaryKey(uid);
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public RunUserInfo getRunUserInfoById(Integer uid) throws AppException {
        RunUserInfo userInfo = runUserInfoMapper.selectByPrimaryKey(uid);

        return userInfo;
    }

    /**
     * 根据用户id获取UserVO
     *
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public UserVO getUserVOById(Integer uid) throws AppException {
        UserVO userVO;
        RunUserInfo userInfo = this.getRunUserInfoById(uid);
        userVO = this.convertVO(userInfo);
        return userVO;
    }

    /**
     * 获取所有用户信息
     *
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult getAllRunUserInfo() throws AppException {
        RunUserInfoExample example = new RunUserInfoExample();
        return BaseResult.success(runUserInfoMapper.selectByExample(example));
    }

    /**
     * 检查系统生成昵称的唯一性，true为不存在可以使用，false为已存在不可使用
     *
     * @param name
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkNameUnique(String name) throws AppException {
        RunUserInfoExample example = new RunUserInfoExample();
        RunUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo("游客_" + name);
        List<RunUserInfo> list = runUserInfoMapper.selectByExample(example);
        if (ValidateUtil.isValid(list)) {
            return false;
        }
        return true;
    }

    @Override
    public BaseResult uploadUserImg(MultipartFile file, Integer uid) {
        try {
            String filePath = FileUploadUtil.uploadFile(file, "userPhoto");
            RunUserInfo runUserInfo = runUserInfoMapper.selectByPrimaryKey(uid);
            runUserInfo.setUserPhoto(filePath);
            runUserInfoMapper.updateByPrimaryKeySelective(runUserInfo);
        } catch (Exception e) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    /**
     * 设置用户的默认地址
     *
     * @param uid
     * @param addrId
     * @throws AppException
     */
    @Override
    public void updateUserAddr(Integer uid, Integer addrId) throws AppException {
        RunUserInfo userInfo = new RunUserInfo();
        userInfo.setUid(uid);
        userInfo.setUserAddressId(addrId);
        runUserInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 将userInfo转换为UserVO
     *
     * @param userInfo
     * @return
     * @throws AppException
     */
    public UserVO convertVO(RunUserInfo userInfo) throws AppException {
        UserVO userVO = new UserVO();
        userVO.setUid(userInfo.getUid());
        userVO.setUserPhone(userInfo.getUserPhone());
        userVO.setUserAddress(userInfo.getUserAddressId());
        userVO.setUserGender(userInfo.getUserGender());
        userVO.setUserGenderDesc(PersonGenderEnum.getUserTypeEnum(userInfo.getUserGender()).getDesc());
        userVO.setUserName(userInfo.getUserName());
        userVO.setUserPhoto(userInfo.getUserPhoto());
        userVO.setUserPoint(userInfo.getUserPoint());

        RunUser user = (RunUser) runUserService.getRunUser(userInfo.getUid()).getData();
        if (user != null) {
            userVO.setUserphone(user.getUserphone());
            userVO.setUpdateTime(user.getUpdateTime());
            userVO.setAddTime(user.getAddTime());
            userVO.setUserStatus(user.getUserStatus());
            userVO.setUserStatusDesc(PersonStatusEnum.getUserTypeEnum(user.getUserStatus()).getDesc());
            userVO.setIsDelete(user.getIsDelete());
            userVO.setIsDeleteDesc(IsDeleteEnum.getUserTypeEnum(user.getIsDelete()).getDesc());
        }
        RunUserBalance balance = runUserBalanceService.getRunUserBalanceByUID(userInfo.getUid());
        if (balance != null) {
            userVO.setBalance(balance.getUserBalance());
        }
        RunUserAddress address = (RunUserAddress) runUserAddressService.getRunUserAddressByID(userInfo.getUserAddressId()).getData();
        if (address != null) {
            userVO.setUserAddressDesc(address.getUserAddress());
        }
        return userVO;
    }
}
