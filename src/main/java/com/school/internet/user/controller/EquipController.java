package com.school.internet.user.controller;

import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.entity.EqType;
import com.school.internet.equip.service.IEqEquipdocService;
import com.school.internet.equip.service.impl.EqEquipdocServiceImpl;
import com.school.internet.equip.service.impl.EqTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("equip")
public class EquipController {

    @Autowired
    private EqTypeServiceImpl iEqTypeService;

    @Autowired
    private IEqEquipdocService iEqEquipdocService;

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
}
