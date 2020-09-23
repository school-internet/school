package com.school.internet.equip.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.school.internet.equip.entity.EqInstruct;
import com.school.internet.equip.entity.EqType;
import com.school.internet.equip.service.IEqInstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
@RestController
@RequestMapping("/equip/eq-instruct")
public class EqInstructController {

    @Autowired
    private IEqInstructService iEqInstructService;

    @GetMapping("listInstruct")
    public List<EqInstruct> listInstruct(String fkEquiptype){
        LambdaQueryWrapper<EqInstruct> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EqInstruct::getDr,0);
        lambdaQueryWrapper.eq(EqInstruct::getFkEquiptype,fkEquiptype);
        List<EqInstruct> instructs = iEqInstructService.list(lambdaQueryWrapper);
        return instructs;
    }

    @PostMapping("saveInstruct")
    public void saveInstruct(EqInstruct eqInstruct){
        eqInstruct.setDr(0);
        iEqInstructService.save(eqInstruct);
    }

    @PostMapping("updateInstruct")
    public void updateInstruct(EqInstruct eqInstruct){
        iEqInstructService.updateById(eqInstruct);
    }

    @GetMapping("removeInstruct")
    public void  removeInstruct(String pkInstruct){
        LambdaUpdateWrapper<EqInstruct> updateWrapper  = new LambdaUpdateWrapper<>();
        updateWrapper.eq(EqInstruct::getPkInstruct,pkInstruct);
        updateWrapper.set(EqInstruct::getDr,1);
        iEqInstructService.update(updateWrapper);
    }

}
