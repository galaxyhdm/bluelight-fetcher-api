package dev.markusk.bluelight.api.objects;

import jdk.internal.jline.internal.Nullable;

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
   * {@link Nullable}
   *
   * @return the latitude of the location
   */
  @Nullable
  double getLatitude();

  void setLatitude(@Nullable double latitude);

  /**
   * {@link Nullable}
   *
   * @return the longitude of the location
   */
  @Nullable
  double getLongitude();

  void setLongitude(@Nullable double longitude);

}
