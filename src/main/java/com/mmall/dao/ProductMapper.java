package com.mmall.dao;

import com.mmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    // 获取商品列表
    List<Product> selectList();

    //
    List<Product> selectByNameAndProductId(@Param("productName")String productName,
                                           @Param("productId") Integer productId);

    // 通过商品名和品类id查询商品
    List<Product> selectByNameAndCategoryId(@Param(value = "productName")String productName,
                                            @Param(value = "categoryIdList")List<Integer> categoryIdList);

}