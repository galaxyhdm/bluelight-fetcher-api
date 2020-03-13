package dev.markusk.bluelight.api.objects;

import java.util.Date;

/**
 * This class is a representation of the needed fetch infos.
 * Like the url and the title.
 */
public class BaseFetchInfo {

  /**
   * This variable must be set
   * This string is used for identify the target.
   * Used for the content extractor and all other processes depends on the target.
   */
  private String targetUid;
  /**
   * This variable must be set
   * This string links to the complete article.
   **/
  private String url;
  /**
   * This string represents the title of the article.
   * Can be null
   */
  private String title;

  /**
   * This date represents the release time of the article
   * Can be null.
   */
  private Date releaseTime;

  public BaseFetchInfo(final String targetUid, final String url, final String title, final Date releaseTime) {
    this.targetUid = targetUid;
    this.url = url;
    this.title = title;
    this.releaseTime = releaseTime;
  }


  public String getTargetUid() {
    return targetUid;
  }

  public void setTargetUid(final String targetUid) {
    this.targetUid = targetUid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public Date getReleaseTime() {
    return releaseTime;
  }

  public void setReleaseTime(final Date releaseTime) {
    this.releaseTime = releaseTime;
  }
}
