package com.school.internet.equip.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.school.internet.corn.config.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EqEquipdoc extends PageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String pkEquipdoc;

    private String equipName;

    private String equipCode;

    private String fkEquiptype;

    private BigDecimal state;

    private String location;

    private String imei;

    private String ip;

    private String vuserdef1;

    private String vuserdef2;

    private String vuserdef3;

    private String vuserdef4;

    private String vuserdef5;

    private BigDecimal dr;

    private String ts;


}
