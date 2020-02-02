package com.me.projects.articles.controller;

import com.me.projects.articles.dao.ArticlesDAO;
import com.me.projects.articles.model.Paginate;
import com.me.projects.articles.model.Vignette;
import com.me.projects.articles.service.PaginationServiceException;
import com.me.projects.articles.service.VignettePaginationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArticleRestControllerTest {

    private ArticleController controllerUnderTest;
    private VignettePaginationService paginationService;
    private ArticlesDAO articlesDAO;

    @Before
    public void init() {
        this.paginationService = mock(VignettePaginationService.class);
        this.articlesDAO = mock(ArticlesDAO.class);
        this.controllerUnderTest = new ArticleController(
                this.paginationService,
                this.articlesDAO
        );
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

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertSame(paginate, responseEntity.getBody());
    }

    @Test
    public void should_fetch_article_by_id() {
        final Long id = 0L;
        this.controllerUnderTest.getArticle(id);
        verify(this.articlesDAO).getArticle(id);
    }

}
