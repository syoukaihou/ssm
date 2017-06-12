package com.snsprj.dao;

import com.snsprj.dto.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(@Param("id") Integer id,@Param("status") byte status);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * get record list by parent id
     * @param parentId parent id
     * @param status status
     * @return List
     */
    List<Category> selectByParentId(@Param("parentId") Integer parentId,
                                    @Param("status") byte status);
}