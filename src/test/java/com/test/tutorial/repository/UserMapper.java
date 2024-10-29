package com.test.tutorial.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.tutorial.repository.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户实体映射器
 *
 * @author Edward Lee
 * @since  2014-7-25
 * @see com.baomidou.mybatisplus.core.mapper.BaseMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //
}
