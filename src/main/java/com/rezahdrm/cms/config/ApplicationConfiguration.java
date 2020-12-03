package com.rezahdrm.cms.config;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.PersianCalendar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.Locale;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Lazy
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss");
    }

    @Bean
    public DateFormat persianCalendar() {
        return new PersianCalendar().getDateTimeFormat(1, 2, new Locale("fa", "IR"));
    }

}
