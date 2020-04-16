package dev.markusk.bluelight.api.handler;

public interface JobHandler {

  void onStart();

  void updateGauge(String targetUid, double value);

  void onEnd();

}
