package dev.markusk.bluelight.api.data;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.objects.Article;
import dev.markusk.bluelight.api.objects.Location;
import dev.markusk.bluelight.api.objects.Topic;

public interface AbstractDataManager {

  void initialize(AbstractFetcher abstractFetcher);

  void close();

  void addArticle(Article article);

  Article getArticle(String id);

  void updateArticle(Article article);

  void updateArticleContent(Article article);

  boolean hasArticle(String id);

  void addLocation(Location location);

  Location getLocation(String id);

  boolean hasLocation(String id);

  void addTopic(Topic topic);

  Topic getTopic(String id);

  boolean hasTopic(String id);

}
