package com.school.internet.equip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.internet.equip.entity.EqRule;
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
public interface EqRuleMapper extends BaseMapper<EqRule> {

    IPage <EqRule> all(Page<EqRule> page,@Param("model") EqRule eqRule);
}
