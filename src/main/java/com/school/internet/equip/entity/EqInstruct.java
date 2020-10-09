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
public class EqInstruct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String pkInstruct;

    private String instructName;

    private String instructValue;

    private String fkEquiptype;

    private String fkEquipdoc;

    private int dr;

    private String ts;

    public static void main(String[] args) {
        double a =(13777-4000)/21d;
        System.out.print(new BigDecimal(a));
    }


}
