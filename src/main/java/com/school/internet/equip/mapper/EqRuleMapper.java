package com.school.internet.equip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.internet.equip.entity.EqRule;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
public interface EqRuleMapper extends BaseMapper<EqRule> {

    public List<EqRule> all();
}
