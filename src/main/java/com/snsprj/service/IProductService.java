package com.snsprj.service;

import com.snsprj.dto.Product;

import java.util.List;

/**
 * Created by skh on 2017/6/27.
 */
public interface IProductService {

    public List<Product> getProductByCategoryId(Integer categoryId, Byte status,
                                                int pageNum, int pageSize );
}
