package com.snsprj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    private ProductMapper productMapper;

    /**
     * 获取某分类下所有商品（分页）
     * @param categoryId categoryId
     * @param status status 1:在售；2：下架；3：删除
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo
     */
    public PageInfo getProductByCategoryId(Integer categoryId,Byte status,
                                                int pageNum,int pageSize ){

        // 紧跟着的第一个select方法会被分页
        PageHelper.startPage(pageNum,pageSize);

        List<Product> productList = productMapper.selectByCategoryId(categoryId,status);

        PageInfo page = new PageInfo(productList);
        return page;
    }
}
