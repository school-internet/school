package com.school.internet.equip.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class EqReceive implements Serializable {


    @TableId(value = "pk_receive",type = IdType.ASSIGN_ID)
    private String pkReveive;


    private String pkEquipdoc;

    private String bodyValue;

    private String receiveValue;

    private Date createTime;

    private Integer dr;

    private Integer  state;

    private String ts;

    private String port1;

    private String port2;
    private String port3;
    private String port4;
    private String port5;
    private String port6;
    private String port7;
    private String port8;

    private String road1;
    private String road2;


    private String temp;

    private String hum;



}
