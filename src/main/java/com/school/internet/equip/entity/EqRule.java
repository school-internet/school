package com.school.internet.equip.entity;

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
public class EqRule implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pkRule;

    private String ruleValue;

    private String fkEquiptype;

    private String fkEquipdoc;

    private String fkInstruct;

    private BigDecimal dr;

    private String ts;


}
