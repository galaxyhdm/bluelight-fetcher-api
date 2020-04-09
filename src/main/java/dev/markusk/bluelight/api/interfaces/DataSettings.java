package dev.markusk.bluelight.api.interfaces;

public class DataSettings {

  private String adapterName;
  // "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
  private String connectionUrl;

  public String getAdapterName() {
    return this.adapterName;
  }

  public void setAdapterName(final String adapterName) {
    this.adapterName = adapterName;
  }

  public String getConnectionUrl() {
    return this.connectionUrl;
  }

  public void setConnectionUrl(final String connectionUrl) {
    this.connectionUrl = connectionUrl;
  }
}
