package com.snsprj.service;

import com.snsprj.vo.CategoryVo;

/**
 * Created by skh on 2017/6/12.
 */
public interface ICategoryService {

    /**
     * add a new category
     * @param categoryVo CategoryVo
     * @return int
     */
    public int insertCategory(CategoryVo categoryVo);

}
