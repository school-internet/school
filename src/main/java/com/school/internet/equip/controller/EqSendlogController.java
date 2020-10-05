package com.school.internet.equip.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.corn.config.MSPage;
import com.school.internet.corn.config.PageUtils;
import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.entity.EqSendlog;
import com.school.internet.equip.entity.EquipdocVO;
import com.school.internet.equip.service.IEqSendlogService;
import com.school.internet.equip.service.impl.EqSendlogServiceImpl;
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
@RequestMapping("/equip/eq-sendlog")
public class EqSendlogController {


    @Autowired
    private IEqSendlogService  iEqSendlogService;

    @PostMapping("pageEqSend")
    public MSPage<EqSendlog> pagelist(Integer pageNo, Integer pageSize, EqSendlog eqSendlog){
        Page<EqSendlog>  page  = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<EqSendlog> queryWrapper  = new QueryWrapper<>();
//        queryWrapper.eq("fkEquipdoc",eqSendlog.getFkEquipdoc());
        return PageUtils.page(iEqSendlogService.page(page,queryWrapper));

    }
}
