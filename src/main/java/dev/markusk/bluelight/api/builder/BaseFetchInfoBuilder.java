package dev.markusk.bluelight.api.builder;

import dev.markusk.bluelight.api.impl.BaseFetchInfo;

import java.util.Date;

public class BaseFetchInfoBuilder {
  private String targetUid;
  private String url;
  private String title;
  private Date releaseTime;
  private Date fetchTime;

  public BaseFetchInfoBuilder targetUid(final String targetUid) {
    this.targetUid = targetUid;
    return this;
  }

  public BaseFetchInfoBuilder url(final String url) {
    this.url = url;
    return this;
  }

  public BaseFetchInfoBuilder title(final String title) {
    this.title = title;
    return this;
  }

  public BaseFetchInfoBuilder releaseTime(final Date releaseTime) {
    this.releaseTime = releaseTime;
    return this;
  }

  public BaseFetchInfoBuilder fetchTime(final Date fetchTime) {
    this.fetchTime = fetchTime;
    return this;
  }

  public BaseFetchInfo createBaseFetchInfo() {
    return new BaseFetchInfo(targetUid, url, title, releaseTime, fetchTime);
  }
}