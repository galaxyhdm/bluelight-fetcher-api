package dev.markusk.bluelight.api.job;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.impl.BaseFetchInfo;

import java.util.UUID;

public abstract class AbstractDownloadJob implements AbstractJob {

  private final AbstractFetcher fetcher;
  private final String jobId;
  private final JobPriority priority;
  private final BaseFetchInfo baseInfo;

  public AbstractDownloadJob(final BaseFetchInfo baseInfo,
      final AbstractFetcher fetcher) {
    this(UUID.randomUUID().toString(), JobPriority.NORMAL, baseInfo, fetcher);
  }

  public AbstractDownloadJob(final String jobId, final JobPriority priority, final BaseFetchInfo baseInfo,
      final AbstractFetcher fetcher) {
    this.jobId = jobId;
    this.priority = priority;
    this.baseInfo = baseInfo;
    this.fetcher = fetcher;
  }

  public AbstractFetcher getFetcher() {
    return this.fetcher;
  }

  public BaseFetchInfo getBaseInfo() {
    return this.baseInfo;
  }

  @Override
  public String getJobId() {
    return this.jobId;
  }

  @Override
  public JobPriority getPriority() {
    return this.priority;
  }

}
