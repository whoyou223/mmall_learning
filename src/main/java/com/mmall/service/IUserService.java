package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * Created by feng on 2017/7/29.
 */
public interface IUserService {

    // 用户登录
    ServerResponse<User> login(String username, String password);

    // 用户注册
    ServerResponse<String> register(User user);

    // 校验用户名或email是否存在
    ServerResponse<String> checkValid(String str, String type);

    // 获取密码问题
    ServerResponse<String> selectQuestion(String username);

    // 校验问题答案
    ServerResponse<String> checkAnswer(String username, String question, String answer);

    // 忘记密码修改密码
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    // 登录状态修改密码
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    // 更新个人信息
    ServerResponse<User> updateInformation(User user);

    // 获取个人信息
    ServerResponse<User> getInformation(Integer userId);

    // 校验当前用户是否管理员
    ServerResponse checkAdminRole(User user);
}
