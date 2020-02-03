package com.me.projects.articles.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WebControllerTest {
    private WebController controllerUnderTest;

    @Before
    public void init() {
        this.controllerUnderTest = new WebController();
    }

    @Test
    public void should_return_forward_to_index_string_when_path_for_article() {
        assertEquals("forward:/index.html", this.controllerUnderTest.forwardArticlePathToIndexPage());
    }

}
