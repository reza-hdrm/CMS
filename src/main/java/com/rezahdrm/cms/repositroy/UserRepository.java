package com.rezahdrm.cms.repositroy;

import com.rezahdrm.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
