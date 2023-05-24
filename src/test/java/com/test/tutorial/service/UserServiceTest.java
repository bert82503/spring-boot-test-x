package com.test.tutorial.service;

import com.test.tutorial.repository.OrgMapper;
import com.test.tutorial.repository.UserMapper;
import com.test.tutorial.repository.entity.Organization;
import com.test.tutorial.repository.entity.User;
import com.test.tutorial.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test of {@link UserServiceImpl}.
 * <pre>
 * <a href="https://juejin.cn/post/7067402739134758948">
 *     SpringBoot+Junit5+Assertj+Mockito的单元测试</a>
 *
 * SpringBootTest 注解会将整个程序运行起来，
 * 如果代码多或者是连接数据库的话，SpringBootTest 启动速度会很慢，不满足单元测试的快速测试要求。
 *
 * 使用 SpringJunitConfig 快速运行单元测试
 * SpringJunitConfig 和 SpringBootTest 相比，SpringJunitConfig 可以只指定需要加载到 spring 容器中的类，
 * 单个测试中用不到的类，不会加载到 spring 容器中。
 *
 * Mock的本质是让我们写更加稳定的单元测试，隔离功能、时间、环境、数据等因素对单元测试的影响，
 * 使结果变的可预测，做到真正的"单元"测试。
 * </pre>
 *
 * @author lihuagang
 * @date 2023/5/24
 */
@SpringJUnitConfig(UserServiceImpl.class)
class UserServiceTest {

    @Resource
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private OrgMapper orgMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUserName() {
    }

    @Test
    void updateUserName() {
    }

    @Test
    void getOrgName() {
        // 构造数据
        User user = new User()
                .setId(3L)
                .setUserName("Edward Lee")
                .setOrgId(13L);
        Organization org = new Organization()
                .setId(13L)
                .setOrgName("OpenSource");
        // 准备
        // Given
        when(userMapper.selectById(anyLong())).thenReturn(user);
        when(orgMapper.selectById(anyLong())).thenReturn(org);

        userService = new UserServiceImpl(userMapper, orgMapper);

        // 执行
        // When
        String orgName = userService.getOrgName(3L);

        // 验证
        // Then
        assertThat(orgName).isEqualTo("OpenSource");
        verify(userMapper, times(1)).selectById(3L);
        verify(orgMapper).selectById(13L);
    }
}
