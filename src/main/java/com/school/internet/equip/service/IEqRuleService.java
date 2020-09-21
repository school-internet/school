package com.school.internet.equip.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.school.internet.corn.config.MSPage;
import com.school.internet.equip.entity.EqRule;
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
@Service
public interface IEqRuleService extends IService<EqRule> {


     MSPage<EqRule> all(Page<EqRule> page);
}
