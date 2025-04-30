package com.yeahka.it.community.topic;

public class ArticleNotFoundException extends Exception {
    private Long id;
    ArticleNotFoundException(Long id) {
        this.id = id;
    }

}
