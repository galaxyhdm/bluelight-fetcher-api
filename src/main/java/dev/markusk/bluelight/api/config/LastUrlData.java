package dev.markusk.bluelight.api.config;

import java.util.HashMap;

public class LastUrlData {

  private HashMap<String, String> lastUrls = new HashMap<>();

  public HashMap<String, String> getLastUrlMap() {
    return lastUrls;
  }

  public void setLastUrlMap(final HashMap<String, String> lastUrlMap) {
    this.lastUrls = lastUrlMap;
  }
}
