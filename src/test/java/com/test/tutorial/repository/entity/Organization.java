package com.test.tutorial.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 组织信息表
 *
 * @author lihuagang
 * @since  2023/5/24
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_digital_organization")
public class Organization implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 组织名称
     */
    @TableField(value = "org_name")
    private String orgName;

    private static final long serialVersionUID = 1L;
}
