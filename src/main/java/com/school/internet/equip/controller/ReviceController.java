package com.school.internet.equip.controller;


import com.school.internet.equip.service.impl.EqEquipdocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class ReviceController {


    @Autowired
    private EqEquipdocServiceImpl  eqEquipdocService;



    public void test(){
        eqEquipdocService.list();
    }
}
