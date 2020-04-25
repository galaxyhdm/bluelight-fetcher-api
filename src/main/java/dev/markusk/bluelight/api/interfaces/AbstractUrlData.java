package dev.markusk.bluelight.api.interfaces;

public interface AbstractUrlData {

  void setLastUrl(final String targetUid, final String url);

  String getLastUrl(final String targetUid);

  void load();

  void save();

}
