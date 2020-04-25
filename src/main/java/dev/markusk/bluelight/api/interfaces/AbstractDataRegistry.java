package dev.markusk.bluelight.api.interfaces;

import dev.markusk.bluelight.api.data.AbstractDataManager;
import dev.markusk.bluelight.api.data.DataSettings;

public interface AbstractDataRegistry {

  /**
   * Register a {@link AbstractDataManager}
   *
   * @param adapterName the name to select the {@link AbstractDataManager}
   * @param dataClass   a {@link AbstractDataManager} class
   */
  void register(final String adapterName, final Class<? extends AbstractDataManager> dataClass);

  /**
   * Initialize and get a {@link AbstractDataManager}. If a targetId is already registered the existing object is returned
   *
   * @param targetUid the targetUid of the target
   * @param settings  the {@link DataSettings} to configure the {@link AbstractDataManager}
   * @return a {@link AbstractDataManager} object
   */
  AbstractDataManager getDataManager(final String targetUid, final DataSettings settings);

  /**
   * Get a {@link AbstractDataManager}
   *
   * @param targetUid the targetUid of the target
   * @return a {@link AbstractDataManager} object, can be null when the {@link AbstractDataManager} is not created
   */
  AbstractDataManager getDataManager(final String targetUid);

}
