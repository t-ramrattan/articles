package com.me.projects.articles.service;

import com.me.projects.articles.dao.ArticlesDAO;
import com.me.projects.articles.model.Paginate;
import com.me.projects.articles.model.Vignette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VignettePaginationService implements PaginationService<Vignette> {

    private final ArticlesDAO articlesDAO;

    @Autowired
    public VignettePaginationService(final ArticlesDAO articlesDAO) {
        this.articlesDAO = articlesDAO;
    }

    @Override
    public Paginate<Vignette> getPagination(
            int page,
            int pageSize
    ) throws PaginationServiceException {
        try {
            final double numberOfArticles = this.articlesDAO.getNumberOfArticles();
            return new Paginate<>(
                    this.articlesDAO.getSlice(page * pageSize, pageSize),
                    page,
                    (int) Math.ceil(numberOfArticles / pageSize),
                    pageSize
            );
        } catch (DataAccessException ex) {
            throw new PaginationServiceException(ex.getMessage(), ex);
        }
    }

}
