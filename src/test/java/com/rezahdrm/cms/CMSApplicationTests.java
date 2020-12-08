package com.rezahdrm.cms;

import com.rezahdrm.cms.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CMSApplicationTests {
    @Autowired
    EmailService emailService;

    @Test
    @Transactional(readOnly = true)
    void contextLoads() {
        emailService.send("rezacolonelup@gmail.com", "testSubjet", "testText");
    }
}
