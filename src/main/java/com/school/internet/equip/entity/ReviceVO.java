package com.school.internet.equip.entity;


import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class ReviceVO {

    private String pkReveive;


    private String pkEquipdoc;

    private String bodyValue;

    private String receiveValue;

    private Date createTime;

    private Integer  state;

    //eqdoc

    private String equipName;

    private String equipCode;

    private String fkEquiptype;

    private String location;

    private String imei;

    private String ip;
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





}
