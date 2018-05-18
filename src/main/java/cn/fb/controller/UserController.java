package cn.fb.controller;

import cn.fb.pojo.User;
import cn.fb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionScope
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/login")
    public ModelAndView login(@RequestParam("loginname") String loginname,
                              @RequestParam("password") String password,
                              HttpSession session,
                              ModelAndView mv){
        // 调用业务逻辑组件判断用户是否可以登录
        User user = userService.login(loginname, password);
        if(user != null){
            // 将用户保存到HttpSession当中
            session.setAttribute("user_session", user);
            //做测试用
            //System.out.println("user_session: " + session.getAttribute("user_session"));
            // 客户端跳转到main页面
            mv.setViewName("redirect:/main");
        }else{
            // 设置登录失败提示信息
            mv.addObject("message", "登录名或密码错误!请重新输入");
            // 服务器内部跳转到登录页面
            mv.setViewName("forward:/loginForm");
        }
        return mv;

    }

}
