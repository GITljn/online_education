package com.atguigu.educenter.mapper;

import com.atguigu.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author ljn
 * @since 2021-12-28
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int selectRegisterNumByDay(String day);
}
