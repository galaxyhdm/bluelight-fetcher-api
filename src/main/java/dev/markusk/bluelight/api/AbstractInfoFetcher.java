package dev.markusk.bluelight.api;

import dev.markusk.bluelight.api.objects.BaseFetchInfo;

import java.util.List;

public interface AbstractInfoFetcher {


  /**
   * @param targetUid is used for identify the target. Used for the content extractor and all other processes depends on the target.
   * @param fetchUrl  this parameter is the base search url like a rss feed or a website with the posted articles
   */
  void initialize(String targetUid, String fetchUrl);

  /**
   * @return a list with {@link BaseFetchInfo} extracted from the fetch source
   * throws {@link Exception} when something is not working
   */
  List<BaseFetchInfo> getFetchInfos() throws Exception;

  String getFetchUrl();

  String getTargetUid();

}
