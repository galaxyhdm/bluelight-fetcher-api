package dev.markusk.bluelight.api.objects;

public interface Location {

  /**
   * @return a unique id as string
   */
  String getId();

  void setId(String id);

  /**
   * @return the location name (example: Germany)
   */
  String getLocationName();

  void setLocationName(String locationName);

  /**
   * Nullable
   *
   * @return the latitude of the location
   */
  Double getLatitude();

  void setLatitude(Double latitude);

  /**
   * Nullable
   *
   * @return the longitude of the location
   */
  Double getLongitude();

  void setLongitude(Double longitude);

  /**
   * @return true when the system tried to set latitude and longitude
   */
  boolean isIndexed();

  void setIndexed(boolean indexed);

}
