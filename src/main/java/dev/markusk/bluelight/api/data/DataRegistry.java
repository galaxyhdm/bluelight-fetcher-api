package dev.markusk.bluelight.api.data;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.interfaces.DataSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DataRegistry {

  private static final Logger LOGGER = LogManager.getLogger();

  private final AbstractFetcher fetcher;
  private final Map<String, Class<? extends AbstractDataManager>> dataManagers = new HashMap<>();
  private final Map<String, AbstractDataManager> dataManagerMap = new HashMap<>();

  public DataRegistry(final AbstractFetcher fetcher) {
    this.fetcher = fetcher;
  }

  public void register(final String adapterName, final Class<? extends AbstractDataManager> dataClass) {
    if (adapterName == null)
      throw new NullPointerException("adapterName is null");
    if (dataClass == null)
      throw new NullPointerException("dataProvider is null");
    if (this.dataManagers.containsKey(adapterName))
      throw new IllegalStateException(String.format("Duplicated key '%s'", adapterName));
    if (this.dataManagers.containsValue(dataClass))
      throw new IllegalStateException(String.format("Duplicated value '%s'", dataClass.getName()));

    this.dataManagers.put(adapterName, dataClass);
  }

  public AbstractDataManager getDataManager(final String targetUid, final DataSettings settings) {
    if (this.dataManagerMap.containsKey(targetUid))
      return this.dataManagerMap.get(targetUid);
    final Class<? extends AbstractDataManager> adapterClass = this.dataManagers.get(settings.getAdapterName());
    if (adapterClass == null)
      throw new NullPointerException(String.format("Entry not found for key '%s'", settings.getAdapterName()));
    final AbstractDataManager abstractDataManager;
    try {
      abstractDataManager = adapterClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      LOGGER.error("Could not initialize data-manager");
      return null;
    }
    abstractDataManager.initialize(this.fetcher, settings);
    this.dataManagerMap.put(targetUid, abstractDataManager);
    return abstractDataManager;
  }

  public AbstractDataManager getDataManager(final String targetUid) {
    if (!this.dataManagerMap.containsKey(targetUid)) return null;
    return this.dataManagerMap.get(targetUid);
  }

}
