package com.school.internet.user.controller;


import com.school.internet.user.entity.SmUser;
import com.school.internet.user.service.ISmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/user/sm-user")
public class SmUserController {

    @Autowired
    private ISmUserService  iSmUserService;


    @PostMapping("saveUser")
    public void  saveUser(@RequestBody SmUser smUser){
        iSmUserService.saveUser(smUser);
    }
}
