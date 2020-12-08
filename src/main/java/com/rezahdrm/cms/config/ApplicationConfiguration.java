package com.rezahdrm.cms.config;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.PersianCalendar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Scope(WebApplicationContext.SCOPE_APPLICATION)
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss");
    }

    @Bean
    @Scope(WebApplicationContext.SCOPE_APPLICATION)
    public DateFormat persianCalendar() {
        return new PersianCalendar().getDateTimeFormat(1, 2, new Locale("fa", "IR"));
    }
}
