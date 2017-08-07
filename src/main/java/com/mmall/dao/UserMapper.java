package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // 验证用户名是否存在，返回查询到的用户数量
    int checkUsername(String username);

    // 验证email是否存在，返回查询到的email数量
    int checkEmail(String email);

    // 验证用户和密码是否正确,返回查询到的用户
    User selectLogin(@Param("username") String username, @Param("password")String password);

    // 获取用户问题
    String selectQuestionByUsername(String username);

    // 校验问题答案
    int checkAnswer(@Param("username") String username, @Param("question")String question, @Param("answer")String answer);

    // 更新密码
    int updatePasswordByUsername(@Param("username")String username, @Param("passwordNew")String passwordNew);

    // 校验密码
    int checkPassword(@Param("password")String password, @Param("username")String username);

    // 校验用户email没有被其他用户使用过
    int checkEmailByUserId(@Param("email")String email, @Param("userId")Integer userId);

}