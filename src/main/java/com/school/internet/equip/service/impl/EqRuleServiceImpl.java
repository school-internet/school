package com.school.internet.equip.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.internet.corn.config.MSPage;
import com.school.internet.corn.config.PageUtils;
import com.school.internet.equip.entity.EqRule;
import com.school.internet.equip.mapper.EqRuleMapper;
import com.school.internet.equip.service.IEqRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
@Service
public class EqRuleServiceImpl extends ServiceImpl<EqRuleMapper, EqRule> implements IEqRuleService {

    @Autowired
    private EqRuleMapper eqRuleMapper;

    public MSPage<EqRule> all(Page<EqRule> page){
        return PageUtils.page(eqRuleMapper.all(page));
    }

}
