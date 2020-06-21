package dev.markusk.bluelight.api.config;

import java.util.List;
import java.util.Map;

public interface Configuration {

  String getModuleFolder();

  void setModuleFolder(String moduleFolder);

  Map<String, TargetConfigurationImpl> getTargets();

  void setTargets(Map<String, TargetConfigurationImpl> targets);

  List<String> getUserAgents();

  void setUserAgents(List<String> userAgents);

}
