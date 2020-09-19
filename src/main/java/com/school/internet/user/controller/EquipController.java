package com.school.internet.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("equip")
public class EquipController {

    @RequestMapping("/typelist")
    public String typelist(){
        return "equip/typelist";
    }

    @RequestMapping("/equiplist")
    public String equiplist(){
        return "equip/equiplist";
    }
}
