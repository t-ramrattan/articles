package com.me.projects.articles.controller;

import com.me.projects.articles.dao.ArticlesDAO;
import com.me.projects.articles.model.Article;
import com.me.projects.articles.model.Paginate;
import com.me.projects.articles.model.Vignette;
import com.me.projects.articles.service.PaginationServiceException;
import com.me.projects.articles.service.VignettePaginationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

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

        assertEquals(OK, responseEntity.getStatusCode());
        assertSame(paginate, responseEntity.getBody());
    }

    @Test
    public void should_return_internal_server_error_when_get_paginate_fails() throws PaginationServiceException {
        final Integer page = 0;
        final Integer pageSize = 1;

        doThrow(mock(PaginationServiceException.class))
                .when(this.paginationService)
                .getPagination(eq(page), eq(pageSize));

        final ResponseEntity<Paginate<Vignette>> responseEntity = this
                .controllerUnderTest
                .getPaginated(page, pageSize);
        assertEquals(INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void should_fetch_article_by_id() {
        final Long id = 0L;
        this.controllerUnderTest.getArticle(id);
        verify(this.articlesDAO).getArticle(id);
    }

    @Test
    public void should_return_internal_servver_error_when_get_article_by_id_fails() {
        final long id = 0;

        doThrow(mock(DataAccessException.class)).when(this.articlesDAO).getArticle(eq(id));

        final ResponseEntity<Article> responseEntity = this.controllerUnderTest.getArticle(id);

        assertEquals(INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void should_return_null_when_article_id_has_no_match() {
        final long id = 1;

        doReturn(Optional.empty()).when(this.articlesDAO).getArticle(eq(id));

        final ResponseEntity<Article> responseEntity = this.controllerUnderTest.getArticle(id);

        assertNull(responseEntity.getBody());
    }

}
