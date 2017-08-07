package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by feng on 2017/7/30.
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param categoryName
     * @param parentId
     * @return
     */
    @Override
    public ServerResponse addCategory(String categoryName, Integer parentId){
        // 校验参数
        if (parentId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        // 设置分类属性
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        // 持久化到数据库
        int resultCount = categoryMapper.insert(category);
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName){
        // 校验参数
        if (categoryId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("更新品类名字成功");
        }else {
            return ServerResponse.createByErrorMessage("更新品类失败");
        }
    }

    public ServerResponse<List<Category>> getChildrenParalleCategory(Integer categoryId){
        List<Category> list = categoryMapper.selectCategoryByParentId(categoryId);
        if (CollectionUtils.isEmpty(list)){
            logger.info("未找到当前分类的子类");
        }
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 递归查询本节点和子节点的id
     * @param categoryId
     * @return
     */
    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenCategory(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        // 调用递归方法，查找结点和子节点
        findChild(categorySet,categoryId);
        //
        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null){
            for (Category category : categorySet){
                categoryIdList.add(category.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);

    }
    // 递归获取结点和子结点
    private Set<Category> findChild(Set<Category> categorySet, Integer categoryId){
        // 查找当前结点
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null){
            categorySet.add(category);
        }
        // 查找该节点的子节点
        List<Category> categoryList = categoryMapper.selectCategoryByParentId(categoryId);
        // 遍历
        for (Category categoryItem : categoryList){
            findChild(categorySet, categoryItem.getId());
        }
        return categorySet;
    }

}
