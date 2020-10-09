package com.school.internet.equip.entity;


import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class EquipdocVO implements Serializable {
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

    private String typeName;

    private Integer  random;


    private String  sendTime;
}
