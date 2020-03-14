package dev.markusk.bluelight.api.objects;

public class Location {

  private String id;
  private String locationName;
  private Double latitude;
  private Double longitude;
  private boolean indexed;

  /**
   * @return a unique id as string
   */
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the location name (example: Germany)
   */
  public String getLocationName() {
    return this.locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  /**
   * Nullable
   *
   * @return the latitude of the location
   */
  public Double getLatitude() {
    return this.latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  /**
   * Nullable
   *
   * @return the longitude of the location
   */
  public Double getLongitude() {
    return this.longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  /**
   * @return true when the system tried to set latitude and longitude
   */
  public boolean isIndexed() {
    return this.indexed;
  }

  public void setIndexed(boolean indexed) {
    this.indexed = indexed;
  }

}
