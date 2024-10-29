package com.test.tutorial.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.tutorial.repository.entity.Organization;
import org.apache.ibatis.annotations.Mapper;

/**
 * 组织实体映射器
 *
 * @author lihuagang
 * @since  2023/5/24
 * @see com.baomidou.mybatisplus.core.mapper.BaseMapper
 */
@Mapper
public interface OrgMapper extends BaseMapper<Organization> {
    //
}
