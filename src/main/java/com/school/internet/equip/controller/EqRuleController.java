package com.school.internet.equip.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.corn.config.MSPage;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.service.IEqRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/equip/eq-rule")
public class EqRuleController {

    @Autowired
    private  IEqRuleService iEqRuleService;

    @GetMapping("queryAll")
    public MSPage<EqRule> queryAll(){
        Page<EqRule> page  = new Page<>();
        page.setCurrent(0);
        page.setSize(10);
      return   iEqRuleService.all(page);
    }

}
