package dev.markusk.bluelight.api.interfaces;

import java.util.Date;

/**
 * This class is a representation of the needed fetch infos.
 * <p>
 * Like the url and the title.
 */
public interface BaseFetchInfo {

  /**
   * This variable must be set
   * <p>
   * This string is used for identify the target.
   * <p>
   * Used for the content extractor and all other processes depends on the target.
   */
  String getTargetUid();

  void setTargetUid(String targetUid);

  /**
   * This variable must be set
   * <p>
   * This string links to the complete article.
   **/
  String getUrl();

  void setUrl(String url);

  /**
   * This string represents the title of the article.
   * <p>
   * Can be null
   */
  String getTitle();

  void setTitle(String title);

  /**
   * This date represents the release time of the article
   * <p>
   * Can be null.
   */
  Date getReleaseTime();

  void setReleaseTime(Date releaseTime);

  /**
   * This date represents the fetch time of the article
   */
  Date getFetchTime();

  void setFetchTime(Date fetchTime);
  
}
