package com.rezahdrm.cms.model;

import javax.persistence.Entity;
import java.sql.Timestamp;
@Entity
public class Post extends HistoricalColumn{

    private String title;
    private String slug;
    private String description;
    private String metaDescription;
    private String metaKeyword;
    private Status status;
    private Long userId;
    private Long photoId;
    private Long categoryId;



    enum Status{
        ACTIVATE,DEACTIVATE
    }
}
