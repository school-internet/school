package com.school.internet.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.internet.user.common.MD5Util;
import com.school.internet.user.common.SaltUtil;
import com.school.internet.user.entity.SmUser;
import com.school.internet.user.mapper.SmUserMapper;
import com.school.internet.user.service.ISmUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-13
 */
@Service
public class SmUserServiceImpl extends ServiceImpl<SmUserMapper, SmUser> implements ISmUserService {


    @Autowired
    private SmUserMapper smUserMapper;


    @Override
    public SmUser querySmUser(String name, String password) {
        String passwordEncry =  MD5Util.getInstance().md5Encryption(password);
        SmUser smUser  = new SmUser();
        smUser.setName(name);
        smUser.setPassword(password);
        QueryWrapper<SmUser> queryWrapper  = new QueryWrapper<>(smUser);
        List<SmUser> userList = smUserMapper.selectList(queryWrapper);
        if (userList.size()>0){
            return userList.get(0);
        }
         return null;
    }
}
