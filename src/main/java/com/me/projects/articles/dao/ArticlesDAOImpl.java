package com.me.projects.articles.dao;

import com.me.projects.articles.model.Article;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesDAOImpl implements ArticlesDAO {
    private final MongoOperations operations;

    public ArticlesDAOImpl(final MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public List<Article> getAllArticles() {
        return this.operations.findAll(Article.class);
    }

}
