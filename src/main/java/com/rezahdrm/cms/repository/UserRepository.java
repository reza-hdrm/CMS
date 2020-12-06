package com.rezahdrm.cms.repository;

import com.rezahdrm.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(value = "update User set deletedAt=:pDeletedAt where id=:pId")
    void setDeletedAt(@Param("pId") Long id, @Param("pDeletedAt") Date deletedAt);

    @Modifying
    @Transactional
    @Query(value = "update User set deletedAt=null where id=:pId")
    void setNullDeletedAt(@Param("pId") Long id);

    @Transactional
    List<User> findAllByDeletedAtIsNull();

    User findByEmail(String email);
}
