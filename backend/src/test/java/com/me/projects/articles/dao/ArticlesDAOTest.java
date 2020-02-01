package com.me.projects.articles.dao;

import com.me.projects.articles.model.Article;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;

public class ArticlesDAOTest {
    private ArticlesDAO daoUnderTest;
    private MongoOperations operations;

    @Before
    public void init() {
        this.operations = mock(MongoOperations.class);
        this.daoUnderTest = new ArticlesDAOImpl(this.operations);
    }

    @Test
    public void should_fetch_all_articles_from_data_source() {
        final List<Article> expected = Collections.singletonList(mock(Article.class));
        doReturn(expected).when(this.operations).findAll(eq(Article.class));
        final List<Article> actual = this.daoUnderTest.getAllArticles();
        assertSame(actual, expected);
    }

}
