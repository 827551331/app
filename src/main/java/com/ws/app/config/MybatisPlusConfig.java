package com.ws.app.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ws.app.dao")
public class MybatisPlusConfig {

}

