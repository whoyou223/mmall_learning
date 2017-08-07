package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * Created by feng on 2017/7/30.
 */
public interface ICategoryService {


    // 添加品类
    ServerResponse addCategory(String categoryName, Integer parentId);

    // 更新品类名称
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    // 查询子节点
    ServerResponse<List<Category>> getChildrenParalleCategory(Integer categoryId);

    // 递归查询本节点和子节点id
    ServerResponse<List<Integer>> selectCategoryAndChildrenCategory(Integer categoryId);

}
