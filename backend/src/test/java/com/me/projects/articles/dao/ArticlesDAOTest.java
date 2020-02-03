package com.me.projects.articles.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;

import com.me.projects.articles.model.Article;
import com.me.projects.articles.model.Vignette;
import com.mongodb.client.MongoCollection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")
public class ArticlesDAOTest {

  private ArticlesDAO daoUnderTest;
  private MongoOperations operations;

  @Value("${articles.collection.name}")
  private String collectionName;

  @Before
  public void init() {
    this.operations = mock(MongoOperations.class);
    this.daoUnderTest = new ArticlesDAOImpl(this.operations, this.collectionName);
  }

  @Test
  public void should_fetch_article_by_id() throws DataAccessException {
    final Long id = 0L;
    final Article expected = mock(Article.class);
    doReturn(expected).when(this.operations).findById(eq(id), eq(Article.class));
    final Optional<Article> actual = this.daoUnderTest.getArticle(id);
    assertSame(expected, actual.orElse(null));
  }

  @Test
  public void should_fetch_slice_of_vignette_by_index_and_size() throws DataAccessException {
    final int index = 0;
    final int size = 10;
    final List<Vignette> expected = Collections.singletonList(mock(Vignette.class));
    final Query query = new Query();
    query.addCriteria(Criteria.where("id").gte(index)).limit(size);
    doReturn(expected).when(this.operations).find(eq(query), eq(Vignette.class));
    List<Vignette> actual = this.daoUnderTest.getSlice(0, size);
    assertSame(expected, actual);
  }

  @Test
  public void should_get_number_of_articles() {
    long size = 10L;
    final MongoCollection collection = mock(MongoCollection.class);
    doReturn(collection).when(this.operations).getCollection(this.collectionName);
    doReturn(size).when(collection).countDocuments();
    assertEquals(size, this.daoUnderTest.getNumberOfArticles());
  }

}
