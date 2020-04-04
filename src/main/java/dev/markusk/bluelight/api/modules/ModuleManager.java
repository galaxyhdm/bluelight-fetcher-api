package dev.markusk.bluelight.api.modules;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.exeptions.InvalidDescriptionException;
import dev.markusk.bluelight.api.exeptions.InvalidModuleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.*;

public class ModuleManager {

  private static final Logger LOGGER = LogManager.getLogger();

  private final AbstractFetcher fetcher;
  private final ModuleLoader moduleLoader;
  private final List<Module> modules = new ArrayList<>();
  private final Map<String, Module> lookupName = new HashMap<>();

  public ModuleManager(final AbstractFetcher instance, final ModuleLoader moduleLoader) {
    this.fetcher = instance;
    this.moduleLoader = moduleLoader;
  }

  public Module[] loadModules(final File directory) {
    if (directory == null) throw new NullPointerException("Directory cannot be null");
    if (!directory.isDirectory()) throw new IllegalStateException("Directory must be a directory");

    final List<Module> result = new ArrayList<>();
    final Map<String, File> modules = new HashMap<>();

    for (final File file : Objects.requireNonNull(directory.listFiles())) {
      final ModuleDescription moduleDescription;
      try {
        moduleDescription = this.moduleLoader.getModuleDescription(file);
      } catch (InvalidDescriptionException e) {
        LOGGER.warn(String.format("Could not load '%s' in folder '%s'", file.getPath(), directory.getPath()), e);
        continue;
      }
      final File replacedFile = modules.put(moduleDescription.getName(), file);
      if (replacedFile != null) {
        LOGGER.warn(String
            .format("Ambiguous plugin name `%s' for files `%s' and `%s' in `%s'", moduleDescription.getName(),
                file.getPath(), directory.getPath(), directory.getPath()));
      }
    }
    for (final String module : modules.keySet()) {
      final File file = modules.get(module);
      try {
        result.add(this.loadModule(file));
      } catch (InvalidModuleException e) {
        LOGGER.warn(String.format("Could not load '%s' in folder '%s'", file.getPath(), directory.getPath()), e);
      }
    }
    return result.toArray(new Module[0]);
  }

  public synchronized Module loadModule(final File file) throws InvalidModuleException {
    if (file == null) throw new NullPointerException("File cannot be null");
    final Module result = this.moduleLoader.loadModule(file);
    if (result != null) {
      this.modules.add(result);
      this.lookupName.put(result.getDescription().getName(), result);
    }
    return result;
  }

  public synchronized Module getModule(String name) {
    return this.lookupName.get(name.replace(' ', '_'));
  }

  public void enableModule(final Module module) {
    if (!module.isEnabled()) {
      module.getModuleLoader().enableModule(module);
    }
  }

  public void disableModule(final Module module) {
    if (module.isEnabled()) {
      module.getModuleLoader().disabledModule(module);
    }
  }

  public List<Module> getModules() {
    return modules;
  }
}
