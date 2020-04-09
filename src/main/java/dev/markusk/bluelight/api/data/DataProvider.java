package dev.markusk.bluelight.api.data;

import dev.markusk.bluelight.api.interfaces.DataSettings;

public class DataProvider {

  private Class<? extends AbstractDataManager> dataClass;
  private Class<? extends DataSettings> settingsClass;

  public DataProvider(final Class<? extends AbstractDataManager> dataClass,
      final Class<? extends DataSettings> settingsClass) {
    this.dataClass = dataClass;
    this.settingsClass = settingsClass;
  }

  public Class<? extends AbstractDataManager> getDataClass() {
    return dataClass;
  }

  public Class<? extends DataSettings> getSettingsClass() {
    return settingsClass;
  }
}
