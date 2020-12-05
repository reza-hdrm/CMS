package com.rezahdrm.cms;

import com.rezahdrm.cms.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CMSApplicationTests {
    @Autowired
    CategoryService categoryService;
	@Test
	void contextLoads() {
        String title = categoryService.findAll().get(0).getTitle();
        System.out.println(title);
    }
}
