package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by feng on 2017/7/29.
 */
@Service("iUserService")    // 定义为接口名，注入到Controller中
public class UserServiceImpl implements IUserService {

    // 自动注入UserMapper
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String username, String password) {
        // 验证用户名是否存在
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        // 将登陆密码进行MD5加密
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null){
            return  ServerResponse.createByErrorMessage("密码错误");
        }
        // 将密码设置为空
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);
    }

    @Override
    public ServerResponse<String> register(User user){
        // 校验用户名和email
        ServerResponse validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        // 设置用户角色
        user.setRole(Const.Role.ROLE_CUSTOMER);
        // 对密码进行加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        // 将用户添加到数据库中
        int resultCount = userMapper.insert(user);
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type){
        // 判断type是否有效
        if (StringUtils.isNotBlank(type)){
            // 如果是username,判断用户名是否存在
            if (Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0){
                    return ServerResponse.createByErrorMessage("用户名已经存在");
                }
            }
            // 如果是email，判断email是否存在
            if (Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0){
                    return ServerResponse.createByErrorMessage("email已经存在");
                }
            }
        }else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<String> selectQuestion(String username){
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
       // 调用mapper查询question
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(question)){
            return ServerResponse.createBySuccessMessage(question);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer){
        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount > 0){
            // 生成一个Token，以username为key，缓存在localcache中
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + username, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题的答案错误");
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken){
        // 校验参数
        if (StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        }
        ServerResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        // 从localcache中获取token
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }
        // 如果token匹配,则进行密码更新
        if (StringUtils.equals(forgetToken, token)){
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
            if (rowCount > 0){
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }
        }else {
            return ServerResponse.createByErrorMessage("token错误，请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user){
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getUsername());
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        // 设置用户新密码，
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        // 更新至数据库
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0){
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    public ServerResponse<User> updateInformation(User user){
        // 校验该用户的email没有被其他用户使用过
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0){
            return ServerResponse.createByErrorMessage("email已经存在，请更换email");
        }
        // 保证username
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0){
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }

    public ServerResponse<User> getInformation(Integer userId){
        // 查找用户
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        // 密码设置为空，返回
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }

    // backend
    // 校验当前用户是否管理员
    public ServerResponse checkAdminRole(User user){
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByError();
        }
    }


}
