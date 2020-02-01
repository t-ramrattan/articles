package com.me.projects.articles.dao;

import com.me.projects.articles.model.Article;
import com.me.projects.articles.model.Vignette;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesDAOImpl implements ArticlesDAO {
    private final MongoOperations operations;

    public ArticlesDAOImpl(final MongoOperations operations) {
        this.operations = operations;
    }

    @Override
    public List<Article> getAllArticles() throws DataAccessException {
        return this.operations.findAll(Article.class);

    }

    @Override
    public List<Vignette> getSlice(long id, int size) throws DataAccessException {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").gte(id)).limit(size);
        return operations.find(query, Vignette.class);
    }

}
