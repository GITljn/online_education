package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.vo.OrderMemberInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author ljn
 * @since 2021-12-28
 */
@RestController
@RequestMapping("/educenter/member")
//@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("/login")
    public R login(@RequestBody UcenterMember member) {
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }

    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("item", member);
    }

    @GetMapping("/getMemberInfoById/{memberid}")
    public Map<String, Object> getMemberInfoById(@PathVariable String memberid) {
        UcenterMember member = memberService.getById(memberid);
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", member.getNickname());
        map.put("avatar", member.getAvatar());
        return map;
    }

    @GetMapping("/selectOrderMemberInfo/{memberId}")
    public OrderMemberInfoVo selectOrderMemberInfo(@PathVariable String memberId) {
        UcenterMember member = memberService.getById(memberId);
        OrderMemberInfoVo memberInfoVo = new OrderMemberInfoVo();
        BeanUtils.copyProperties(member, memberInfoVo);
        return memberInfoVo;
    }

    @GetMapping("/selectRegisterNumByDay/{day}")
    public R selectRegisterNumByDay(@PathVariable String day) {
        int num = memberService.selectRegisterNumByDay(day);
        return R.ok().data("registerNum", num);
    }

}

