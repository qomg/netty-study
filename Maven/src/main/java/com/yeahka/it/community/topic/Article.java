package com.yeahka.it.community.topic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "Article")
public // (1)
class Article {

    @Id
    @GeneratedValue
    private Long id; // (2)

    private ArticleStatus articleStatus; // (3)

    private String description; // (4)

    private Article() {
        this.id = null;
        this.articleStatus = articleStatus.BEING_CREATED;
        this.description = "";
    }

    public Article(String description) {
        this();
        this.description = description;
    }

    public Article(Long id, String description) {
        this();
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArticleStatus getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article order = (Article) o;
        return Objects.equals(id, order.id) &&
                articleStatus == order.articleStatus &&
                Objects.equals(description, order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, articleStatus, description);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", articleStatus=" + articleStatus +
                ", description='" + description + '\'' +
                '}';
    }
}
