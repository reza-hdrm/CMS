package com.rezahdrm.cms.repositroy;

import com.rezahdrm.cms.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Modifying
    @Transactional
    @Query(value = "update Photo set deletedAt=:pDeletedAt where id=:pId")
    void setDeletedAt(@Param("pId") Long id, @Param("pDeletedAt") Date deletedAt);
}
