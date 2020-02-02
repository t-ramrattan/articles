package com.me.projects.articles.service;

public class PaginationServiceException extends Exception {

  public PaginationServiceException(final String msg, final Throwable t) {
    super(msg, t);
  }

}
