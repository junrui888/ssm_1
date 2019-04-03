package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;

public interface UserService {


    boolean findLogin(String username, String password);

   // List<User> findList();


    User findById(Integer id);

    void deleteById(Integer id);

    void updateUser(User user);

    void saveUser(User user);

    PageBean<User> findUserByPage(Integer currentPage, User user);

    String findByPic(Integer id);

}
