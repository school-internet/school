package com.school.internet.equip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.internet.equip.entity.EqReceive;
import com.school.internet.equip.mapper.EqReceiveMapper;
import com.school.internet.equip.service.IEqReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-10-02
 */
@Service
public class EqReceiveServiceImpl extends ServiceImpl<EqReceiveMapper, EqReceive> implements IEqReceiveService {


    @Autowired
    private EqReceiveMapper eqReceiveMapper;

}
