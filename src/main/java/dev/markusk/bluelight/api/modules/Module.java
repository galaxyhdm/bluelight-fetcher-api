package dev.markusk.bluelight.api.modules;

import dev.markusk.bluelight.api.AbstractFetcher;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Represents a {@link Module}
 * A module must have a default constructor!
 */
public abstract class Module {

  private Logger logger;
  private ModuleLoader moduleLoader;
  private AbstractFetcher fetcher;
  private ModuleDescription moduleDescription;
  private File dataFolder;
  private File moduleFolder;
  private boolean enabled;
  private ClassLoader classLoader = null;

  public Module() {
//    final ClassLoader classLoader = getClass().getClassLoader();
//    LOGGER.fatal(classLoader.getClass());
//    if (!(classLoader instanceof ModuleClassLoader)) {
//      throw new IllegalStateException(String.format("Module requires %s", ModuleClassLoader.class.getName()));
//    }
//    ((ModuleClassLoader) classLoader).initialize(this);
  }

  public void initialize(final Logger logger, final ModuleLoader moduleLoader,
      final AbstractFetcher fetcher, final ModuleDescription moduleDescription, final File dataFolder,
      final File moduleFolder, final ModuleClassLoader moduleClassLoader) {
    this.logger = logger;
    this.moduleLoader = moduleLoader;
    this.fetcher = fetcher;
    this.moduleDescription = moduleDescription;
    this.dataFolder = dataFolder;
    this.moduleFolder = moduleFolder;
    this.classLoader = moduleClassLoader;

    if (!this.dataFolder.exists() && !this.dataFolder.mkdirs()) {
      throw new RuntimeException("Error creating " + this.dataFolder.getPath() + " directory");
    }
  }

  public abstract void enable();

  public abstract void disable();

  public ModuleLoader getModuleLoader() {
    return this.moduleLoader;
  }

  public AbstractFetcher getFetcher() {
    return this.fetcher;
  }

  public ModuleDescription getDescription() {
    return this.moduleDescription;
  }

  public File getDataFolder() {
    return this.dataFolder;
  }

  public File getModuleFolder() {
    return this.moduleFolder;
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(final boolean enabled) {
    if (this.enabled != enabled) {
      this.enabled = enabled;
      if (this.enabled)
        enable();
      else
        disable();
    }
  }

  public ClassLoader getClassLoader() {
    return classLoader;
  }

  public Logger getLogger() {
    return this.logger;
  }
}
