package com.rezahdrm.cms.repositroy;

import com.rezahdrm.cms.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
