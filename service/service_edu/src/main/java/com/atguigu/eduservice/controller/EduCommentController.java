package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.service.EduCommentService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author ljn
 * @since 2022-01-04
 */
@RestController
@RequestMapping("/eduservice/comment")
//@CrossOrigin
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;
    @Autowired
    private UcenterClient ucenterClient;

    @GetMapping("/selectCommentWithPage/{current}/{size}")
    public R selectCommentWithPage(@PathVariable long current,
                                   @PathVariable long size,
                                   String courseId) {
        Page<EduComment> pageParam = new Page<>(current, size);
        QueryWrapper<EduComment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("course_id", courseId);
        commentQueryWrapper.orderByDesc("gmt_create");
        commentService.page(pageParam, commentQueryWrapper);
        List<EduComment> commentList = pageParam.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return R.ok().data(map);
    }

    @PostMapping("/insertComment")
    public R insertComment(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            new GuliException(20001, "请先登录");
        }
        Map<String, Object> map = ucenterClient.getMemberInfoById(memberId);
        comment.setMemberId(memberId);
        comment.setNickname((String) map.get("nickname"));
        comment.setAvatar((String) map.get("avatar"));
        commentService.save(comment);
        return R.ok();
    }
}

