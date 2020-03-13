package dev.markusk.bluelight.api.fetcher;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import dev.markusk.bluelight.api.AbstractInfoFetcher;
import dev.markusk.bluelight.api.builder.BaseFetchInfoBuilder;
import dev.markusk.bluelight.api.objects.BaseFetchInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public abstract class RssFetcher implements AbstractInfoFetcher {

  private RssReader rssReader;

  private String targetUid;
  private String url;

  @Override
  public void initialize(final String targetUid, final String fetchUrl) {
    this.rssReader = new RssReader();
    this.targetUid = targetUid;
    this.url = fetchUrl;
  }

  @Override
  public List<BaseFetchInfo> getFetchInfos() throws IOException {
    final Stream<Item> read = this.rssReader.read(this.getFetchUrl());
    final List<BaseFetchInfo> fetchInfos = new ArrayList<>();
    read.forEach(item -> {
      final BaseFetchInfo baseFetchInfo = new BaseFetchInfoBuilder()
          .targetUid(this.getTargetUid())
          .url(item.getLink().orElse(null))
          .title(item.getTitle().orElse(null))
          .releaseTime(this.extractDate(item.getPubDate().orElse(null)))
          .createBaseFetchInfo();
      fetchInfos.add(baseFetchInfo);
    });
    return fetchInfos;
  }

  @Override
  public String getFetchUrl() {
    return this.url;
  }

  @Override
  public String getTargetUid() {
    return this.targetUid;
  }

  /**
   * @param inputDate is a formatted date string
   *                  This string can be null. This means that the pubDate could not be extracted from the RSS feed.
   *                  Then return null.
   *                  Example: Fri, 13 Mar 2020 19:40:45 +0100
   * @return a date object in utc time zone!
   */
  public abstract Date extractDate(String inputDate);

}
