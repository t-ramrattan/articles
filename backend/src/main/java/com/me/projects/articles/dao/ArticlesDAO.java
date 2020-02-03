package com.me.projects.articles.dao;

import com.me.projects.articles.model.Article;
import com.me.projects.articles.model.Vignette;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface ArticlesDAO {
    Optional<Article> getArticle(Long id) throws DataAccessException;

    List<Vignette> getSlice(int index, int size) throws DataAccessException;

    long getNumberOfArticles() throws DataAccessException;
}
