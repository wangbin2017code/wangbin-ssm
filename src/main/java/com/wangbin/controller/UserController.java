package com.wangbin.controller;

import com.wangbin.entity.User;
import com.wangbin.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wangbin on 2018/06/10.
 */
@Controller
public class UserController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;


    /**
     * @param mm
     * @return 参数
     * @Title: addPage
     * @Description: 用户添加页面
     */
    @RequestMapping("addpage.jhtml")
    public String addPage(ModelMap mm) {
        logger.info("add page...");
        return "/register";
    }

    /**
     * @param request
     * @param response
     * @param user
     * @throws IOException
     * @throws ServletException 参数
     * @Title: Regiseter
     * @Description: 用户添加操作
     */
    @RequestMapping("register.jhtml")
    public void Regiseter(HttpServletRequest request, HttpServletResponse response, User user) throws IOException, ServletException {
        int result = 0;
        if (user != null) {
            result = userService.addUser(user);
        }
        if (result > 0) {
            logger.info("注册成功..");
            response.sendRedirect("/allUser.jhtml");
        } else {
            logger.error("注册失败");
            request.getRequestDispatcher("/500.jhtml").forward(request, response);
        }
    }

    /**
     * @param mm
     * @return 参数
     * @Title: listUser
     * @Description: 列表显示所有用户
     */
    @RequestMapping("allUser.jhtml")
    public String listUser(ModelMap mm) {
        List<User> list = userService.getUserList();
        mm.put("list", list);
        return "/userlist";
    }


    /**
     * @param id
     * @param mm
     * @return 参数
     * @Title: editUser
     * @Description: 用户信息编辑页面
     */
    @RequestMapping("editUser.jhtml")
    public String editUser(String id, ModelMap mm) {
        User user = userService.getUser(Integer.parseInt(id));
        mm.put("user", user);
        return "/register";
    }


    /**
     * @param request
     * @param response
     * @param user
     * @throws Exception 参数
     * @Title: updateUser
     * @Description: 更新用户信息
     */
    @RequestMapping("updateUser.jhtml")
    public void updateUser(HttpServletRequest request, HttpServletResponse response, User user) throws Exception {
        int result = userService.updateUser(user);
        if (result > 0) {
            logger.info("更新用户数据成功");
            response.sendRedirect("/allUser.jhtml");

        } else {
            logger.error("更新用户数据失败");
            request.getRequestDispatcher("/500.jhtml").forward(request, response);

        }
    }

    /**
     * @param request
     * @param response
     * @param id
     * @throws Exception 参数
     * @Title: deleteUserById
     * @Description: 删除一条用户记录
     */
    @RequestMapping("deleteUser.jhtml")
    public void deleteUserById(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        int result = userService.deleteUser(Integer.parseInt(id));
        if (result > 0) {
            logger.info("删除用户数据成功..");
            response.sendRedirect("/allUser.jhtml");

        } else {
            logger.error("删除用户数据失败..");
            request.getRequestDispatcher("/500.jhtml").forward(request, response);

        }
    }
}
