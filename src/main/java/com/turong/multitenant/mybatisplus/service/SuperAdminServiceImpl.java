package com.turong.multitenant.mybatisplus.service;

import com.turong.multitenant.mybatisplus.config.AppContextHolder;
import com.turong.multitenant.mybatisplus.entity.User;
import com.turong.multitenant.mybatisplus.mapper.SuperAdminMapper;
import com.turong.multitenant.mybatisplus.config.SupportedTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    private SuperAdminMapper mapper;

    SuperAdminServiceImpl(@Autowired final SuperAdminMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<User> searchAllUsers(final SearchCriteria criteria) {
        if (Objects.isNull(criteria)) {

        }

        return Arrays
                .stream(SupportedTenant.values())
                .map(SupportedTenant::getTenant)
                .map(this::searchUsersInTenant)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    private List<User> searchUsersInTenant(final String tenant) {
        AppContextHolder.setTenant(tenant);
        return mapper.findUsers();
    }

}
