package com.mapper;

import java.util.List;

import com.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int deleteById(int id);

    int insert(User user);

    User selectByKey(Integer id);

    int updateByKey(User user);

    User getUserByUserName(String userName);

    User getUserByAccessToken(String accessToken);

    List<User> getUserList(@Param("userName") String userName, @Param("realName") String realName);
}