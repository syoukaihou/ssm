package com.snsprj.service.impl;

import com.snsprj.common.ConstCode;
import com.snsprj.common.ErrorCode;
import com.snsprj.dao.CategoryMapper;
import com.snsprj.dto.Category;
import com.snsprj.service.ICategoryService;
import com.snsprj.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by skh on 2017/6/12.
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CategoryMapper categoryMapper;


    @Override
    public int insertCategory(CategoryVo categoryVo) {

        String name = categoryVo.getCategoryName();
        int parentId = categoryVo.getParentId();

        // TODO check category name is exist

        if(parentId != 0){
            // check parentId is available
            Category parentCategory = categoryMapper.selectByPrimaryKey(parentId, ConstCode.CATEGORY_STATUS_ACTIVE);
            if(parentCategory == null){
                return ErrorCode.ILLEGAL_PARENTID;
            }

            // limit depth
            int depth = this.getCategoryDepth(parentId);
            if(ConstCode.CATEGORY_DEPTH <= depth){
                return ErrorCode.ILLEGAL_PARENTID;
            }
        }

        Category category = new Category();
        category.setCategoryName(name);
        category.setParentId(parentId);
        category.setStatus(ConstCode.CATEGORY_STATUS_ACTIVE);

        categoryMapper.insert(category);

        return ConstCode.ZERO;
    }

    public void getCategoryList() {


        // first: get all parentId=0 records
        List<Category> categoryList = categoryMapper.selectByParentId(0, ConstCode.CATEGORY_STATUS_ACTIVE);

        // second: traverse list
        for (int index = 0; index < categoryList.size(); index++) {
            Category category = categoryList.get(index);
            int categoryId = category.getId();

        }
    }



    /**
     * get depth of category,
     * depth=1 when record's parentId=0.
     * @param categoryId category id
     * @return int
     */
    private int getCategoryDepth(int categoryId){

        Category category = categoryMapper.selectByPrimaryKey(categoryId,ConstCode.CATEGORY_STATUS_ACTIVE);

        int parentId = category.getParentId();

        if(parentId == 0){
            return 1;
        }else{
            return 1 + getCategoryDepth(parentId);
        }
    }










}
