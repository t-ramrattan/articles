package com.me.projects.articles.dao;

import com.me.projects.articles.model.Article;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ArticlesDAO {
    List<Article> getAllArticles() throws DataAccessException;
    List<Article> getSlice(long id, int size) throws DataAccessException;
}
