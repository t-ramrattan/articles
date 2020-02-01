package com.me.projects.articles.controller;

import com.me.projects.articles.dao.ArticlesDAO;
import com.me.projects.articles.model.Vignette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api")
public class ArticleController {
    private ArticlesDAO dao;

    @Autowired
    public ArticleController(ArticlesDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/vignette")
    public ResponseEntity<List<Vignette>> getPaginated(
            @RequestParam(required = false, defaultValue = "0") Integer index,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        try {
            return ResponseEntity.ok(dao.getSlice(index, size));
        } catch (DataAccessException ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(emptyList());
        }
    }

    @GetMapping("/pages")
    public ResponseEntity<Integer> getNumberOfPages(
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        try {
            return ResponseEntity.ok((int) this.dao.getNumberOfArticles() / size);
        } catch (DataAccessException ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
