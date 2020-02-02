package com.me.projects.articles.service;

import com.me.projects.articles.model.Paginate;

public interface PaginationService<T> {

  Paginate<T> getPagination(int page, int pageSize) throws PaginationServiceException;

}
