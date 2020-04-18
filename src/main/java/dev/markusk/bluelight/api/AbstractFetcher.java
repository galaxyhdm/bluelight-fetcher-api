package dev.markusk.bluelight.api;

import dev.markusk.bluelight.api.config.Configuration;
import dev.markusk.bluelight.api.config.DataStore;
import dev.markusk.bluelight.api.config.TargetConfiguration;
import dev.markusk.bluelight.api.data.DataRegistry;
import dev.markusk.bluelight.api.interfaces.AbstractExtractorRegistry;
import dev.markusk.bluelight.api.interfaces.AbstractFetcherExecutor;
import dev.markusk.bluelight.api.interfaces.AbstractFetcherRegistry;
import dev.markusk.bluelight.api.interfaces.AbstractScheduler;
import dev.markusk.bluelight.api.modules.ModuleManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public interface AbstractFetcher {

  void initialize();

  DataRegistry getDataRegistry();

  ModuleManager getModuleManager();

  Logger getLogger();

  AbstractFetcherRegistry getFetcherRegistry();

  AbstractFetcherExecutor getFetcherExecutor();

  AbstractExtractorRegistry getExtractorRegistry();

  AbstractScheduler getDownloadScheduler();

  AbstractScheduler getImportScheduler();

  Configuration getConfiguration();

  DataStore getDataStore();

  File getWorkDir();

  TargetConfiguration getTargetConfiguration(final String targetUid);

}
