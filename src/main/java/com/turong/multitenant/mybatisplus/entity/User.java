package com.turong.multitenant.mybatisplus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "users")
@Accessors(chain = true)
public class User {

    @Id
    private Long id;
    private String username;
    private String email;
    private String tenant;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate modifiedAt;

}
