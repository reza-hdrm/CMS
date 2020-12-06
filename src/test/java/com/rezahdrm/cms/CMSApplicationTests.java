package com.rezahdrm.cms;

import com.rezahdrm.cms.repository.UserRepository;
import com.rezahdrm.cms.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CMSApplicationTests {
    @Autowired
    UserRepository userRepository;
	@Test
    @Transactional(readOnly = true)
	void contextLoads() {
        userRepository.findByEmail("reza@gmail.com").getRoles().forEach(System.out::println);
    }
}
