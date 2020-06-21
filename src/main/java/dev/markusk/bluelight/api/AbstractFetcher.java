package dev.markusk.bluelight.api;

import dev.markusk.bluelight.api.config.Configuration;
import dev.markusk.bluelight.api.config.TargetConfiguration;
import dev.markusk.bluelight.api.factory.ObjectFactory;
import dev.markusk.bluelight.api.interfaces.*;
import dev.markusk.bluelight.api.modules.ModuleManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public interface AbstractFetcher {

  void initialize();

  AbstractDataRegistry getDataRegistry();

  ModuleManager getModuleManager();

  Logger getLogger();

  AbstractFetcherRegistry getFetcherRegistry();

  AbstractFetcherExecutor getFetcherExecutor();

  AbstractExtractorRegistry getExtractorRegistry();

  AbstractScheduler getDownloadScheduler();

  AbstractScheduler getImportScheduler();

  Configuration getConfiguration();

  AbstractUrlData getUrlData();

  File getWorkDir();

  TargetConfiguration getTargetConfiguration(final String targetUid);

  ObjectFactory getObjectFactory();

}
