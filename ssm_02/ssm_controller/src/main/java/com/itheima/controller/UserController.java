package com.itheima.controller;

import com.itheima.domain.PageBean;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        boolean flag = userService.findLogin(user.getUsername(), user.getPassword());
        if (flag) {
            session.setAttribute("user", user);


            return "forward:../index.jsp";
        }
        model.addAttribute("login_msg", "账号或密码输入有误");
        return "forward:../login.jsp";
    }


    @RequestMapping("/list")
    public String findUserByPage(@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage, Model model, User user) {
        if (currentPage <= 0 || currentPage == null) {
            currentPage = 1;
        }
        System.out.println(user);
        PageBean<User> pb = userService.findUserByPage(currentPage, user);
        model.addAttribute("pb", pb);
        model.addAttribute("user", user);
        return "list";
    }

    @RequestMapping("/update")
    public String update(Integer id, Model model) {
        User user = userService.findById(id);
        System.out.println(user);
        model.addAttribute("user", user);
        return "update";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        userService.deleteById(id);
        return "redirect:/user/list";
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(Integer[] uid) {
        for (Integer id : uid) {
            userService.deleteById(id);
        }
        return "redirect:/user/list";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user, HttpSession session, MultipartFile upload, Model model) {
        try {
            //判断用户是否修改图片路径
            String dataPath = userService.findByPic(user.getId());
            //获取当前用户在数据库保存的路径
            String picpath = dataPath.split("_")[1];
            String pname = upload.getOriginalFilename();
            //修改了
            if (!picpath.equals(pname) && !"".equals(pname) && pname!=null) {
                //允许上
                String[] allowType = {"image/jpeg"};
                //获取类路径下文件
                String path = session.getServletContext().getRealPath("/images/");
                File file = new File(path);
                //判断文件是否存在
                if (!file.exists()) {
                    file.mkdirs();
                }
                //获取上传文件的类型
                String contentType = upload.getContentType();
                if (!Arrays.asList(allowType).contains(contentType)) {
                    model.addAttribute("msg_erro", "您上传的图片格式有误");
                    return "update";
                }
                //获取上传文件名称
                String filename = upload.getOriginalFilename();
                //获取uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                //把文件名称唯一化
                filename = uuid + "_" + filename;
                //文件上传
                upload.transferTo(new File(file, filename));
                user.setPic(filename);
               //System.out.println("我的fuck" + user);

            }
            userService.updateUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/list";
    }




    @RequestMapping("/add")
    public String addUser(MultipartFile upload, HttpSession session, User user, Model model) {
        try {
            System.out.println(user);
            //允许上
            String[] allowType = {"image/jpeg"};
            //获取类路径下文件
            String path = session.getServletContext().getRealPath("/images/");
            File file = new File(path);
            //判断文件是否存在
            if (!file.exists()) {
                file.mkdirs();
            }
            //获取上传文件的类型
            String contentType = upload.getContentType();
            if (!Arrays.asList(allowType).contains(contentType)) {
                model.addAttribute("msg_erro", "您上传的图片格式有误");
                return "add";
            }
            //获取上传文件名称
            String filename = upload.getOriginalFilename();
            //获取uuid
            String uuid = UUID.randomUUID().toString().replace("-", "");
            //把文件名称唯一化
            filename = uuid + "_" + filename;
            //文件上传
            upload.transferTo(new File(file, filename));
            user.setPic(filename);
            userService.saveUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/list";
    }
}
