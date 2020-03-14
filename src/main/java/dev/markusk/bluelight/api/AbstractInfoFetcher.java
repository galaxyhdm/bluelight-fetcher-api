package dev.markusk.bluelight.api;

import dev.markusk.bluelight.api.impl.BaseFetchInfo;

import java.util.List;

public interface AbstractInfoFetcher {


  /**
   * @param targetUid  is used for identify the target. Used for the content extractor and all other processes depends on the target.
   * @param fetchUrl   this parameter is the base search url like a rss feed or a website with the posted articles
   * @param updateTime this parameter is used to set the fetch frequency of the target. In minutes
   */
  void initialize(String targetUid, String fetchUrl, int updateTime);

  /**
   * @return a list with {@link BaseFetchInfo} extracted from the fetch source
   * throws {@link Exception} when something is not working
   */
  List<BaseFetchInfo> getFetchInfos() throws Exception;

  /**
   * @return the fetch url. For example a rss-feed or a website.
   */
  String getFetchUrl();

  /**
   * @return A unique id used for identify the target. Used for the content extractor and all other processes depends on the target.
   */
  String getTargetUid();

  /**
   * @return the update wait time in minutes.
   */
  int getUpdateTime();

}
