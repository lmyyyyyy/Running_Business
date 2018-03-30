package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.Config;
import com.running.business.common.ResultEnum;
import com.running.business.dto.UserDTO;
import com.running.business.enums.AvailableEnum;
import com.running.business.enums.IsDeleteEnum;
import com.running.business.enums.PersonGenderEnum;
import com.running.business.enums.PersonStatusEnum;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.JedisClient;
import com.running.business.mapper.RunDeliveryuserMapper;
import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryBalance;
import com.running.business.pojo.RunDeliveryDistance;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunDeliveryuser;
import com.running.business.pojo.RunDeliveryuserExample;
import com.running.business.pojo.RunDeliveryuserExample.Criteria;
import com.running.business.service.RunDeliveryAddressService;
import com.running.business.service.RunDeliveryBalanceRecordService;
import com.running.business.service.RunDeliveryBalanceService;
import com.running.business.service.RunDeliveryDistanceService;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunDeliveryuserService;
import com.running.business.service.RunOrderService;
import com.running.business.util.CookieUtils;
import com.running.business.util.FileUploadUtil;
import com.running.business.util.JsonUtils;
import com.running.business.util.Run_StringUtil;
import com.running.business.util.UserUtil;
import com.running.business.util.ValidateUtil;
import com.running.business.vo.DeliveryDetailVO;
import com.running.business.vo.DeliveryVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RunDeliveryuserServiceImpl implements RunDeliveryuserService {

    @Autowired
    private RunDeliveryuserMapper runDeliveryuserMapper;

    @Autowired
    private RunDeliveryInfoService runDeliveryInfoService;

    @Autowired
    private RunDeliveryAddressService runDeliveryAddressService;

    @Autowired
    private RunDeliveryBalanceService runDeliveryBalanceService;

    @Autowired
    private RunDeliveryDistanceService runDeliveryDistanceService;

    @Autowired
    private RunDeliveryBalanceRecordService runDeliveryBalanceRecordService;

    @Autowired
    private RunOrderService runOrderService;

    /**
     * 连接redis
     */
    @Autowired
    private JedisClient jedisClient;
    /**
     * 用户token键
     */
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    /**
     * session生命周期
     */
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    /**
     * 添加配送员
     *
     * @param user
     * @return
     */
    @Override
    public Integer saveRunDeliveryuser(RunDeliveryuser user) throws AppException {
        if (user == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        user.setPassword(Run_StringUtil.MD5(user.getPassword()));
        user.setAddTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(false);
        user.setIsDelete(false);
        user.setAvailable(false);
        user.setCredits(0);
        user.setReviewPhoto(null);
        Integer did = runDeliveryuserMapper.insert(user);
        return did;
    }

    /**
     * 根据id删除配送员
     *
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public void deleteRunDeliveryuser(Integer uid) throws AppException {
        RunDeliveryuser user = runDeliveryuserMapper.selectByPrimaryKey(uid);
        if (user == null) {
            throw new AppException(ResultEnum.INPUT_ERROR.getCode(), ResultEnum.INPUT_ERROR.getMsg());
        }
        user.setIsDelete(true);
        user.setUpdateTime(new Date());
        runDeliveryuserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 配送员修改密码
     *
     * @param user
     * @return
     * @throws AppException
     */
    @Override
    public void updateRunDeliveryuserPassword(RunDeliveryuser user) throws AppException {
        if (user == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        user.setPassword(Run_StringUtil.MD5(user.getPassword()));
        user.setUpdateTime(new Date());
        runDeliveryuserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 更新配送员信息
     *
     * @param runDeliveryuser
     * @throws AppException
     */
    @Override
    public void updateRunDeliveryuser(RunDeliveryuser runDeliveryuser) throws AppException {
        if (runDeliveryuser == null || runDeliveryuser.getDid() == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        runDeliveryuser.setUpdateTime(new Date());
        runDeliveryuserMapper.updateByPrimaryKeySelective(runDeliveryuser);
    }

    /**
     * 根据用户id集合更新管理员登录状态
     *
     * @param userIds
     * @throws AppException
     */
    @Override
    public void updateDeliveryListStatus(Set<Integer> userIds) throws AppException {
        if (userIds == null || userIds.size() == 0) {
            return;
        }
        for (Integer userId : userIds) {
            RunDeliveryuser user = new RunDeliveryuser();
            user.setDid(userId);
            user.setUpdateTime(new Date());
            user.setStatus(false);
            runDeliveryuserMapper.updateByPrimaryKeySelective(user);
        }

    }

    /**
     * 检查账号是否被注册 true:可用没被删除 false:不可用／注册过
     *
     * @param userphone
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkRunDeliveryuser(String userphone) throws AppException {
        if (userphone == null || "".equals(userphone)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunDeliveryuserExample example = new RunDeliveryuserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserphoneEqualTo(userphone);
        criteria.andIsDeleteEqualTo(false);
        List<RunDeliveryuser> list = runDeliveryuserMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            return true;
        }
        return false;
    }

    /**
     * 根据id获取配送员账号信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public RunDeliveryuser getRunDeliveryuserById(Integer id) throws AppException {
        if (id == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunDeliveryuser user = runDeliveryuserMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new AppException(ResultEnum.QUERY_ERROR);
        }
        return user;
    }

    /**
     * 配送员登录
     *
     * @param phone
     * @param password
     * @param request
     * @param response
     * @return
     * @throws AppException
     */
    @Override
    public String login(String phone, String password, HttpServletRequest request, HttpServletResponse response) throws AppException {
        if (checkRunDeliveryuser(phone)) {
            throw new AppException(ResultEnum.TELTPHONE_DELIVERY_ERROR);
        }
        List<RunDeliveryuser> list;
        RunDeliveryuserExample example = new RunDeliveryuserExample();
        Criteria criteria1 = example.createCriteria();
        criteria1.andUserphoneEqualTo(phone);
        criteria1.andPasswordEqualTo(Run_StringUtil.MD5(password));
        list = runDeliveryuserMapper.selectByExample(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.TELTPHONE_DELIVERY_ERROR);
        }
        RunDeliveryuser deliveryuser = list.get(0);
        if (deliveryuser == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        if (!deliveryuser.getAvailable()) {
            throw new AppException(ResultEnum.DELIVERY_IS_NOT_AVAILABLE);
        }
        if (deliveryuser.getCredits() <= 0) {
            throw new AppException(ResultEnum.DELIVERY_CREDITS_IS_TOO_LOW);
        }
        deliveryuser.setStatus(true);
        deliveryuser.setUpdateTime(new Date());
        runDeliveryuserMapper.updateByPrimaryKeySelective(deliveryuser);
        //生成token
        String token = "RD" + UUID.randomUUID().toString();
        //保存用户之前，将用户对象中的密码清空
        deliveryuser.setPassword(null);
        //把用户信息写入redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(deliveryuser));
        //设置sesison的生命周期
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        //添加cookie
        CookieUtils.setCookie(request, response, "RUN_TOKEN", token);
        RunDeliveryInfo deliveryInfo = runDeliveryInfoService.getRunDeliveryInfoByID(deliveryuser.getDid());
        if (deliveryInfo == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(deliveryuser.getDid());
        userDTO.setName(deliveryInfo.getName());
        userDTO.setPhone(deliveryuser.getUserphone());
        userDTO.setStatus(deliveryuser.getStatus());
        userDTO.setUserType(UserTypeEnum.DELIVERY.getCode());
        UserUtil.bind(userDTO);
        return token;
    }

    /**
     * 分页获取配送员列表
     *
     * @param status
     * @param isDelete
     * @param able
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public PageInfo<DeliveryDetailVO> pageAllRunDeliveryuser(Boolean status, Boolean isDelete, Boolean able, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "add_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        List<RunDeliveryuser> list;
        RunDeliveryuserExample example = new RunDeliveryuserExample();
        example.setOrderByClause(orderField + " " + orderType);
        Criteria criteria = example.createCriteria();
        if (isDelete != null) {
            criteria.andIsDeleteEqualTo(isDelete);
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        if (able != null) {
            criteria.andAvailableEqualTo(able);
        }
        PageHelper.startPage(page, size);
        list = runDeliveryuserMapper.selectByExample(example);
        List<DeliveryDetailVO> vos = this.convertDeliverys2VOs(list);
        return new PageInfo<>(vos);
    }

    /**
     * 分页获取禁用且正在审核的配送员列表
     *
     * @param page
     * @param size
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public PageInfo<RunDeliveryuser> pageAbleRunDeliveryuser(Integer page, Integer size, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "ASC";
        }
        List<RunDeliveryuser> list;
        RunDeliveryuserExample example = new RunDeliveryuserExample();
        example.setOrderByClause("add_time " + orderType);
        Criteria criteria = example.createCriteria();
        criteria.andAvailableEqualTo(false);
        criteria.andIsDeleteEqualTo(false);
        criteria.andReviewPhotoIsNotNull();
        PageHelper.startPage(page, size);
        list = runDeliveryuserMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 检查账号和密码是否匹配 true:匹配 false:不匹配
     *
     * @param phone
     * @param password
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkPhoneAndPwd(String phone, String password) throws AppException {
        if (phone == null || password == null || phone.equals("") || password.equals("")) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunDeliveryuserExample example = new RunDeliveryuserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUserphoneEqualTo(phone);
        criteria.andPasswordEqualTo(Run_StringUtil.MD5(password));
        List<RunDeliveryuser> list = runDeliveryuserMapper.selectByExample(example);
        if (ValidateUtil.isValid(list)) {
            return true;
        }
        return false;
    }

    /**
     * 根据token从redis中查询配送员信息
     *
     * @param token token码
     * @return 如何为空则返回会话超时，否则更新生命周期，将用户返回
     */
    @Override
    public BaseResult getDeliveryuserByToken(String token) throws AppException {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);

        //判断是否为空
        if (StringUtils.isBlank(json)) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME.getCode(), ResultEnum.SESSION_IS_OUT_TIME.getMsg());
        }
        //更新生命周期
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        return BaseResult.success(JsonUtils.jsonToPojo(json, RunDeliveryuser.class));
    }

    /**
     * 注销用户，根据token从redis中查询配送员信息
     *
     * @param token token码
     * @return 如果token为空，说明会话超时，否则将该token删除，返回用户实体
     */
    @Override
    public BaseResult logout(String token) throws AppException {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        //判断是否为空
        if (StringUtils.isBlank(json)) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME.getCode(), ResultEnum.SESSION_IS_OUT_TIME.getMsg());
        }
        //更新生命周期
        jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
        RunDeliveryuser deliveryuser = JsonUtils.jsonToPojo(json, RunDeliveryuser.class);
        if (deliveryuser == null) {
            throw new AppException(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        //登录人员列表中去除当前正常退出人员
        jedisClient.srem(Config.LOGIN_DELIVERY_IDS_KEY, String.valueOf(deliveryuser.getDid()));
        deliveryuser.setUpdateTime(new Date());
        deliveryuser.setStatus(false);
        runDeliveryuserMapper.updateByPrimaryKeySelective(deliveryuser);
        return BaseResult.success(deliveryuser);
    }

    /**
     * 根据状态获取所有未被删除未被禁用的配送员
     *
     * @param status
     * @return
     * @throws AppException
     */
    @Override
    public List<RunDeliveryuser> getRunDeliveryuserByStatus(boolean status) throws AppException {
        RunDeliveryuserExample example = new RunDeliveryuserExample();
        Criteria criteria = example.createCriteria();
        criteria.andAvailableEqualTo(true);
        criteria.andIsDeleteEqualTo(false);
        criteria.andStatusEqualTo(status);
        List<RunDeliveryuser> deliveryusers = runDeliveryuserMapper.selectByExample(example);
        return deliveryusers;
    }

    /**
     * 根据id集合查询当前在线的配送员集合
     *
     * @param ids
     * @return
     * @throws AppException
     */
    @Override
    public Set<Integer> queryDeliverysByIds(Set<Integer> ids) throws AppException {
        if (ids.isEmpty()) {
            return null;
        }
        RunDeliveryuserExample example = new RunDeliveryuserExample();
        Criteria criteria = example.createCriteria();
        criteria.andDidIn(new ArrayList<>(ids));
        criteria.andStatusEqualTo(true);
        example.setOrderByClause(" update_time desc");
        List<RunDeliveryuser> list = runDeliveryuserMapper.selectByExample(example);
        return list.stream().map(user -> user.getDid()).collect(Collectors.toSet());
    }

    /**
     * 上传配送员身份证
     *
     * @param file
     * @param did
     * @return
     */
    @Override
    public BaseResult uploadDeliveryCard(MultipartFile file, Integer did) {
        try {
            String filePath = FileUploadUtil.uploadFile(file, "reviewPhoto");
            RunDeliveryuser runDeliveryuser = runDeliveryuserMapper.selectByPrimaryKey(did);
            runDeliveryuser.setReviewPhoto(filePath);
            runDeliveryuserMapper.updateByPrimaryKeySelective(runDeliveryuser);
        } catch (Exception e) {
            return BaseResult.fail("配送员身份证图片上传异常");
        }
        return BaseResult.success(ResultEnum.DELIVERY_CHECKING);
    }

    /**
     * 检查配送员是否是可用状态
     *
     * @param did
     * @return
     * @throws AppException
     */
    @Override
    public boolean checkIsAble(Integer did) throws AppException {
        RunDeliveryuser deliveryuser = runDeliveryuserMapper.selectByPrimaryKey(did);
        if (deliveryuser == null) {
            throw new AppException(ResultEnum.DELIVERY_ID_IS_ERROR);
        }
        return deliveryuser.getAvailable();
    }

    /**
     * 禁用／启用 配送员
     *
     * @param did
     * @param available
     * @throws AppException
     */
    @Override
    public void updateAvailable(Integer did, boolean available) throws AppException {
        RunDeliveryuser deliveryuser = new RunDeliveryuser();
        deliveryuser.setDid(did);
        deliveryuser.setAvailable(available);
        runDeliveryuserMapper.updateByPrimaryKeySelective(deliveryuser);
    }

    /**
     * 根据配送员id查询普通VO
     *
     * @param did
     * @return
     * @throws AppException
     */
    @Override
    public DeliveryDetailVO queryDeliveryVO(Integer did) throws AppException {
        RunDeliveryuser deliveryuser = runDeliveryuserMapper.selectByPrimaryKey(did);
        if (deliveryuser == null) {
            throw new AppException(ResultEnum.DELIVERY_ID_IS_ERROR);
        }
        return this.convertDelivery2VO(deliveryuser);
    }

    /**
     * 根据配送员id查询详细VO
     *
     * @param did
     * @return
     * @throws AppException
     */
    @Override
    public DeliveryDetailVO queryDeliveryDetailVO(Integer did) throws AppException {
        RunDeliveryuser deliveryuser = runDeliveryuserMapper.selectByPrimaryKey(did);
        if (deliveryuser == null) {
            throw new AppException(ResultEnum.DELIVERY_ID_IS_ERROR);
        }
        DeliveryDetailVO vo = this.convertDelivery2VO(deliveryuser);
        vo = this.fillDeliveryVO(vo);
        return vo;
    }

    /**
     * 配送员集合转VO集合
     *
     * @param runDeliveryusers
     * @return
     * @throws AppException
     */
    @Override
    public List<DeliveryDetailVO> convertDeliverys2VOs(List<RunDeliveryuser> runDeliveryusers) throws AppException {
        if (runDeliveryusers == null || runDeliveryusers.isEmpty()) {
            return new ArrayList<>();
        }
        List<DeliveryDetailVO> vos = new ArrayList<>(runDeliveryusers.size());
        for (RunDeliveryuser user : runDeliveryusers) {
            if (user == null) {
                continue;
            }
            DeliveryDetailVO vo = convertDelivery2VO(user);
            if (vo == null) {
                continue;
            }
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 配送员转VO
     *
     * @param deliveryuser
     * @return
     * @throws AppException
     */
    @Override
    public DeliveryDetailVO convertDelivery2VO(RunDeliveryuser deliveryuser) throws AppException {
        if (deliveryuser == null) {
            return null;
        }
        DeliveryDetailVO deliveryVO = new DeliveryDetailVO();
        deliveryVO.setDid(deliveryuser.getDid());
        RunDeliveryInfo runDeliveryInfo = runDeliveryInfoService.getRunDeliveryInfoByID(deliveryuser.getDid());
        if (runDeliveryInfo != null) {
            deliveryVO.setAddressId(runDeliveryInfo.getAddressId());
            RunDeliveryAddress address = runDeliveryAddressService.getRunDeliveryAddressByID(runDeliveryInfo.getAddressId());
            if (address != null) {
                deliveryVO.setAddressDesc(address.getDeliveryAddress());
            }
            deliveryVO.setReportedTimes(runDeliveryInfo.getReportedTimes());
            deliveryVO.setPoint(runDeliveryInfo.getPoint());
            deliveryVO.setPhoto(runDeliveryInfo.getPhoto());
            deliveryVO.setPhone(runDeliveryInfo.getPhone());
            deliveryVO.setName(runDeliveryInfo.getName());
            deliveryVO.setLevel(runDeliveryInfo.getLevel());
            deliveryVO.setGender(runDeliveryInfo.getGender());
            deliveryVO.setGenderDesc(PersonGenderEnum.getUserTypeEnum(runDeliveryInfo.getGender()).getDesc());
            deliveryVO.setCard(runDeliveryInfo.getCard());
        }
        deliveryVO.setUserphone(deliveryuser.getUserphone());
        deliveryVO.setUpdateTime(deliveryuser.getUpdateTime());
        deliveryVO.setReviewPhoto(deliveryuser.getReviewPhoto());
        deliveryVO.setPassword(deliveryuser.getPassword());
        deliveryVO.setCredits(deliveryuser.getCredits());
        deliveryVO.setAddTime(deliveryuser.getAddTime());
        deliveryVO.setStatus(deliveryuser.getStatus());
        deliveryVO.setStatusDesc(deliveryuser.getStatus() ? PersonStatusEnum.ONLINE.getDesc() : PersonStatusEnum.NOT_ONLINE.getDesc());
        deliveryVO.setIsDelete(deliveryuser.getIsDelete());
        deliveryVO.setIsDeleteDesc(deliveryuser.getIsDelete() ? IsDeleteEnum.DELETE.getDesc() : IsDeleteEnum.NOT_DELETE.getDesc());
        deliveryVO.setAvailable(deliveryuser.getAvailable());
        deliveryVO.setAvailableDesc(deliveryuser.getAvailable() ? AvailableEnum.CAN.getDesc() : AvailableEnum.CAN_NOT.getDesc());

        RunDeliveryBalance balance = runDeliveryBalanceService.getRunDeliveryBalanceByDID(deliveryuser.getDid());
        if (balance != null) {
            deliveryVO.setBalance(balance.getDeliveryBalance());
        }
        return deliveryVO;
    }

    /**
     * 填充配送员VO集合
     *
     * @param vos
     * @return
     * @throws AppException
     */
    @Override
    public List<DeliveryDetailVO> fillDeliveryVOs(List<DeliveryDetailVO> vos) throws AppException {
        if (vos == null || vos.isEmpty()) {
            return new ArrayList<>();
        }
        for (DeliveryDetailVO vo : vos) {
            if (vo == null) {
                continue;
            }
            fillDeliveryVO(vo);
        }
        return vos;
    }

    /**
     * 填充配送员VO
     *
     * @param vo
     * @return
     * @throws AppException
     */
    @Override
    public DeliveryDetailVO fillDeliveryVO(DeliveryDetailVO vo) throws AppException {
        if (vo == null) {
            return null;
        }
        Integer did = vo.getDid();
        if (did == null) {
            return null;
        }
        RunDeliveryDistance distance = runDeliveryDistanceService.getRunDeliveryDistanceByDID(did);
        if (distance != null) {
            vo.setViewOrderDistance(distance.getViewOrderDistance());
            vo.setDeliveryDistance(distance.getDeliveryDistance());
            vo.setSendDistance(distance.getSendDistance());
        }
        vo.setOrderCount(runOrderService.orderCountByUIDAndDID(null, did));
        List<RunDeliveryAddress> addresses = runDeliveryAddressService.getAllRunDeliveryAddressByDID(did);
        vo.setAddressList(addresses);
        vo.setBalanceRecordPageInfo(runDeliveryBalanceRecordService.pageAllDeliveryRecordByDID(did, null, null, null, null));
        return vo;
    }

}
