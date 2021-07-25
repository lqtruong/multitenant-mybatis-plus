package com.turong.multitenant.mybatisplus.service;

import com.turong.multitenant.mybatisplus.controller.UserResponse;
import com.turong.multitenant.mybatisplus.entity.User;
import com.turong.multitenant.mybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(@Autowired final UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse getUser(String id) {
        Optional<User> user = userMapper.findUser(id);
        if (!user.isPresent()) {
            return null;
        }
        User foundUser = user.get();
        return UserResponse.builder()
                .id(foundUser.getId())
                .username(foundUser.getUsername())
                .email(foundUser.getEmail())
                .tenant(foundUser.getTenant())
                .build();
    }

    @Override
    public User create(User userToCreate) {
        final int count = userMapper.insert(userToCreate);
        if (count == 0) {
            return null;
        }
        return userMapper.findUserByEmail(userToCreate.getEmail()).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public int deleteByEmail(String email) {
        return userMapper.deleteByEmail(email);
    }

    @Override
    public int deleteById(String id) {
        return userMapper.deleteById(id);
    }

}
