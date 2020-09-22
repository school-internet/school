package com.school.internet.equip.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.corn.config.MSPage;
import com.school.internet.corn.config.PageUtils;
import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.service.IEqEquipdocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
@RestController
@RequestMapping("/equip/eq-equipdoc")
public class EqEquipdocController {

    @Autowired
    private IEqEquipdocService iEqEquipdocService;

    @PostMapping("pageEqEquipdoc")
    public MSPage<EqEquipdoc>  pagelist(int pageNo, int pageSize){
        Page<EqEquipdoc>  page  = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
       return PageUtils.page(iEqEquipdocService.page(page));

    }

}
