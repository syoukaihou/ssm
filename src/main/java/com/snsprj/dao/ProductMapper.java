package com.snsprj.dao;

import com.snsprj.dto.Product;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

    /**
     * 根据categoryId查询商品
     * @param categoryId 分类id
     * @param status 商品状态
     * @return List
     */
    List<Product> selectByCategoryId(@Param("categoryId") Integer categoryId,
                                     @Param("status") Byte status);

    /**
     * vane大于0表示大于；0表示等于；小于0表示小于；
     * status 商品状态。1：在售；2：下架；3：删除；
     * @param vane vane
     * @param date 时间
     * @param status 商品状态
     * @return List
     */
    List<Product> selectByUpdatedAt(@Param("vane") Integer vane,
                                    @Param("date") Date date,
                                    @Param("status") Byte status);
}