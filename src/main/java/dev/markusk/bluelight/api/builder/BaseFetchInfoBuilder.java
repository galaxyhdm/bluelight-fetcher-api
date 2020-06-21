package dev.markusk.bluelight.api.builder;

import dev.markusk.bluelight.api.interfaces.BaseFetchInfo;

import java.util.Date;

public interface BaseFetchInfoBuilder {

  BaseFetchInfoBuilder targetUid(String targetUid);

  BaseFetchInfoBuilder url(String url);

  BaseFetchInfoBuilder title(String title);

  BaseFetchInfoBuilder releaseTime(Date releaseTime);

  BaseFetchInfoBuilder fetchTime(Date fetchTime);

  BaseFetchInfo createBaseFetchInfo();

}
