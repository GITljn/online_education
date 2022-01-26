package com.atguigu.eduorder.service;

import com.atguigu.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author ljn
 * @since 2022-01-05
 */
public interface PayLogService extends IService<PayLog> {

    void insertPayInfo(Map<String, String> map);

    Map<String, Object> selectPayInfoByOrderNo(String orderNo);

    Map<String, String> selectTradeStateByOrderNo(String orderNo);
}
