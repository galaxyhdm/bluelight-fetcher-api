package dev.markusk.bluelight.api.objects;

import java.util.Date;
import java.util.List;

/**
 * This class is a representation for a fetched article from a website or rss-feed.
 */
public class Article {

  private String id;
  private String title;
  private String url;
  private Date releaseTime;
  private Date fetchTime;
  private String fileIdentification;
  private List<Location> locationTags;
  private List<Topic> topicTags;
  private String content;

  /**
   * This method can return a id provided by the fetch target
   * or a self generated id.
   *
   * @return a unique id as string
   */
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the fetched title of the article
   */
  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the download url for the whole article
   */
  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * Nullable
   *
   * @return the release time of the article
   */
  public Date getReleaseTime() {
    return this.releaseTime;
  }

  /**
   * Nullable
   *
   * @param releaseTime the release time of the article
   */
  public void setReleaseTime(Date releaseTime) {
    this.releaseTime = releaseTime;
  }

  /**
   * @return the fetch time of the article
   */
  public Date getFetchTime() {
    return this.fetchTime;
  }

  public void setFetchTime(Date fetchTime) {
    this.fetchTime = fetchTime;
  }

  /**
   * The identification can be a SHA-256 hash of other parameters or a random string
   * It is important that this variable is unique.
   *
   * @return a unique identification for the downloaded file
   */
  public String getFileIdentification() {
    return this.fileIdentification;
  }

  public void setFileIdentification(String fileIdentification) {
    this.fileIdentification = fileIdentification;
  }

  /**
   * Nullable
   * Not all articles provides separated location and topic tags.
   * This list must not exist at the first fetch.
   * It can be filled later when the content is downloaded
   *
   * @return a list with @{@link Location} objects
   */
  public List<Location> getLocationTags() {
    return this.locationTags;
  }

  public void setLocationTags(List<Location> locationTags) {
    this.locationTags = locationTags;
  }

  /**
   * Nullable
   * This list must not exist at the first fetch.
   * It can be filled later when the content is downloaded
   *
   * @return a list with @{@link Topic} objects
   */
  public List<Topic> getTopicTags() {
    return this.topicTags;
  }

  public void setTopicTags(List<Topic> topicTags) {
    this.topicTags = topicTags;
  }

  /**
   * Nullable
   * This value must not exist at the first fetch.
   * It can be filled later when the content is downloaded.
   *
   * @return the written content of the article
   */
  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
