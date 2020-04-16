package dev.markusk.bluelight.api;

import dev.markusk.bluelight.api.config.Configuration;
import dev.markusk.bluelight.api.config.TargetConfiguration;
import dev.markusk.bluelight.api.data.DataRegistry;
import dev.markusk.bluelight.api.interfaces.AbstractExtractorRegistry;
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

  AbstractExtractorRegistry getExtractorRegistry();

  AbstractScheduler getDownloadScheduler();

  AbstractScheduler getImportScheduler();

  Configuration getConfiguration();

  File getWorkDir();

  TargetConfiguration getTargetConfiguration(final String targetUid);

}
