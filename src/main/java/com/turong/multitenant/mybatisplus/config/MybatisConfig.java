package com.turong.multitenant.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.turong.multitenant.mybatisplus.mapper")
@Slf4j
public class MybatisConfig {

    @Bean(name = "tenantLineInnerInterceptor")
    public TenantLineInnerInterceptor tenantLineInnerInterceptor() {

        TenantLineInnerInterceptor innerInterceptor = new TenantLineInnerInterceptor();
        innerInterceptor.setTenantLineHandler(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                String currentTenant = AppContextHolder.getTenant();
                if (StringUtils.isBlank(currentTenant)) {
                    throw new IllegalArgumentException("The tenant must be present!");
                }
                return new StringValue(currentTenant);
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant";
            }

        });


        return innerInterceptor;
    }

    @Bean(name = "dataPermissionInterceptor")
    public DataPermissionInterceptor dataPermissionInterceptor() {
        log.debug("dataPermissionInterceptor");
        DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
        dataPermissionInterceptor.setDataPermissionHandler(new DataPermissionHandler() {
            @Override
            public Expression getSqlSegment(Expression where, String mappedStatementId) {
                log.debug("where={}", where);
                log.debug("mappedStatementId = {}", mappedStatementId);
                return null;
            }
        });
        return dataPermissionInterceptor;
    }

}
