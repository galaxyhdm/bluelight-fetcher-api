package dev.markusk.bluelight.api.job;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.handler.JobHandler;
import dev.markusk.bluelight.api.interfaces.AbstractInfoFetcher;

import java.util.TimerTask;

public abstract class FetcherJob extends TimerTask {

  private final AbstractFetcher fetcher;
  private final AbstractInfoFetcher infoFetcher;
  private final boolean download;
  private JobHandler jobHandler;

  public FetcherJob(final AbstractFetcher fetcher, final AbstractInfoFetcher infoFetcher,
      final boolean download) {
    this.fetcher = fetcher;
    this.infoFetcher = infoFetcher;
    this.download = download;
  }

  public AbstractFetcher getFetcher() {
    return fetcher;
  }

  public boolean isDownload() {
    return download;
  }

  public JobHandler getJobHandler() {
    return jobHandler;
  }

  public void setJobHandler(final JobHandler jobHandler) {
    this.jobHandler = jobHandler;
  }

  public AbstractInfoFetcher getInfoFetcher() {
    return this.infoFetcher;
  }

}
