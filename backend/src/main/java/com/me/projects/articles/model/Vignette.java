package com.me.projects.articles.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Vignette {
    @Id
    private long id;
    private String author;
    private String title;
    private String caption;
    private String imgUrl;

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
