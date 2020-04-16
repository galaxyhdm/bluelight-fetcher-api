package dev.markusk.bluelight.api.impl;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.handler.JobHandler;
import dev.markusk.bluelight.api.interfaces.AbstractInfoFetcher;
import dev.markusk.bluelight.api.job.AbstractFetcherJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FetcherJob extends AbstractFetcherJob {

  private static final Logger LOGGER = LogManager.getLogger();

  public FetcherJob(final AbstractFetcher fetcher,
      final AbstractInfoFetcher infoFetcher, final boolean noDownload) {
    super(fetcher, infoFetcher, noDownload);
  }

  @Override
  public void run() {
    final JobHandler jobHandler = this.getJobHandler();
    if (jobHandler != null) jobHandler.onStart();
    try {
      final List<BaseFetchInfo> fetchInfos = this.getInfoFetcher().getFetchInfos();
      final String lastUrl =
          this.getFetcher().getDataStore().getLastUrl(this.getInfoFetcher().getTargetUid());
      final List<BaseFetchInfo> baseFetchInfos = this.getFilteredInfos(fetchInfos, lastUrl);

      if (this.isDownload())
        baseFetchInfos.forEach(baseFetchInfo -> {
          this.getFetcher().getDownloadScheduler()
              .scheduleJob(new DownloadJob(baseFetchInfo, this.getFetcher()));
        });

      LOGGER.info(this.getInfoFetcher().getTargetUid() + " | New article count: " + baseFetchInfos.size());
      if (jobHandler != null) jobHandler.updateGauge(this.getInfoFetcher().getTargetUid(), baseFetchInfos.size());
      if (baseFetchInfos.size() > 0) {
        final String lastFetchedUrl = baseFetchInfos.get(0).getUrl();
        LOGGER.info(this.getInfoFetcher().getTargetUid() + " | lastUrl=" + lastFetchedUrl);
        this.getFetcher().getDataStore().setLastUrl(this.getInfoFetcher().getTargetUid(), lastFetchedUrl);
        this.getFetcher().getDataStore().saveMap();
      }
    } catch (Exception e) {
      LOGGER.error(String.format("Error while fetching infos from: %s", this.getInfoFetcher().getTargetUid()), e);
    }
    if (jobHandler != null) jobHandler.onEnd();
  }

  private List<BaseFetchInfo> getFilteredInfos(final List<BaseFetchInfo> fetchInfos, final String lastUrl) {
    final BaseFetchInfo lastInfo = fetchInfos.stream().filter(
        baseFetchInfo -> baseFetchInfo.getUrl().equals(lastUrl))
        .findFirst().orElse(null);

    return lastInfo == null ? fetchInfos : fetchInfos.subList(0, fetchInfos.indexOf(lastInfo));
  }

}
