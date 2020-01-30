package com.me.projects.articles.controller;

import com.me.projects.articles.dao.ArticlesDAO;
import com.me.projects.articles.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.emptyList;

@RestController
public class ArticleController {
    private ArticlesDAO dao;

    @Autowired
    public ArticleController(ArticlesDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/articles/paginated")
    public ResponseEntity<List<Article>> getPaginated(
            @RequestParam(required = false, defaultValue = "0") Integer index,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        try {
            return ResponseEntity.ok(dao.getSlice(index, size));
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList());
        }
    }

}
