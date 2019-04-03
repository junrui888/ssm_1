package com.itheima.mapper;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User findUser(@Param("username") String username, @Param("password") String password);

    List<User> findAll(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("user")User user);

    User findById(Integer id);

    void deleteById(Integer id);

    void updateUser(User user);

    void saveUser(User user);

    Integer findTotalCount(User user);

    String findByPic(Integer id);
}
