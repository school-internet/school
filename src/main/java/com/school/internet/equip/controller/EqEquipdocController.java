package com.school.internet.equip.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.corn.config.*;
import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.entity.EquipdocVO;
import com.school.internet.equip.service.IEqEquipdocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @PostMapping("pageEqEquipdoc")
    public MSPage<EquipdocVO>  pagelist(@RequestBody  EqEquipdoc eqEquipdoc){
        Page<EqEquipdoc>  page  = new Page<>();
        page.setCurrent(eqEquipdoc.getPageNo());
        page.setSize(eqEquipdoc.getPageSize());
       return PageUtils.page(iEqEquipdocService.pageEquipdoc(page,eqEquipdoc));

    }

    @PostMapping("saveEqEquipdoc")
    public void  saveEqEquipdoc(@RequestBody EqEquipdoc eqEquipdoc){
        iEqEquipdocService.save(eqEquipdoc);
    }


    @PostMapping("updateEqEquipdoc")
    public void  updateEqEquipdoc(@RequestBody EqEquipdoc eqEquipdoc){
        iEqEquipdocService.updateById(eqEquipdoc);
    }

    @GetMapping("deleteEqEquipdoc")
    public void deleteEqEquipdoc(String pkEquipdoc){
        LambdaUpdateWrapper<EqEquipdoc> updateWrapper  =  new LambdaUpdateWrapper<>();
        updateWrapper.eq(EqEquipdoc::getDr,1);
        updateWrapper.eq(EqEquipdoc::getPkEquipdoc,pkEquipdoc);
        iEqEquipdocService.update(updateWrapper);
    }


    @GetMapping("sendMsg")
    public void sendMsg(String imei,String value)  throws Exception{
        SocketChannel socket = dcc_client.dcc_Socket("iot.harvestcloud.cn", 9877);

        //数据包格式看mserver相关手册
        //发送广播

        dcc_msg msg = new dcc_msg();
        msg.setMsg_type((byte) 0x00);
        msg.setMsg_len(8);
        msg.setMsg_body(ByteUtils.getByteArray(value));
        msg.setImei(imei);
        System.out.println("发送="+dcc_client.dcc_msg_send(socket, msg));

        Thread.sleep(300);

        //接受数据
        msg = new dcc_msg();
        System.out.print("返回="+dcc_client.dcc_msg_recv(socket, msg));
    }
}
