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
public class EqSendlog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pkSendlog;

    private String sendTime;

    private String fkEquipdoc;

    private String instructValue;

    private String resultValue;

    private BigDecimal state;

    private BigDecimal dr;

    private String ts;


}
