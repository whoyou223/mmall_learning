package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

/**
 * Created by feng on 2017/7/31.
 */
public interface IProductService {

    // 更新或新增产品
    ServerResponse saveOrUpdateProduct(Product product);

    // 修改产品销售状态
    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    // 获取产品详情
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    // 获取商品列表并进行分页
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    // 根据name和id查找商品，并分页
    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    // 获取商品详情,需判断商品是否下架
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    // 根据关键字和品类信息查询商品，并进行分页和排序
    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderby);
}
