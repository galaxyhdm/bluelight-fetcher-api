package dev.markusk.bluelight.api.interfaces;

import dev.markusk.bluelight.api.impl.FetcherJob;

public interface AbstractFetcherExecutor {

  void initializeJobs();

  void addJob(AbstractInfoFetcher infoFetcher);

  FetcherJob getFetcherJob(final String targetUid);

}
