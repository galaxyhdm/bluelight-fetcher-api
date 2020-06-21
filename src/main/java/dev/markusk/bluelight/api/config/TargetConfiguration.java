package dev.markusk.bluelight.api.config;

import dev.markusk.bluelight.api.data.DataSettings;
import dev.markusk.bluelight.api.enums.ImportState;

import java.util.List;

public interface TargetConfiguration {

  String getFetchUrl();

  void setFetchUrl(String fetchUrl);

  int getUpdateTime();

  void setUpdateTime(int updateTime);

  boolean isTor();

  void setTor(boolean tor);

  boolean isAutoIndex();

  void setAutoIndex(boolean autoIndex);

  byte getIndexType();

  void setIndexType(byte indexType);

  String getExtractorPath();

  void setExtractorPath(String extractorPath);

  String getFetcherPath();

  void setFetcherPath(String fetcherPath);

  String getWorkDir();

  void setWorkDir(String workDir);

  String getSuffix();

  void setSuffix(String suffix);

  DataSettings getDatabase();

  void setDatabase(DataSettings database);

  List<ImportState> getImportStates();

  void setImportStates(List<ImportState> importStates);

}
