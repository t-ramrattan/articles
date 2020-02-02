package com.me.projects.articles.controller;

import com.me.projects.articles.dao.ArticlesDAO;
import com.me.projects.articles.model.Article;
import com.me.projects.articles.model.Paginate;
import com.me.projects.articles.model.Vignette;
import com.me.projects.articles.service.PaginationServiceException;
import com.me.projects.articles.service.VignettePaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api")
public class ArticleController {

    private final VignettePaginationService paginationService;
    private final ArticlesDAO dao;

    @Autowired
    public ArticleController(
            final VignettePaginationService paginationService,
            final ArticlesDAO articlesDAO
    ) {
        this.paginationService = paginationService;
        this.dao = articlesDAO;
    }

    @GetMapping("/paginated")
    public ResponseEntity<Paginate<Vignette>> getPaginated(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        try {
            Paginate<Vignette> paginate = paginationService.getPagination(page, size);
            return ResponseEntity.ok(paginate);
        } catch (PaginationServiceException ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/article")
    public ResponseEntity<Article> getArticle(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(this.dao.getArticle(id).orElse(null));
        } catch (DataAccessException ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
