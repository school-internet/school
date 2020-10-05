package com.school.internet.equip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.corn.config.PageUtils;
import com.school.internet.equip.entity.EqEquipdoc;
import com.school.internet.equip.entity.EqReceive;
import com.school.internet.equip.entity.EquipdocVO;
import com.school.internet.equip.entity.ReviceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
@Mapper
public interface EqEquipdocMapper extends BaseMapper<EqEquipdoc> {


    IPage<EquipdocVO>  pageEquip(Page page, @Param("model")EqEquipdoc eqEquipdoc);

    List<EquipdocVO> selectEquip(String imei);

    ReviceVO equipselect(String pkEquipdoc);
}
