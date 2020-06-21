package dev.markusk.bluelight.api.job;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.interfaces.BaseFetchInfo;

public interface DownloadJob extends AbstractJob {

  AbstractFetcher getFetcher();

  BaseFetchInfo getBaseInfo();

}
