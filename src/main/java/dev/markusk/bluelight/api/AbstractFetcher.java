package dev.markusk.bluelight.api;

import dev.markusk.bluelight.api.data.DataRegistry;
import dev.markusk.bluelight.api.modules.ModuleManager;
import org.apache.logging.log4j.Logger;

public interface AbstractFetcher {

  void initialize();

  DataRegistry getDataRegistry();

  ModuleManager getModuleManager();

  Logger getLogger();

}
