package com.atguigu.eduorder.client;

import com.atguigu.servicebase.vo.OrderMemberInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("/educenter/member/selectOrderMemberInfo/{memberId}")
    OrderMemberInfoVo selectOrderMemberInfo(@PathVariable("memberId") String memberId);
}
