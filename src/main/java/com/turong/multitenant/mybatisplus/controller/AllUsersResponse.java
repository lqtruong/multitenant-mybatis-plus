package com.turong.multitenant.mybatisplus.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllUsersResponse {

    private List<UserResponse> users;

    @JsonIgnore
    public static AllUsersResponse of(final List<UserResponse> users) {
        AllUsersResponse res = new AllUsersResponse();
        res.setUsers(users);
        return res;
    }

}
