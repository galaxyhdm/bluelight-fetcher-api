package dev.markusk.bluelight.api.objects;


import java.util.Date;
import java.util.Set;

/**
 * This class is a representation for a fetched article from a website or rss-feed.
 */
public interface Article {

  /**
   * This method can return a id provided by the fetch target
   * or a self generated id.
   *
   * @return a unique id as string
   */
  String getId();

  void setId(String id);

  /**
   * @return the fetched title of the article
   */
  String getTitle();

  void setTitle(String title);

  /**
   * @return the download url for the whole article
   */
  String getUrl();

  void setUrl(String url);

  /**
   * Nullable
   *
   * @return the release time of the article
   */
  Date getReleaseTime();

  /**
   * Nullable
   *
   * @param releaseTime the release time of the article
   */
  void setReleaseTime(Date releaseTime);

  /**
   * @return the fetch time of the article
   */
  Date getFetchTime();

  void setFetchTime(Date fetchTime);

  /**
   * The identification can be a SHA-256 hash of other parameters or a random string
   * It is important that this variable is unique.
   *
   * @return a unique identification for the downloaded file (without file-extension)
   */
  String getFileIdentification();

  void setFileIdentification(String fileIdentification);

  /**
   * Nullable
   * Not all articles provides separated location and topic tags.
   * This list must not exist at the first fetch.
   * It can be filled later when the content is downloaded
   *
   * @return a list with @{@link Location} objects
   */
  Set<Location> getLocationTags();

  void setLocationTags(Set<Location> locationTags);

  /**
   * Nullable
   * This list must not exist at the first fetch.
   * It can be filled later when the content is downloaded
   *
   * @return a list with @{@link Topic} objects
   */
  Set<Topic> getTopicTags();

  void setTopicTags(Set<Topic> topicTags);

  /**
   * Nullable
   * This value must not exist at the first fetch.
   * It can be filled later when the content is downloaded.
   *
   * @return the written content of the article
   */
  String getContent();

  void setContent(String content);
}
