package com.rezahdrm.cms.config;

import com.ibm.icu.text.SimpleDateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Lazy
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss");
    }

}
