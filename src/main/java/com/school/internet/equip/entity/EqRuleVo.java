package com.school.internet.equip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
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

public class EqRuleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pkRule;

    private String ruleValue;

    private String fkEquiptype;

    private String fkEquipdoc;

    private String fkInstruct;

    private String imei;

    private BigDecimal dr;

    private String ts;

    private String instructValue;

    private Integer port;

    private Integer state;

    private String ruletime;

    private String effectivedate;


}
