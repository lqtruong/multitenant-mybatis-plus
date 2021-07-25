package com.turong.multitenant.mybatisplus.mapper;

import com.turong.multitenant.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SuperAdminMapper {

    @Select("select * from users")
    List<User> findUsers();

}
