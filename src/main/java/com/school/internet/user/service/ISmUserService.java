package com.school.internet.user.service;

import com.school.internet.user.entity.SmUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-13
 */
public interface ISmUserService extends IService<SmUser> {


     SmUser querySmUser(String name,String password);

     int  saveUser(SmUser smUser);



}
