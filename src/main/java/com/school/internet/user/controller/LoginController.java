package com.school.internet.user.controller;


import com.school.internet.user.common.ResultMsg;
import com.school.internet.user.common.ThreadVariable;
import com.school.internet.user.entity.SmUser;
import com.school.internet.user.service.ISmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-13
 */
@Controller
public class LoginController {

    @Autowired
    private ISmUserService iSmUserService;

    @RequestMapping("wxindex")
    public String wxindex(HttpServletRequest req){
        SmUser smUser =  iSmUserService.querySmUser("root","123789");
        ThreadVariable threadVariable =  ThreadVariable.getInstance();
        Thread thread = new Thread(threadVariable);
        threadVariable.setPsndoc(smUser);
        thread.start();
        req.getSession().setAttribute("user",smUser);
        return  "wxindex";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest req){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        req.setAttribute("errorMsg", req.getParameter("errorMsg"));
        if (username == null || password == null) {

            return "/login";
        }
        SmUser smUser =  iSmUserService.querySmUser(username,password);
        if(smUser == null){
            return "redirect:/login?" + "username=" + username + "&" + ResultMsg.errorMsg("用户名或密码错误").asUrlParams();
        }else{
            ThreadVariable threadVariable =  ThreadVariable.getInstance();
            Thread thread = new Thread(threadVariable);
            threadVariable.setPsndoc(smUser);
            thread.start();
            req.getSession().setAttribute("user",smUser);
            return  "redirect:/main";
        }

    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest req){
        ThreadVariable threadVariable =  ThreadVariable.getInstance();
        SmUser smUser  = threadVariable.getPsndoc();
        threadVariable.remove();
        req.getSession().removeAttribute("user");
        return "redirect:/login";
    }





    @RequestMapping("main")
    public String main(){
        return "main";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @PostMapping("test")
    public void  query(@RequestBody String  userId){
        System.out.println("userId===="+userId);
    }
}
