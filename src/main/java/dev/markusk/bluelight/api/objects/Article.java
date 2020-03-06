package dev.markusk.bluelight.api.objects;

import jdk.internal.jline.internal.Nullable;

import java.util.Date;
import java.util.List;

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
   * @return the release time of the article
   */
  @Nullable
  Date getReleaseTime();

  void setReleaseTime(@Nullable Date releaseTime);

  /**
   * @return the fetch time of the article
   */
  String getFetchTime();

  void setFetchTime(Date fetchTime);

  /**
   * The identification can be a SHA-256 hash of other parameters or a random string
   * It is important that this variable is unique.
   *
   * @return a unique identification for the downloaded file
   */
  String getFileIdentification();

  void setFileIdentification(String fileIdentification);

  /**
   * Not all articles provides separated location and topic tags.
   * This list must not exist at the first fetch.
   * It can be filled later when the content is downloaded
   *
   * @return a list with @{@link Location} objects
   */
  @Nullable
  List<Location> getLocationTags();

  void setLocationTags(@Nullable List<Location> locationTags);

  /**
   * This list must not exist at the first fetch.
   * It can be filled later when the content is downloaded
   *
   * @return a list with @{@link Topic} objects
   */
  @Nullable
  List<Topic> getTopicTags();

  void setTopicTags(@Nullable List<Topic> topicTags);

  /**
   * This value must not exist at the first fetch.
   * It can be filled later when the content is downloaded.
   *
   * @return the written content of the article
   */
  @Nullable
  String getContent();

  void setContent(@Nullable String content);
}
