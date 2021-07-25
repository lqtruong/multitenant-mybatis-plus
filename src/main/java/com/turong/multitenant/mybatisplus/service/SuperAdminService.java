package com.turong.multitenant.mybatisplus.service;

import com.turong.multitenant.mybatisplus.entity.User;

import java.util.List;

public interface SuperAdminService {

    List<User> searchAllUsers(final SearchCriteria criteria);

}
