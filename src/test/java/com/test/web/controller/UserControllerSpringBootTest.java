package com.test.web.controller;

import com.test.Application;
import com.test.MockMvcRequestUtils;
import com.test.tutorial.service.UserService;
import com.test.tutorial.web.controller.UserController;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

/**
 * Test of {@link UserController} by HTTP API.
 * <pre>
 * <a href="https://juejin.cn/post/7067402739134758948">
 *     SpringBoot+Junit5+Assertj+Mockito的单元测试</a>
 * <a href="https://juejin.cn/post/7036140165944836104">
 *     SpringBoot实战：JUnit5+MockMvc+Mockito做好单元测试</a>
 *
 * 启动 Spring Boot 和 Tomcat 容器，耗时较长。
 * Started * in 4.148 seconds (JVM running for 6.623)
 *
 * SpringBootTest 注解已经添加 @ExtendWith(SpringExtension.class)
 * </pre>
 *
 * @author lihuagang
 * @since  2023/5/24
 */
@Slf4j
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
//@EnableWebMvc
//@ExtendWith(MockitoExtension.class)
public class UserControllerSpringBootTest {
    @Resource
    private MockMvc mockMvc;

    // mocked service (被依赖的服务)
    @MockBean
    private UserService userService;

    // 组件自动装配
//    @Resource
//    private UserService userService;

    public UserControllerSpringBootTest() {
        log.info("create UserControllerSpringBootTest instance");
    }

    @BeforeEach
    void setUp() {
        // 组件自动装配
//        ReflectionTestUtils.setField(this, "userService", userService);
    }

    @Test
    void getUserName() throws Exception {
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
