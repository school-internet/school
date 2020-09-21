package com.school.internet.equip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.internet.equip.entity.EqRule;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
public interface IEqRuleService extends IService<EqRule> {


    public List<EqRule> all();
}
