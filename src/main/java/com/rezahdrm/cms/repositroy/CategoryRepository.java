package com.rezahdrm.cms.repositroy;

import com.rezahdrm.cms.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c.id, c.title from Category c")
    List<Category> findALLCreatePost();
}
