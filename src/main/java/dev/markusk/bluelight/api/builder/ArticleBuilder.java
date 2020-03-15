package dev.markusk.bluelight.api.builder;

import dev.markusk.bluelight.api.objects.Article;
import dev.markusk.bluelight.api.objects.Location;
import dev.markusk.bluelight.api.objects.Topic;

import java.util.Date;
import java.util.Set;

public class ArticleBuilder {
  private String id;
  private String title;
  private String url;
  private Date releaseTime;
  private Date fetchTime;
  private String fileIdentification;
  private Set<Location> locationTags;
  private Set<Topic> topicTags;
  private String content;

  public ArticleBuilder id(final String id) {
    this.id = id;
    return this;
  }

  public ArticleBuilder title(final String title) {
    this.title = title;
    return this;
  }

  public ArticleBuilder url(final String url) {
    this.url = url;
    return this;
  }

  public ArticleBuilder releaseTime(final Date releaseTime) {
    this.releaseTime = releaseTime;
    return this;
  }

  public ArticleBuilder fetchTime(final Date fetchTime) {
    this.fetchTime = fetchTime;
    return this;
  }

  public ArticleBuilder fileIdentification(final String fileIdentification) {
    this.fileIdentification = fileIdentification;
    return this;
  }

  public ArticleBuilder locationTags(final Set<Location> locationTags) {
    this.locationTags = locationTags;
    return this;
  }

  public ArticleBuilder topicTags(final Set<Topic> topicTags) {
    this.topicTags = topicTags;
    return this;
  }

  public ArticleBuilder content(final String content) {
    this.content = content;
    return this;
  }

  public Article createArticle() {
    return new Article(id, title, url, releaseTime, fetchTime, fileIdentification, locationTags, topicTags, content);
  }
}