package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Map;

public interface IOrderService {

    ServerResponse<Map<String, String>> pay(Long orderNo, Integer userId, String path);

    ServerResponse<String> aliCallback(Map<String, String> params);

    ServerResponse<Boolean> queryOrderPayStatus(Integer userId, Long orderNo);

}
