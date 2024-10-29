package com.test.tutorial.web.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * User id request param.
 * <p/>
 * Validating input received from the user to maintain data integrity is
 * an important part of application logic.
 * 通过验证用户的输入来维护数据完整性是应用逻辑的重要组成部分。
 * <a href="https://docs.oracle.com/javaee/7/tutorial/partbeanvalidation.htm">
 *     Bean Validation - The Java EE Tutorial</a>
 *
 * @author	Bert Lee
 * @version 2014-8-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class UserIdParam extends AbstractParam {
    /**
     * 用户身份
     */
    @JsonProperty(value = "userId", required = true)
    @NotNull(message = "'userId' param is null")
    // 4.3. Message interpolation -《JSR 303: Bean Validation》
    @Min(value = 1, message = "'userId' param must be great or equal than {value}")
	private long userId;
}
