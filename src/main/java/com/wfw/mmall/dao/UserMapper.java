package com.wfw.mmall.dao;

import org.apache.ibatis.annotations.Param;

import com.wfw.mmall.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int checkUsername(String username);
    
    int checkEmail(String email);
    
    User selectLogin(@Param("username") String username,@Param("password") String password);
    
    String selectQuestionByUsername(String username);
    
    int checkAnswer(@Param("username") String username,@Param("question") String question,@Param("answer") String answer);
    
    int updatePasswordByUsername(@Param("username") String username,@Param("passwordNew") String passwordNew);
    
    int checkPassword(@Param("password") String password,@Param("id") Integer id);
    
    //通过用户id检查邮箱是否已存在,更新邮箱的时候不与自己原来的邮箱进行比较
    int checkEmailByUserId(@Param("email") String email,@Param("id") Integer id);
    
    
    
    
    
    
    
    
    
    
}