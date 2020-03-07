package dev.markusk.bluelight.api.data;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.objects.Article;
import dev.markusk.bluelight.api.objects.Location;
import dev.markusk.bluelight.api.objects.Topic;

public interface AbstractDataManager {

  void initialize(AbstractFetcher abstractFetcher);

  void close();

  /**
   * This method can be used to insert a {@link Article} object to a datasource.
   *
   * @param article a new {@link Article} object
   */
  void addArticle(Article article);

  /**
   * This method doesn't load the related tags!
   *
   * @param id the unique identifier of a article given by {@link Article#getId()}
   * @return the related {@link Article} object
   */
  default Article getArticle(String id) {
    return getArticle(id, false);
  }

  /**
   * @param id       the unique identifier of a article given by {@link Article#getId()}
   * @param loadTags set true to load the related tags of this article
   * @return the related {@link Article} object
   */
  Article getArticle(String id, boolean loadTags);

  /**
   * This method can be used to update a {@link Article} in a datasource.
   * Not recommended to update the unique identifier.
   *
   * @param article a edited {@link Article} object
   */
  void updateArticle(Article article);

  /**
   * This method can be used to update only the article-content.
   *
   * @param article a {@link Article} object with edited {@link Article#getContent()}
   */
  void updateArticleContent(Article article);

  /**
   * @param id the unique identifier of a article given by {@link Article#getId()}
   * @return true when article is present
   */
  boolean hasArticle(String id);

  /**
   * This method can be used to update/set the location-links in the datasource.
   *
   * @param article a {@link Article} object with set/edited {@link Article#getLocationTags()}
   */
  void updateLocationLinks(Article article);

  /**
   * This method can be used to update/set the topic-links in the datasource.
   *
   * @param article a {@link Article} object with set/edited {@link Article#getTopicTags()}
   */
  void updateTopicLinks(Article article);

  /**
   * This method can be used to insert a {@link Location} object to a datasource
   *
   * @param location a new {@link Location} object
   */
  void addLocation(Location location);

  /**
   * @param id the unique identifier of a location given by {@link Location#getId()}
   * @return the related {@link Location} object
   */
  Location getLocation(String id);

  /**
   * @param id the unique identifier of a location given by {@link Location#getId()}
   * @return true when location is present
   */
  boolean hasLocation(String id);

  /**
   * This method can be used to insert a {@link Topic} object to a datasource
   *
   * @param topic a new {@link Topic} object
   */
  void addTopic(Topic topic);

  /**
   * @param id the unique identifier of a topic given by {@link Topic#getId()}
   * @return the related {@link Topic} object
   */
  Topic getTopic(String id);

  /**
   * @param id the unique identifier of a topic given by {@link Topic#getId()}
   * @return true when topic is present
   */
  boolean hasTopic(String id);

}
