package com.me.projects.articles.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.me.projects.articles.dao.ArticlesDAO;
import com.me.projects.articles.model.Paginate;
import com.me.projects.articles.model.Vignette;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class VignettePaginationServiceTest {

  private VignettePaginationService serviceUnderTest;
  private ArticlesDAO articlesDAO;

  @Before
  public void init() {
    this.articlesDAO = mock(ArticlesDAO.class);
    this.serviceUnderTest = new VignettePaginationService(articlesDAO);
  }

  @Test
  public void should_return_paginated_vignettes() throws PaginationServiceException {
    final long page = 0;
    final int pageSize = 1;
    final List<Vignette> vignettes = Arrays.asList(mock(Vignette.class), mock(Vignette.class));

    doReturn(2L).when(this.articlesDAO).getNumberOfArticles();
    doReturn(vignettes.subList(0, pageSize)).when(this.articlesDAO).getSlice(0, 1);

    Paginate<Vignette> paginate = this.serviceUnderTest.getPagination(page, pageSize);

    assertEquals(1, paginate.getEntries().size());
    assertEquals(page, paginate.getCurrentPage());
    assertEquals(pageSize, paginate.getPageSize());
    assertEquals(2, paginate.getTotalPages());
  }
}
