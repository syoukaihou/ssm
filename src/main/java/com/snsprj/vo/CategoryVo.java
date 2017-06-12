package com.snsprj.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by John on 2017/6/13.
 */
public class CategoryVo {

    @NotNull
    @Size(min=2,max = 6)
    private String categoryName;

    private int parentId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
