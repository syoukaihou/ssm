package com.snsprj.dao.manual;

import com.snsprj.dto.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryManualMapper {

    /**
     * get record list by parent id
     * @param parentId parent id
     * @param status status
     * @return List
     */
    List<Category> selectByParentId(@Param("parentId") Integer parentId,
                                    @Param("status") byte status);
}