package com.test.tutorial.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户信息表
 *
 * @author Edward Lee
 * @since  2014-7-25
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_digital_user")
public class User implements Serializable {
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 用户名称
	 */
	@TableField(value = "user_name")
	private String userName;

	/**
	 * 组织id
	 */
	@TableField(value = "org_id")
	private Long orgId;

	private static final long serialVersionUID = 1L;
}
