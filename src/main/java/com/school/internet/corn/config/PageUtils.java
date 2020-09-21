package com.school.internet.corn.config;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public final class PageUtils {

    private PageUtils() {
    }


    public static <T> MSPage<T> page(IPage<T> pageInfo) {
        MSPage<T> page = new MSPage<>();
        page.setPageNum((int) pageInfo.getCurrent());
        page.setPageSize((int) pageInfo.getSize());
        page.setPages((int) pageInfo.getPages());
        page.setTotal(pageInfo.getTotal());
        page.setRows(pageInfo.getRecords());
        return page;
    }

    public static <T,E> MSPage<E> page(MSPage<T> pageInfo, List<E> data){
        MSPage<E> page = new MSPage<>();
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setPages(pageInfo.getPages());
        page.setTotal(pageInfo.getTotal());
        page.setRows(data);
        return page;
    }

}
