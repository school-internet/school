package com.school.internet.corn.config;


import lombok.Data;

import java.io.Serializable;

@Data
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer  pageNo;

    private Integer pageSize;
}
