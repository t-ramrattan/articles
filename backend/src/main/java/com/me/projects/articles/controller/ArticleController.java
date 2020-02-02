package com.me.projects.articles.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.me.projects.articles.model.Paginate;
import com.me.projects.articles.model.Vignette;
import com.me.projects.articles.service.PaginationServiceException;
import com.me.projects.articles.service.VignettePaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticleController {

  private final VignettePaginationService paginationService;

  @Autowired
  public ArticleController(
      final VignettePaginationService paginationService
  ) {
    this.paginationService = paginationService;
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

}
