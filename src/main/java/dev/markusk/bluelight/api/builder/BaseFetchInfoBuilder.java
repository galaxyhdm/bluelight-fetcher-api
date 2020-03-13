package dev.markusk.bluelight.api.builder;

import dev.markusk.bluelight.api.objects.BaseFetchInfo;

import java.util.Date;

public class BaseFetchInfoBuilder {
  private String targetUid;
  private String url;
  private String title;
  private Date releaseTime;

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

  public BaseFetchInfo createBaseFetchInfo() {
    return new BaseFetchInfo(targetUid, url, title, releaseTime);
  }
}