package com.me.projects.articles.service;

import com.me.projects.articles.model.Paginate;

public interface PaginationService<T> {

  Paginate<T> getPagination(long page, int pageSize);

}
