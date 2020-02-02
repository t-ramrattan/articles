package com.me.projects.articles.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;

import com.me.projects.articles.model.Paginate;
import com.me.projects.articles.model.Vignette;
import com.me.projects.articles.service.PaginationServiceException;
import com.me.projects.articles.service.VignettePaginationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleRestControllerTest {

  private ArticleController controllerUnderTest;
  private VignettePaginationService paginationService;

  @Before
  public void init() {
    this.paginationService = mock(VignettePaginationService.class);
    this.controllerUnderTest = new ArticleController(this.paginationService);
  }

  @Test
  public void should_get_paginate_from_pagination_service() throws PaginationServiceException {
    final Integer page = 0;
    final Integer pageSize = 1;
    final Paginate paginate = mock(Paginate.class);

    doReturn(paginate)
        .when(this.paginationService)
        .getPagination(eq(page), eq(pageSize));

    final ResponseEntity<Paginate<Vignette>> responseEntity = this
        .controllerUnderTest
        .getPaginated(page, pageSize);

    Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    Assert.assertSame(paginate, responseEntity.getBody());

  }

}
