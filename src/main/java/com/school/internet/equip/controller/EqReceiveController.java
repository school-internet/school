package com.school.internet.equip.controller;


import com.school.internet.equip.entity.EqReceive;
import com.school.internet.equip.service.IEqReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-10-02
 */
@RestController
@RequestMapping("/receive/eq-receive")
public class EqReceiveController {


    @Autowired
    private IEqReceiveService  iEqReceiveService;


    //查找设备机型当前最新状态
    @GetMapping("selectState")
    public EqReceive  selectState(String pkEquipdoc){
               return iEqReceiveService.selectState(pkEquipdoc);
    }

}
