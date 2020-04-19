package dev.markusk.bluelight.api.interfaces;

import dev.markusk.bluelight.api.impl.FetcherJob;

public interface AbstractFetcherExecutor {

  /**
   * This method can be used to setup all jobs at once
   */
  void initializeJobs();

  /**
   * Set up a {@link FetcherJob} from a {@link AbstractInfoFetcher}
   *
   * @param infoFetcher a already registered {@link AbstractInfoFetcher} instance
   */
  void addJob(AbstractInfoFetcher infoFetcher);

  /**
   * @param targetUid the targetUid with which the {@link FetcherJob} was registered
   * @return the provided {@link FetcherJob}
   */
  FetcherJob getFetcherJob(final String targetUid);

  /**
   * Executed when the system stops
   */
  void stop();
}
