package com.me.projects.articles.service;

import com.me.projects.articles.dao.ArticlesDAO;
import com.me.projects.articles.model.Paginate;
import com.me.projects.articles.model.Vignette;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        final int page = 0;
        final int pageSize = 2;
        final List<Vignette> vignettes = Arrays
                .asList(mock(Vignette.class), mock(Vignette.class), mock(Vignette.class));

        doReturn(3L).when(this.articlesDAO).getNumberOfArticles();
        doReturn(vignettes.subList(0, pageSize)).when(this.articlesDAO).getSlice(0, pageSize);

        Paginate<Vignette> paginate = this.serviceUnderTest.getPagination(page, pageSize);

        assertEquals(2, paginate.getEntries().size());
        assertEquals(page, paginate.getCurrentPage());
        assertEquals(pageSize, paginate.getPageSize());
        assertEquals(2, paginate.getTotalPages());
    }

    @Test
    public void should_request_articles_with_index_and_range_of_pagaination() throws PaginationServiceException {
        final int totalPages = 6;
        final int page = 4;
        final int pageSize = 10;

        doReturn((long) totalPages * pageSize).when(this.articlesDAO).getNumberOfArticles();

        this.serviceUnderTest.getPagination(4, pageSize);

        verify(this.articlesDAO).getSlice(eq(page * pageSize), eq(pageSize));
    }

    @Test(expected = PaginationServiceException.class)
    public void should_thrown_pagination_service_exception_when_get_number_of_articles_fails()
            throws PaginationServiceException {
        doThrow(mock(DataAccessException.class)).when(this.articlesDAO).getNumberOfArticles();
        this.serviceUnderTest.getPagination(0, 1);
    }

}
