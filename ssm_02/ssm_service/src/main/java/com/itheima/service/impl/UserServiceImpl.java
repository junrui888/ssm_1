package com.itheima.service.impl;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PageBean<User> pageBean;

    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean findLogin(String username, String password) {
        User user = userMapper.findUser(username, password);
        boolean flag = false;
        if (user != null) {
            flag = true;
        }
        return flag;
    }

//    @Override
//    public List<User> findList() {
//        return userMapper.findAll(start, pageSize);
//    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public PageBean<User> findUserByPage(Integer currentPage, User user) {

        Integer pageSize = 5;
        //设置每页显示个数
        pageBean.setPageSize(pageSize);
        //设置总记录数
        Integer totalCount = userMapper.findTotalCount(user);
        pageBean.setTotalCount(totalCount);
        //设置总页码
        Integer totalPage = totalCount %  pageSize == 0 ? totalCount /  pageSize : totalCount /  pageSize + 1;
        pageBean.setTotalPage(totalPage);
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        System.out.println(currentPage);
        //设置当前页码
        pageBean.setCurrentPage(currentPage);
        //设置list对象
        //获取start
        Integer start = (currentPage - 1) * pageSize;
        List<User> users = userMapper.findAll(start,pageSize,user);
        pageBean.setUsers(users);
        return pageBean;
    }

    @Override
    public String findByPic(Integer id) {
        return userMapper.findByPic(id);
    }


}
