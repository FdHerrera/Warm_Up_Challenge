package com.alkemy.warmup.blog.model;

import lombok.Data;

@Data
public class PostRequest {

    private String title;
    private String content;
    private String urlImage;
    private String category;

}
