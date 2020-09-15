package com.school.internet.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/user")
public class LoginController {



    @RequestMapping("/login")
    public String login(HttpServletRequest  request){
        System.out.println("haha");
        return "index";
    }



    @PostMapping("test")
    public void  query(@RequestBody String  userId){
        System.out.println("userId===="+userId);
    }
}
