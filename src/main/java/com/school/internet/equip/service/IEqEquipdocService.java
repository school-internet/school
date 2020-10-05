package com.school.internet.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.entity.EquipdocVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
public interface IEqEquipdocService extends IService<EqEquipdoc> {


    IPage<EquipdocVO>  pageEquipdoc(Page page, EqEquipdoc eqEquipdoc);


    List<EquipdocVO> selectEquipdoc(String imei);
}
