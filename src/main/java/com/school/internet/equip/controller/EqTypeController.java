package com.school.internet.equip.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.corn.config.MSPage;
import com.school.internet.corn.config.PageUtils;
import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.entity.EqType;
import com.school.internet.equip.service.IEqTypeService;
import com.school.internet.equip.service.impl.EqTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
@RestController
@RequestMapping("/equip/eq-type")
public class EqTypeController {

    @Autowired
    private EqTypeServiceImpl iEqTypeService;

    @PostMapping("saveType")
    public void  saveType(@RequestBody EqType eqType){
        iEqTypeService.save(eqType);
    }


    @PostMapping("updateType")
    public void  updateType(@RequestBody EqType eqType){
        iEqTypeService.updateById(eqType);
    }

    @GetMapping("removeType")
    public void  removeType(String pkEquipdoc){
        LambdaUpdateWrapper<EqType>  updateWrapper  = new LambdaUpdateWrapper<>();
        updateWrapper.eq(EqType::getDr,1);
        iEqTypeService.update(updateWrapper);
    }


    @PostMapping("pageType")
     public MSPage<EqType>  pageType(@RequestParam  String name, @RequestParam Integer pageNo,@RequestParam Integer pageSize,@RequestParam String typeName){
         Page<EqType>  page  = new Page<>();
         page.setCurrent(pageNo);
         page.setSize(pageSize);
          LambdaQueryWrapper<EqType> lambdaQueryWrapper =  new LambdaQueryWrapper<>();
         lambdaQueryWrapper.eq(EqType::getDr,0);
         lambdaQueryWrapper.eq(EqType::getTypeName,typeName);
         return PageUtils.page(iEqTypeService.page(page,lambdaQueryWrapper));
     }


}
