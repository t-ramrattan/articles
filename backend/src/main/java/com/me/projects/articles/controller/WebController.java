package com.me.projects.articles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/article/{id}")
    public String forwardArticlePathToIndexPage() {
        return "forward:/index.html";
    }

}
