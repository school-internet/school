package com.school.internet.equip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
public class EqRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pk_rule",type = IdType.ASSIGN_ID)
    private String pkRule;

    private String ruleValue;

    private String fkEquiptype;

    private String fkEquipdoc;

    private String fkInstruct;

    private String imei;

    private BigDecimal dr;

    private String ts;

    private String instructValue;

    @TableField(exist = false)
    private Integer port;


    @TableField(exist = false)
    private Integer state;

//生效时间
    private String effectivedate;


    private String  ruletime;


    public static void main(String[] args) {
        try {
            String  a ="2020.9.4";
            a=   a.replace(".","-");
            System.out.print("2222="+a);
            SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-HH-dd");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
