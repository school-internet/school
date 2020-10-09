package com.school.internet.equip.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.corn.config.*;
import com.school.internet.equip.entity.*;
import com.school.internet.equip.service.IEqEquipdocService;
import com.school.internet.equip.service.IEqInstructService;
import com.school.internet.equip.service.IEqSendlogService;
import com.school.internet.utils.DateTimeUtils;
import com.school.internet.utils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.SocketChannel;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
@RestController
@RequestMapping("/equip/eq-equipdoc")
public class EqEquipdocController {

    @Autowired
    private IEqEquipdocService iEqEquipdocService;
    @Autowired
    private IEqSendlogService iEqSendlogService;
    @Autowired
    private IEqInstructService iEqInstructService;

    @PostMapping("pageEqEquipdoc")
    public MSPage<EquipdocVO>  pagelist( Integer pageNo, Integer pageSize,EquipdocVO equipdocVO){
        Page<EqEquipdoc>  page  = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        EqEquipdoc eqEquipdoc  = new EqEquipdoc();
        if(null != equipdocVO.getEquipName()){
            eqEquipdoc.setEquipName(equipdocVO.getEquipName());
        }
        if(null != equipdocVO.getFkEquiptype()){
            eqEquipdoc.setFkEquiptype(equipdocVO.getFkEquiptype());
        }

       return PageUtils.page(iEqEquipdocService.pageEquipdoc(page,eqEquipdoc));

    }

    @PostMapping("saveEqEquipdoc")
    public void  saveEqEquipdoc(EqEquipdoc eqEquipdoc){
        eqEquipdoc.setDr(0);
        iEqEquipdocService.save(eqEquipdoc);
    }


    @PostMapping("updateEqEquipdoc")
    public void  updateEqEquipdoc(EqEquipdoc eqEquipdoc){
        iEqEquipdocService.updateById(eqEquipdoc);
    }

    @GetMapping("deleteEqEquipdoc")
    public void deleteEqEquipdoc(String pkEquipdoc){
        LambdaUpdateWrapper<EqEquipdoc> updateWrapper  =  new LambdaUpdateWrapper<>();
        updateWrapper.set(EqEquipdoc::getDr,1);
        updateWrapper.eq(EqEquipdoc::getPkEquipdoc,pkEquipdoc);
        iEqEquipdocService.update(updateWrapper);
    }

    /**
     * 继电器发送
     * @param pkEquipdoc
     * @param structs
     */
    @GetMapping("sendMsg")
    public void sendjdq(String pkEquipdoc,String  imei,String structs){
        //指令格式 1:true,2:false,3:true.... 以逗号截取8个口
        Dcc_client dcc_client = new Dcc_client();
        SocketChannel socket = dcc_client.dcc_Socket("iot.harvestcloud.cn", 9877);

        //数据包格式看mserver相关手册
        //发送广播

        String[] array =  structs.split(",");
        for(int i=0;i<array.length;i++){
            String msg = array[i];
            String[] param = msg.split(":");
            QueryWrapper<EqInstruct> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("fk_equipdoc",pkEquipdoc);
            queryWrapper.eq("port",param[0]);
            queryWrapper.eq("state",param[1]);
            EqInstruct eqInstruct= iEqInstructService.getOne(queryWrapper);
           String value =  eqInstruct.getInstructValue();
            MsgUtil.sendMsg(imei,value);
        }
        EqSendlog  eqSendlog  = new EqSendlog();
        eqSendlog.setFkEquipdoc(pkEquipdoc);
        eqSendlog.setSendTime(DateTimeUtils.formatTime());
        eqSendlog.setResultValue("成功");
        eqSendlog.setState(0);
        eqSendlog.setInstructValue(structs);
        iEqSendlogService.save(eqSendlog);

    }


    @GetMapping("sendMsg")
    public Integer sendMsg(String pkEquipdoc,String value,String imei)  throws Exception{
        Dcc_client dcc_client = new Dcc_client();
        SocketChannel socket = dcc_client.dcc_Socket("iot.harvestcloud.cn", 9877);

        //数据包格式看mserver相关手册
        //发送广播
        Integer  values = MsgUtil.sendMsg(imei,value);
        EqSendlog  eqSendlog  = new EqSendlog();
        eqSendlog.setFkEquipdoc(pkEquipdoc);
        eqSendlog.setSendTime(DateTimeUtils.formatTime());
        eqSendlog.setResultValue("成功");
        eqSendlog.setState(values);
        eqSendlog.setInstructValue(value);
        iEqSendlogService.save(eqSendlog);

        return 0;

    }

    //查找设备机型当前最新状态

}
