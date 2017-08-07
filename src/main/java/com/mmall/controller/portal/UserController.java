package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by feng on 2017/7/29.
 */

@Controller
@RequestMapping("/user/")
public class UserController {

    // 自动注入service Bean
    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     * 返回响应对象
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<User> login(String username, String password, HttpSession session){
        // 调用service的方法
        ServerResponse<User> response = iUserService.login(username, password);
        // 如果登录成功，
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    /**
     * 退出登录
     * 将session中用户信息删除
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<User> logout(HttpSession session){
        // 删除session中的用户信息
        session.removeAttribute(Const.CURRENT_USER);
        // 返回成功响应对象
        return ServerResponse.createBySuccess();
    }

    /**
     * 注册功能
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    /**
     * 校验用户名或者email是否存在
     * @param str
     * @param type
     * @return
     */
    @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<String> checkValid(String str, String type){
        return iUserService.checkValid(str, type);
    }

    /**
     * 获取用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse getUserInfo(HttpSession session){
        // 判断用户是否登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取用户信息");
    }

    /**
     * 获取密码提示问题
     * @param username
     * @return
     */
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    /**
     * 校验问题答案是否正确
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer){
        return iUserService.checkAnswer(username,question,answer);
    }

    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken){
        return iUserService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld, passwordNew, user);
    }

    /**
     * 更新个人信息
     * 判断用户是否登录
     * 不更新id和username
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<User> updateInformation(HttpSession session, User user){
        // 判断用户是否登录
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if (response.isSuccess()){
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER, user);
        }
        return response;
    }

    /**
     * 获取个人信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)     // 配置请求路径和请求方式
    @ResponseBody   // 配置将方法返回值自动序列化为json
    public ServerResponse<User> getInformation(HttpSession session){
        // 判断用户是否登录
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录，需要强制登录");
        }
        return iUserService.getInformation(currentUser.getId());
    }

}

