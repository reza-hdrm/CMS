package com.rezahdrm.cms.repository;

import com.rezahdrm.cms.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c.id, c.title from Category c")
    List<Category> findALLCreatePost();

    @Modifying
    @Transactional
    @Query(value = "update Category set deletedAt=:pDeletedAt where id=:pId")
    void setDeletedAt(@Param("pId") Long id, @Param("pDeletedAt") Date deletedAt);

    @Modifying
    @Transactional
    @Query(value = "update Category set deletedAt=null where id=:pId")
    void setNullDeletedAt(@Param("pId") Long id);

    List<Category> findAllByDeletedAtIsNull();
}
