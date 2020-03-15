package dev.markusk.bluelight.api.impl;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import dev.markusk.bluelight.api.AbstractInfoFetcher;
import dev.markusk.bluelight.api.builder.BaseFetchInfoBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class RssFetcher implements AbstractInfoFetcher {

  private static final Logger LOGGER = LogManager.getLogger();

  private RssReader rssReader;

  private String targetUid;
  private String url;
  private String simpleDatePattern = "E',' dd MMM yyyy HH:mm:ss Z";
  private int updateTime;

  private SimpleDateFormat simpleDateFormat;

  @Override
  public void initialize(final String targetUid, final String fetchUrl, final int updateTime) {
    this.rssReader = new RssReader();
    this.targetUid = targetUid;
    this.url = fetchUrl;
    this.updateTime = updateTime;
    this.simpleDateFormat = new SimpleDateFormat(this.simpleDatePattern, Locale.US);
    this.simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  @Override
  public List<BaseFetchInfo> getFetchInfos(final Date fetchTime) throws Exception {
    final Stream<Item> read = this.rssReader.read(this.getFetchUrl());
    final List<BaseFetchInfo> fetchInfos = new ArrayList<>();
    read.forEach(item -> {
      final BaseFetchInfo baseFetchInfo = new BaseFetchInfoBuilder()
          .targetUid(this.getTargetUid())
          .url(item.getLink().orElse(null))
          .title(item.getTitle().orElse(null))
          .releaseTime(this.extractDate(item.getPubDate().orElse(null)))
          .fetchTime(fetchTime)
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

  public String getSimpleDatePattern() {
    return simpleDatePattern;
  }

  public void setSimpleDatePattern(final String simpleDatePattern) {
    this.simpleDatePattern = simpleDatePattern;
  }

  @Override
  public int getUpdateTime() {
    return this.updateTime;
  }

  /**
   * @param inputDate is a formatted date string
   *                  This string can be null. This means that the pubDate could not be extracted from the RSS feed.
   *                  Then return null.
   *                  Example: Fri, 13 Mar 2020 19:40:45 +0100
   * @return a date object in utc time zone!
   */
  public Date extractDate(String inputDate) {
    if (inputDate == null) return null;
    final Date parse;
    try {
      parse = this.simpleDateFormat.parse(inputDate);
    } catch (ParseException e) {
      LOGGER.error("Error while parsing date", e);
      return null;
    }
    return parse;
  }

}
