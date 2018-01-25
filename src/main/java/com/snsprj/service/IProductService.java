package com.snsprj.service;

import com.github.pagehelper.PageInfo;
import com.snsprj.dto.Product;

/**
 * Created by skh on 2017/6/27.
 */
public interface IProductService {

    public PageInfo<Product> getProductByCategoryId(Integer categoryId, Byte status,
                                           int pageNum, int pageSize );
}
