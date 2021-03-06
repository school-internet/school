package com.school.internet.equip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.internet.corn.config.PageUtils;
import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.entity.EqSendlog;
import com.school.internet.equip.entity.EquipdocVO;
import com.school.internet.equip.entity.ReviceVO;
import com.school.internet.equip.mapper.EqEquipdocMapper;
import com.school.internet.equip.mapper.EqSendlogMapper;
import com.school.internet.equip.service.IEqEquipdocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
@Service
public class EqEquipdocServiceImpl extends ServiceImpl<EqEquipdocMapper, EqEquipdoc> implements IEqEquipdocService {


    @Autowired
    private EqEquipdocMapper eqEquipdocMapper;

    @Autowired
    private EqSendlogMapper eqSendlogMapper;

    @Override
    public IPage<EquipdocVO> pageEquipdoc(Page page, EqEquipdoc eqEquipdoc) {

         IPage<EquipdocVO> eqEquipdocIPage  = eqEquipdocMapper.pageEquip(page,eqEquipdoc);
         List<EquipdocVO> EquipdocVOList  =   eqEquipdocIPage.getRecords();
         for(EquipdocVO eqEquipdocs: EquipdocVOList){
             QueryWrapper<EqSendlog> queryWrapper = new QueryWrapper<>();
             queryWrapper.eq("fk_equipdoc",eqEquipdocs.getPkEquipdoc());
             queryWrapper.orderByDesc("send_time");
             queryWrapper.last("limit 1");
             EqSendlog eqSendlog =  eqSendlogMapper.selectOne(queryWrapper);
             if(eqSendlog!=null) {
                 eqEquipdocs.setSendTime(eqSendlog.getSendTime());
             }
         }
         return eqEquipdocIPage;
    }

    @Override
    public List<EquipdocVO> selectEquipdoc(String imei) {
        return eqEquipdocMapper.selectEquip(imei);
    }

    @Override
    public ReviceVO selectState(String pkEquipdoc) {
        return eqEquipdocMapper.equipselect(pkEquipdoc);
    }



}
