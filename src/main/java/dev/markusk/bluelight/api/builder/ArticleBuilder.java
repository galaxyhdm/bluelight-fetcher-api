package dev.markusk.bluelight.api.builder;

import dev.markusk.bluelight.api.objects.Article;
import dev.markusk.bluelight.api.objects.Location;
import dev.markusk.bluelight.api.objects.Topic;

import java.util.Date;
import java.util.Set;

public interface ArticleBuilder {

  ArticleBuilder id(String id);

  ArticleBuilder title(String title);

  ArticleBuilder url(String url);

  ArticleBuilder releaseTime(Date releaseTime);

  ArticleBuilder fetchTime(Date fetchTime);

  ArticleBuilder fileIdentification(String fileIdentification);

  ArticleBuilder locationTags(Set<Location> locationTags);

  ArticleBuilder topicTags(Set<Topic> topicTags);

  ArticleBuilder content(String content);

  Article createArticle();

}
