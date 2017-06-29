package com.snsprj.service;

import com.github.pagehelper.PageInfo;

/**
 * Created by skh on 2017/6/27.
 */
public interface IProductService {

    public PageInfo getProductByCategoryId(Integer categoryId, Byte status,
                                           int pageNum, int pageSize );
}
