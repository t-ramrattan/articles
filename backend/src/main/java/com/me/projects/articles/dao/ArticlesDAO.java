package com.me.projects.articles.dao;

import com.me.projects.articles.model.Article;
import com.me.projects.articles.model.Vignette;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ArticlesDAO {
    List<Article> getAllArticles() throws DataAccessException;
    List<Vignette> getSlice(long id, int size) throws DataAccessException;
}
