package com.school.internet.equip.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.corn.config.ByteUtils;
import com.school.internet.corn.config.CronTaskRegistrar;
import com.school.internet.corn.config.MSPage;
import com.school.internet.corn.task.SchedulingRunnable;
import com.school.internet.equip.entity.*;
import com.school.internet.equip.service.IEqEquipdocService;
import com.school.internet.equip.service.IEqInstructService;
import com.school.internet.equip.service.IEqRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private IEqEquipdocService iEqEquipdocService;

    @PostMapping("queryAll")
    public MSPage<EqRuleVo> queryAll(Integer pageNo, Integer pageSize, EqRule eqRule){
        Page<EqRuleVo> page  = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);

      return   iEqRuleService.all(page,eqRule);
    }

   @PostMapping("addrule")
    public void  addrule(String list){
        JSONArray array =   JSONArray.parseArray(list);

         for(int i=0; i<array.size(); i++) {

             EqRule eqRule =  JSON.toJavaObject(array.getJSONObject(i),EqRule.class);
             //根据穿得类型和端口以及值来判断   0 0/2 10-11 * * ?

             String effecttime = eqRule.getEffectivedate();
            String[] arraytime =  effecttime.split("-");
            Integer endTime = Integer.valueOf(arraytime[1])-1;
            String effectivedate  =arraytime[0]+"-"+endTime;
             StringBuffer rule = new StringBuffer();
             //断开的时间
             String hour ="";
             if(i==0) {
               hour  = "0/" + eqRule.getRuletime();
             }else{
                 EqRule eqRuleold =  JSON.toJavaObject(array.getJSONObject(0),EqRule.class);
                 hour  =eqRule.getRuletime()+"-"+"59"+"/"+eqRuleold.getRuletime();
             }
             rule.append("0 ");
             rule.append(hour);
             rule.append(" "+effectivedate);
             rule.append(" * * ?");
             QueryWrapper<EqInstruct> queryWrapper = new QueryWrapper<>();
             queryWrapper.eq("fk_equiptype", eqRule.getFkEquiptype());
             queryWrapper.eq("port", eqRule.getPort());
             //继电器
             queryWrapper.eq("state", eqRule.getState());
             EqInstruct eqInstruct = iEqInstructService.getOne(queryWrapper);
             if (eqInstruct != null) {
                 eqRule.setFkInstruct(eqInstruct.getPkInstruct());
                 EqEquipdoc eqEquipdoc = iEqEquipdocService.getById(eqRule.getFkEquipdoc());
                 eqRule.setImei(eqEquipdoc.getImei());
                 eqRule.setInstructValue(eqInstruct.getInstructValue());
                 //查询这个时间段有没有设置规则
               /*  QueryWrapper<EqRule> queryWrappers = new QueryWrapper<>();
                 queryWrapper.eq("imei",eqEquipdoc.getImei());
                 queryWrapper.eq("effectivedate", eqRule.getFkEquiptype());
                 queryWrapper.eq("instruct_value", eqInstruct.getInstructValue());
                 List<EqRule> eqRules = iEqRuleService.list(queryWrappers);
                 if(eqRules.size()>0){
                     continue;
                 }*/
                 eqRule.setRuleValue(rule.toString());
                 iEqRuleService.saveOrUpdate(eqRule);
                 SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskWithParams", eqRule.getPkRule());
                 cronTaskRegistrar.addCronTask(task, eqRule.getRuleValue());
             }
         }

   }

   @GetMapping("removerule")
    public void removerule(String pkRule){
        iEqRuleService.removeById(pkRule);
       SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskWithParams",pkRule );
       cronTaskRegistrar.removeCronTask(task);
   }

}
