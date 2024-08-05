package ott.j4jg_be.domain.collection;

import java.time.LocalDate;

public class NewsArticle {
    private String title;
    private String content;
    private String articleUrl;
    private LocalDate date;

    public NewsArticle(String title, String articleUrl, String content, LocalDate date) {
        this.title = this.title;
        this.content = this.content;
        this.articleUrl = this.articleUrl;
        this.date = this.date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}