package dev.markusk.bluelight.api.interfaces;

import dev.markusk.bluelight.api.job.AbstractJob;

public interface AbstractScheduler {

  /**
   * This method can be used to setup the schedule and executors
   */
  void initialize();

  /**
   * @param job a new {@link AbstractJob} instance to queue in the scheduler
   */
  void scheduleJob(AbstractJob job);

  /**
   * @return the current queued count
   */
  int getQueuedCount();

  /**
   * This method can be used to close the scheduler at the end of the program
   */
  void closeScheduler();

}
