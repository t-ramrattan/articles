package com.me.projects.articles.dao;

import com.me.projects.articles.model.Article;
import com.me.projects.articles.model.Vignette;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlesDAOImpl implements ArticlesDAO {
    private final MongoOperations operations;
    private final String collectionName;

    public ArticlesDAOImpl(
            final MongoOperations operations,
            @Value("${articles.collection.name}") String collectionName
    ) {
        this.operations = operations;
        this.collectionName = collectionName;
    }

    @Override
    public Optional<Article> getArticle(Long id) throws DataAccessException {
        return Optional.ofNullable(this.operations.findById(id, Article.class));
    }

    @Override
    public List<Vignette> getSlice(int index, int size) throws DataAccessException {
        final Query query = new Query();
        query.addCriteria(Criteria.where("id").gte(index)).limit(size);
        return operations.find(query, Vignette.class);
    }

    @Override
    public long getNumberOfArticles() throws DataAccessException {
        return operations.getCollection(collectionName).countDocuments();
    }
}
