package com.running.business.util;

import com.running.business.common.Config;
import com.running.business.dto.UserDTO;

/**
 * @author liumingyu
 * @create 2018-01-21 下午6:34
 */
public class UserUtil {
    public static UserDTO getUser() {
        return (UserDTO) ThreadContext.get(Config.USER_KEY);
    }

    public static void bind(UserDTO userDTO) {
        if (userDTO != null && null != userDTO.getId() && userDTO.getId() > 0) {
            ThreadContext.put(Config.USER_KEY, userDTO);
        }
    }

    public static void unbindUser() {
        ThreadContext.remove(Config.USER_KEY);
    }
}
