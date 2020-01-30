package com.me.projects.articles.model;

import java.util.Date;

public class Article {
    private int id;
    private String author;
    private String content;
    private String title;
    private Date date;

    public Article(final int id, final String author, final String content, final String title, final Date date) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

}
