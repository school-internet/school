package com.school.internet.equip.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.corn.config.ByteUtils;
import com.school.internet.corn.config.CronTaskRegistrar;
import com.school.internet.corn.config.MSPage;
import com.school.internet.corn.task.SchedulingRunnable;
import com.school.internet.equip.entity.EqInstruct;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.entity.EqRuleVo;
import com.school.internet.equip.entity.EquipdocVO;
import com.school.internet.equip.service.IEqInstructService;
import com.school.internet.equip.service.IEqRuleService;
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
@RequestMapping("/equip/eq-rule")
public class EqRuleController {

    @Autowired
    private  IEqRuleService iEqRuleService;

    @Autowired
    private IEqInstructService iEqInstructService;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @GetMapping("queryAll")
    public MSPage<EqRuleVo> queryAll(Integer pageNo, Integer pageSize, EqRule eqRule){
        Page<EqRuleVo> page  = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);

      return   iEqRuleService.all(page,eqRule);
    }

   @PostMapping("addrule")
    public void  addrule(EqRule eqRule){
        //根据穿得类型和端口以及值来判断

           QueryWrapper<EqInstruct> queryWrapper = new QueryWrapper<>();
           queryWrapper.eq("fk_equiptype", eqRule.getFkEquiptype());
           queryWrapper.eq("port", eqRule.getPort());
           //继电器
           if (!eqRule.getFkEquiptype().equals("0001000004AMN8S1VDZS")) {
               queryWrapper.eq("state",eqRule.getState());
           }
           EqInstruct eqInstruct = iEqInstructService.getOne(queryWrapper);

            if(!eqRule.getFkEquiptype().equals("0001000004AMN8S1VDZS")){
                eqRule.setInstructValue(eqInstruct.getInstructValue());
            }else {
                Integer value = eqRule.getState();
                value = value * 4095 / 100;
                String values = Integer.toHexString(value).toUpperCase();
                StringBuffer buffer = new StringBuffer();
                buffer.append(eqInstruct.getInstructValue());
                buffer.append(values);
                buffer.append(ByteUtils.getCRC(eqInstruct.getInstructValue()));
                eqRule.setInstructValue(buffer.toString());
            }

        iEqRuleService.saveOrUpdate(eqRule);
       SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskWithParams",eqRule.getPkRule() );
       cronTaskRegistrar.addCronTask(task, eqRule.getRuleValue());

   }

   @GetMapping("removerule")
    public void removerule(String pkRule){
        iEqRuleService.removeById(pkRule);
       SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskWithParams",pkRule );
       cronTaskRegistrar.removeCronTask(task);
   }

}
