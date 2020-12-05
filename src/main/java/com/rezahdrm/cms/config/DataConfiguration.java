package com.rezahdrm.cms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.rezahdrm.cms.repository")
public class DataConfiguration {

}
