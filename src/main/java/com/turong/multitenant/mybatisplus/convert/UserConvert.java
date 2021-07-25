package com.turong.multitenant.mybatisplus.convert;

import com.turong.multitenant.mybatisplus.controller.AllUsersResponse;
import com.turong.multitenant.mybatisplus.controller.UserResponse;
import com.turong.multitenant.mybatisplus.controller.UserSaveRequest;
import com.turong.multitenant.mybatisplus.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserConvert {

    User toUser(final UserSaveRequest userSaveRequest);

    UserResponse toResponse(final User user);

    default AllUsersResponse toUserListResponse(final List<User> allUsers) {
        return AllUsersResponse.of(allUsers.stream()
                .filter(Objects::nonNull)
                .map(this::toResponse)
                .collect(Collectors.toList())
        );
    }
}
