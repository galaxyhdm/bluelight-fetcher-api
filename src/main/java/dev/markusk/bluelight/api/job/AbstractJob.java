package dev.markusk.bluelight.api.job;

public interface AbstractJob extends Runnable {

  String getJobId();

  JobPriority getPriority();

}
