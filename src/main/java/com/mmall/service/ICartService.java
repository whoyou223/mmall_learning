package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * Created by feng on 2017/7/31.
 */
public interface ICartService {

    // 向购物车添加商品
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    // 更新购物车
    ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count);

    // 删除指定id的商品
    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);

    ServerResponse<CartVo> list (Integer userId);
    ServerResponse<CartVo> selectOrUnSelect (Integer userId,Integer productId,Integer checked);
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
