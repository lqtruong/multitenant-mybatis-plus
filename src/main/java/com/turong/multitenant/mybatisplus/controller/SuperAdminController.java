package com.turong.multitenant.mybatisplus.controller;

import com.turong.multitenant.mybatisplus.convert.UserConvert;
import com.turong.multitenant.mybatisplus.service.SuperAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("super")
@Slf4j
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private UserConvert userConvert;

    @GetMapping("/users")
    public ResponseEntity<AllUsersResponse> getAllUsers(@RequestHeader("x-tenant-id") final String tenant) {
        log.debug("Super Admin in tenant={}, get all users in all tenants", tenant);
        return ResponseEntity.ok(userConvert.toUserListResponse(superAdminService.searchAllUsers(null)));

    }

}
