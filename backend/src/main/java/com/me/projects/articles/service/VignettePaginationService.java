package com.me.projects.articles.service;

import com.me.projects.articles.dao.ArticlesDAO;
import com.me.projects.articles.model.Paginate;
import com.me.projects.articles.model.Vignette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VignettePaginationService implements PaginationService<Vignette> {

  private final ArticlesDAO articlesDAO;

  @Autowired
  public VignettePaginationService(final ArticlesDAO articlesDAO) {
    this.articlesDAO = articlesDAO;
  }

  @Override
  public Paginate<Vignette> getPagination(long page, int pageSize) {
    return null;
  }

}
