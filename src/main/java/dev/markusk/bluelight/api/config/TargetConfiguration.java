package dev.markusk.bluelight.api.config;

import dev.markusk.bluelight.api.data.DataSettings;
import dev.markusk.bluelight.api.enums.ImportState;

import java.util.List;

public class TargetConfiguration {

  private String fetchUrl;
  private Integer updateTime;
  private Boolean tor;
  private Boolean autoIndex;

  /**
   * 1 = locations
   * 2 = topics
   * 4 = content
   * <p>
   * mean:
   * - 1 = locations only
   * - 3 = locations + topics
   * - 5 = location + content
   * - 6 = topic + content
   * - 7 = location + topic + content
   */
  private Byte indexType;
  private String extractorPath;
  private String fetcherPath;
  private String workDir;
  private String suffix;
  private DataSettings database;

  private List<ImportState> importStates;

  public String getFetchUrl() {
    return fetchUrl;
  }

  public void setFetchUrl(final String fetchUrl) {
    this.fetchUrl = fetchUrl;
  }

  public int getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(final int updateTime) {
    this.updateTime = updateTime;
  }

  public boolean isTor() {
    return tor;
  }

  public void setTor(final boolean tor) {
    this.tor = tor;
  }

  public boolean isAutoIndex() {
    return autoIndex;
  }

  public void setAutoIndex(final boolean autoIndex) {
    this.autoIndex = autoIndex;
  }

  public byte getIndexType() {
    return indexType;
  }

  public void setIndexType(final byte indexType) {
    this.indexType = indexType;
  }

  public String getExtractorPath() {
    return extractorPath;
  }

  public void setExtractorPath(final String extractorPath) {
    this.extractorPath = extractorPath;
  }

  public String getFetcherPath() {
    return fetcherPath;
  }

  public void setFetcherPath(final String fetcherPath) {
    this.fetcherPath = fetcherPath;
  }

  public String getWorkDir() {
    return workDir;
  }

  public void setWorkDir(final String workDir) {
    this.workDir = workDir;
  }

  public String getSuffix() {
    return this.suffix;
  }

  public void setSuffix(final String suffix) {
    this.suffix = suffix;
  }

  public DataSettings getDatabase() {
    return this.database;
  }

  public void setDatabase(final DataSettings database) {
    this.database = database;
  }

  public List<ImportState> getImportStates() {
    return this.importStates;
  }

  public void setImportStates(final List<ImportState> importStates) {
    this.importStates = importStates;
  }

}
