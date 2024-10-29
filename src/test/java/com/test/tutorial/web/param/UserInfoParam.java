package com.test.tutorial.web.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User info request param.
 *
 * @author	Bert Lee
 * @version 2014-8-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoParam extends UserIdParam {
    // 变量名与请求参数名不一样，在 @RequestBody 中用到
    // https://github.com/FasterXML/jackson-annotations#annotations-for-renaming-properties
    /**
     * 用户名称
     */
    @JsonProperty(value = "userName", required = true)
    @NotNull(message = "'userName' param is null")
    @Size(min = 1, message = "'userName' param is empty")
	private String name;
}
