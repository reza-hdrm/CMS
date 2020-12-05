package com.rezahdrm.cms.repository;

import com.rezahdrm.cms.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Modifying
    @Transactional
    @Query(value = "update Photo set deletedAt=:pDeletedAt where id=:pId")
    void setDeletedAt(@Param("pId") Long id, @Param("pDeletedAt") Date deletedAt);

    @Modifying
    @Transactional
    @Query(value = "update Photo set deletedAt=null where id=:pId")
    void setNullDeletedAt(@Param("pId") Long id);

    List<Photo> findAllByDeletedAtIsNull();

}
