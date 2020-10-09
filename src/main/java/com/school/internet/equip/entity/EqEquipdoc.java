package com.school.internet.equip.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

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
@KeySequence(value = "SEQ_ORACLE_STRING_KEY", clazz = String.class)
public class EqEquipdoc  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_equipdoc",type = IdType.ASSIGN_ID)
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
    @TableLogic
    private Integer dr;

    private BigDecimal random;


    private Timestamp ts;

    @Version
    private Integer  version;


}
