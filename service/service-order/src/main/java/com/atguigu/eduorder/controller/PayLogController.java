package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author ljn
 * @since 2022-01-05
 */
@RestController
@RequestMapping("/eduorder/paylog")
//@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    @GetMapping("/selectPayInfoByOrderNo/{orderNo}")
    public R selectPayInfoByOrderNo(@PathVariable String orderNo) {
        Map<String, Object> map = payLogService.selectPayInfoByOrderNo(orderNo);
        return R.ok().data(map);

    }

    @GetMapping("/selectTradeStateByOrderNo/{orderNo}")
    public R selectTradeStateByOrderNo(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.selectTradeStateByOrderNo(orderNo);
        if (map == null) {
            return R.error().message("支付失败");
        }
        if ("SUCCESS".equals(map.get("trade_state"))) {
            payLogService.insertPayInfo(map);
            return R.ok().message("支付成功");
        }

        return R.ok().code(25000).message("支付中");
    }
}

