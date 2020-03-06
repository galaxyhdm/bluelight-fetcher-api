package dev.markusk.bluelight.api.job;

public interface AbstractJob extends Runnable {

  /**
   * Example: {@link java.util.UUID#randomUUID()}
   *
   * @return a unique id as string
   */
  String getJobId();

  /**
   * @return the priority for the job {@link JobPriority}
   */
  JobPriority getPriority();

}
