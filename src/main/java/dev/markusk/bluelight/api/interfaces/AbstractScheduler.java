package dev.markusk.bluelight.api.interfaces;

import dev.markusk.bluelight.api.job.AbstractJob;

public interface AbstractScheduler {

  void initialize();

  void scheduleJob(AbstractJob job);

  int getQueuedCount();

  void closeScheduler();

}
