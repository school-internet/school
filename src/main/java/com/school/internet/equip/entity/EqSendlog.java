package com.school.internet.equip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@EqualsAndHashCode(callSuper = false)
public class EqSendlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_sendlog",type = IdType.ASSIGN_ID)
    private String pkSendlog;

    private String sendTime;

    private String fkEquipdoc;

    private String instructValue;

    private String resultValue;

    private int userId;

    private Integer state;

    private Integer dr;

    private String ts;


}
