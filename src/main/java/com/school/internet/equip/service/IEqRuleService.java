package com.school.internet.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.school.internet.corn.config.MSPage;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.entity.EqRuleVo;
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

public interface IEqRuleService extends IService<EqRule> {


     MSPage<EqRuleVo> all(Page<EqRuleVo> page, EqRule eqRule);
}
