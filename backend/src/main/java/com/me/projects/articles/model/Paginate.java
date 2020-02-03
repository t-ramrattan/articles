package com.me.projects.articles.model;

import java.util.List;

public class Paginate<T> {

  List<T> entries;
  long currentPage;
  long totalPages;
  int pageSize;

  public Paginate(List<T> entries, long currentPage, int totalPages, int pageSize) {
    this.entries = entries;
    this.currentPage = currentPage;
    this.totalPages = totalPages;
    this.pageSize = pageSize;
  }

  public List<T> getEntries() {
    return entries;
  }

  public long getCurrentPage() {
    return currentPage;
  }

  public long getTotalPages() {
    return totalPages;
  }

  public int getPageSize() {
    return pageSize;
  }
}
