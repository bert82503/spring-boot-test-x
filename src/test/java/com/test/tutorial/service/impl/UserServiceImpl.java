package com.test.tutorial.service.impl;

import com.test.tutorial.repository.OrgMapper;
import com.test.tutorial.repository.entity.Organization;
import com.test.tutorial.repository.entity.User;
import com.test.tutorial.service.UserService;
import com.test.tutorial.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * User service implementation.
 *
 * @author Edward Lee
 * @since  2014-7-25
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

//    private final UserMapper userMapper;
//    private final OrgMapper orgMapper;

    private final UserMapper userMapper;
    private final OrgMapper orgMapper;

    public UserServiceImpl(
            UserMapper userMapper,
            OrgMapper orgMapper
    ) {
        this.userMapper = userMapper;
        this.orgMapper = orgMapper;
    }

    @Override
    public String getUserName(long userId) {
        if (userId <= 0L) {
            return "";
        }

        User user = userMapper.selectById(userId);
        return user != null ? user.getUserName() : "";
    }

    @Override
    public boolean updateUserName(long userId, String userName) {
        if (userId <= 0L || !StringUtils.hasLength(userName)) {
            log.warn("param of 'updateUserName' is invalid, userId: {}, userName: {}",
                    userId, userName);
            return false;
        }

        User user = new User()
                .setId(userId)
                .setUserName(userName);
        int result = userMapper.updateById(user);
        return result > 0;
    }

    @Override
    public String getOrgName(long userId) {
        return Optional.of(userId)
                .map(userMapper::selectById)
                .map(User::getOrgId)
                .map(orgMapper::selectById)
                .map(Organization::getOrgName)
                .orElse(null);
    }
}
