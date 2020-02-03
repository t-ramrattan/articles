package com.me.projects.articles.model;

import java.util.List;

public class Paginate<T> {

    private List<T> entries;
    private int currentPage;
    private int totalPages;
    private int pageSize;

    public Paginate(List<T> entries, int currentPage, int totalPages, int pageSize) {
        this.entries = entries;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
    }

    public List<T> getEntries() {
        return entries;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }
}
