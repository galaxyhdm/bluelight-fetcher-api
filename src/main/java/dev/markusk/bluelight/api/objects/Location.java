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
  double getLatitude();

  void setLatitude(double latitude);

  /**
   * Nullable
   *
   * @return the longitude of the location
   */
  double getLongitude();

  void setLongitude(double longitude);

}
