package com.snsprj.service;

/**
 * Created by skh on 2017/6/12.
 */
public interface ICategoryService {

    /**
     * add a new category
     * @param name category name
     * @param parentId parent id
     * @return int
     */
    public int insertCategory(String name,int parentId);

    public int getCategotyDepth(int categoryId);
}
