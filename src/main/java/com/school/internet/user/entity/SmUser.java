package com.school.internet.user.entity;

import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private String orgCode;

    private String orgType;

    private String orgName;

    private Integer status;

    private Integer type;

    private String password;

    private String encryptpara;

    private String creater;

    private LocalDate createTime;

    private String certType;

    private String certTime;

    private String certNo;

    private String idCardNo;

    private String sex;

    private String phone;

    private String political;

    private String centerCode;

    private String nation;

    private String education;

    private String centerName;

    private String duty;

    private String lawType;

    private String workDate;

    private String birthday;

    private String major;

    private Integer isPc;

    private String lawEf;

    private String certInstitution;

    private Integer orderNum;


}
