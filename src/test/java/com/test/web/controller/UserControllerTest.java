package com.test.web.controller;

import com.test.MockMvcRequestUtils;
import com.test.tutorial.service.UserService;
import com.test.tutorial.web.controller.UserController;
import com.test.tutorial.web.param.UserIdParam;
import com.test.tutorial.web.result.UserNameResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test of {@link UserController}.
 * <pre>
 * <a href="https://juejin.cn/post/7067402739134758948">
 *     SpringBoot+Junit5+Assertj+Mockito的单元测试</a>
 *
 * 控制器主要测试请求方式、请求地址、请求内容、返回内容、参数校验等内容，不测试业务逻辑。
 * </pre>
 *
 * @author lihuagang
 * @since  2023/5/24
 */
@SpringJUnitWebConfig(UserController.class)
@AutoConfigureMockMvc
@EnableWebMvc
//@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
    @Resource
    private UserController userController;
    @Resource
    private MockMvc mockMvc;

    // mocked service (被依赖的服务)
    @MockBean
    private UserService userService;

    @Test
    @Order(10)
    void getUserName() {
        // 数据准备
        UserIdParam userIdParam = new UserIdParam().setUserId(3L);
        // 准备-Given
        // 1. 定义"被依赖的服务"的方法行为
        when(userService.getUserName(anyLong())).thenReturn("Edward Lee");

        // 执行-When
        UserNameResult result = userController.getUserName(userIdParam);

        // 验证-Then
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(0);
        assertThat(result.getMessage()).isEqualTo("OK");
        assertThat(result.getName()).isEqualTo("Edward Lee");
        verify(userService, times(1)).getUserName(3L);
    }

    @Test
    @Order(20)
    void getUserName4MockMvc() throws Exception {
        // 准备-Given
        // 1. 定义"被依赖的服务"的方法行为
        when(userService.getUserName(anyLong())).thenReturn("Edward Lee");

        // 执行-When
        String url = "/user/getUserName?userId={userId}&ptp={ptp}";
        Object[] params = new Object[]{"3", "1.PC.1AUqk"};
        String expectedContent = "{\"code\":0,\"message\":\"OK\",\"name\":\"Edward Lee\"}";
        MockMvcRequestUtils.getMock(mockMvc, url, params, expectedContent);

        // 验证-Then
        verify(userService, times(1)).getUserName(3L);
    }
}
