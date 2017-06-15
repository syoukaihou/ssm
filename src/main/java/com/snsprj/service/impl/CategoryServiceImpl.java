package com.snsprj.service.impl;

import com.snsprj.common.Const;
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
            Category parentCategory = categoryMapper.selectByPrimaryKey(parentId, Const.activeStatus);
            if(parentCategory == null){
                return Const.illegalParentId;
            }

            // limit depth
            int depth = this.getCategoryDepth(parentId);
            if(Const.categoryDepth <= depth){
                return Const.illegalParentId;
            }
        }

        Category category = new Category();
        category.setCategoryName(name);
        category.setParentId(parentId);
        category.setStatus(Const.activeStatus);

        return categoryMapper.insert(category);
    }

    public void getCategoryTree() {


        // first: get all parentId=0 records
        List<Category> categoryList = categoryMapper.selectByParentId(0, Const.activeStatus);

        // second: traverse list
        for (int index = 0; index < categoryList.size(); index++) {
            Category category = categoryList.get(index);
            int categoryId = category.getId();

        }
    }

    private void recursiveCategory(){



    }

    /**
     * get depth of category,
     * depth=1 when record's parentId=0.
     * @param categoryId category id
     * @return int
     */
    private int getCategoryDepth(int categoryId){

        Category category = categoryMapper.selectByPrimaryKey(categoryId,Const.activeStatus);

        int parentId = category.getParentId();

        if(parentId == 0){
            return 1;
        }else{
            return 1 + getCategoryDepth(parentId);
        }
    }










}