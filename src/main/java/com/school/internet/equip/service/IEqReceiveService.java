package com.school.internet.equip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.internet.equip.entity.EqReceive;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-10-02
 */
public interface IEqReceiveService extends IService<EqReceive> {


    EqReceive  selectState(String pkEquipdoc);
}
