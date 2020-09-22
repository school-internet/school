package com.school.internet.equip.entity;

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
public class EqType   implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String pkEquiptype;

    private String typeCode;

    private String typeName;

    private BigDecimal state;

    private int dr;

    private String ts;


}
