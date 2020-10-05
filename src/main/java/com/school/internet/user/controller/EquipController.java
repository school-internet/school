package com.school.internet.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.entity.EqInstruct;
import com.school.internet.equip.entity.EqType;
import com.school.internet.equip.entity.ReviceVO;
import com.school.internet.equip.service.IEqEquipdocService;
import com.school.internet.equip.service.IEqInstructService;
import com.school.internet.equip.service.impl.EqEquipdocServiceImpl;
import com.school.internet.equip.service.impl.EqTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("equip")
public class EquipController {

    @Autowired
    private EqTypeServiceImpl iEqTypeService;

    @Autowired
    private IEqEquipdocService iEqEquipdocService;
    @Autowired
    private IEqInstructService iEqInstructService;

    @RequestMapping("/typelist")
    public String typelist(){
        return "equip/typelist";
    }

    @RequestMapping("/equiplist")
    public String equiplist(){
        return "equip/equiplist";
    }

    @RequestMapping("/addtype")
    public String addtype(){
        return "equip/addtype";
    }

    @RequestMapping("/addequip")
    public String addequip(){
        return "equip/addequip";
    }

    @GetMapping("toeditType")
    public String toeditType(String pkEquiptype, ModelMap modelMap){
        EqType eqtype = iEqTypeService.getById(pkEquiptype);
        modelMap.put("eqtype",eqtype);
        return "equip/edittype";
    }

    @GetMapping("toeditEquip")
    public String toeditEquipdoc(String pkEquipdoc,ModelMap modelMap){
        EqEquipdoc eqEquipdoc = iEqEquipdocService.getById(pkEquipdoc);
        modelMap.put("eqEquipdoc",eqEquipdoc);
        return "equip/editequip";
    }

    @GetMapping("listInstruct")
    public String listInstruct(String fkEquiptype,ModelMap modelMap){
        LambdaQueryWrapper<EqInstruct> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EqInstruct::getDr,0);
        lambdaQueryWrapper.eq(EqInstruct::getFkEquiptype,fkEquiptype);
        List<EqInstruct> instructs = iEqInstructService.list(lambdaQueryWrapper);
        modelMap.put("instructs",instructs);
        modelMap.put("fkEquiptype",fkEquiptype);
        return "equip/instructlist";
    }

    @GetMapping("detailsEquip")
    public String detailsEquip(String pkEquipdoc,ModelMap modelMap){
        ReviceVO reviceVO = iEqEquipdocService.selectState(pkEquipdoc);
        modelMap.put("reviceVO",reviceVO);
        return "equip/detailsequip";
    }



    @GetMapping("sendInstructs")
    public String sendInstructs(String pkEquipdoc,String imei,String fkEquiptype,ModelMap modelMap){
        LambdaQueryWrapper<EqInstruct> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EqInstruct::getDr,0);
        lambdaQueryWrapper.eq(EqInstruct::getFkEquiptype,fkEquiptype);
        List<EqInstruct> instructs = iEqInstructService.list(lambdaQueryWrapper);
        modelMap.put("imei",imei);
        modelMap.put("instructs",instructs);
        modelMap.put("fkEquiptype",fkEquiptype);
        modelMap.put("pkEquipdoc",pkEquipdoc);
        return "equip/sendinstructs";
    }

    @GetMapping("addInstruct")
    public String addInstruct(String fkEquiptype,ModelMap modelMap){
        modelMap.put("fkEquiptype",fkEquiptype);
        return "equip/addinstruct";
    }

    @GetMapping("toeditInstruct")
    public String toeditInstruct(String pkInstruct,ModelMap modelMap){
        EqInstruct eqInstruct = iEqInstructService.getById(pkInstruct);
        modelMap.put("eqInstruct",eqInstruct);
        return "equip/editinstruct";
    }
}
