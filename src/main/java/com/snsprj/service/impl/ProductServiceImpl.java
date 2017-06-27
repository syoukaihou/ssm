package com.snsprj.service.impl;

import com.github.pagehelper.PageHelper;
import com.snsprj.dao.ProductMapper;
import com.snsprj.dto.Product;
import com.snsprj.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by skh on 2017/6/27.
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapper productMapper;

    /**
     * 获取某分类下所有商品（分页）
     * @param categoryId categoryId
     * @param status status 1:在售；2：下架；3：删除
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return List
     */
    public List<Product> getProductByCategoryId(Integer categoryId,Byte status,
                                                int pageNum,int pageSize ){

        PageHelper.startPage(pageNum,pageSize);

        List<Product> productList = productMapper.selectByCategoryId(categoryId,status);

        return productList;
    }
}
