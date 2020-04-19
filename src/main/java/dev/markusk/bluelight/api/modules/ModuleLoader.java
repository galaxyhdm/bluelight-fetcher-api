package dev.markusk.bluelight.api.modules;

import com.google.gson.Gson;
import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.exeptions.InvalidDescriptionException;
import dev.markusk.bluelight.api.exeptions.InvalidModuleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Represents a module loader, which handles direct access to specific types
 * of modules
 */
public class ModuleLoader {

  private static final Logger LOGGER = LogManager.getLogger();
  private static final Gson GSON = new Gson();

  private final AbstractFetcher abstractFetcher;
  private final Map<String, ModuleClassLoader> loaders = new LinkedHashMap<>();

  public ModuleLoader(final AbstractFetcher abstractFetcher) {
    this.abstractFetcher = abstractFetcher;
  }

  /**
   * Loads the {@link Module} contained in the specified file
   *
   * @param file {@link File} to attempt to load
   * @return {@link Module} that was contained in the specified file, or null if this process was unsuccessful
   * @throws InvalidModuleException Thrown when the specified file is not a plugin
   */
  public Module loadModule(final File file) throws InvalidModuleException {
    if (file == null) throw new NullPointerException("File cannot be null");

    if (!file.exists())
      throw new InvalidModuleException(new FileNotFoundException(String.format("%s does not exist", file.getPath())));

    final ModuleDescription moduleDescription;
    try {
      moduleDescription = this.getModuleDescription(file);
    } catch (InvalidDescriptionException e) {
      throw new InvalidModuleException(e);
    }

    final File parentFile = file.getParentFile();
    final File dataFolder = new File(parentFile, moduleDescription.getName());

    if (dataFolder.exists() && !dataFolder.isDirectory()) {
      throw new InvalidModuleException(String
          .format("Projected datafolder: `%s' for %s (%s) exists and is not a directory", dataFolder,
              moduleDescription.getName(), file));
    }

    final ModuleClassLoader classLoader;
    try {
      classLoader = new ModuleClassLoader(this, this.getClass().getClassLoader(), moduleDescription, dataFolder, file);
      classLoader.load();
    } catch (IOException e) {
      throw new InvalidModuleException(e);
    }
    this.loaders.put(moduleDescription.getName(), classLoader);
    return classLoader.getModule();
  }

  /**
   * Loads a {@link ModuleDescription} from the specified file
   *
   * @param file {@link File} to attempt to load from
   * @return A new {@link ModuleDescription} loaded from module.json in the specified file
   * @throws InvalidDescriptionException If the module description file could not be created
   */
  public ModuleDescription getModuleDescription(final File file) throws InvalidDescriptionException {
    if (file == null) throw new NullPointerException("File cannot be null");
    try {
      try (JarFile jarFile = new JarFile(file)) {
        final JarEntry jarEntry = jarFile.getJarEntry("module.json");
        if (jarEntry == null)
          throw new InvalidDescriptionException(new FileNotFoundException("Jar does not contain module.json"));
        try (InputStream inputStream = jarFile.getInputStream(jarEntry)) {
          try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            return GSON.fromJson(inputStreamReader, ModuleDescription.class);
          }
        }
      }
    } catch (IOException e) {
      throw new InvalidDescriptionException(e);
    }
  }

  /**
   * Enables the specified {@link Module}
   * <p>
   * Attempting to enable a module that is already enabled will have no effect
   *
   * @param module {@link Module} to enable
   */
  public void enableModule(final Module module) {
    if (!module.isEnabled()) {
      LOGGER.info("Enabling " + module.getDescription().getName());

      final String name = module.getDescription().getName();
      if (!loaders.containsKey(name)) {
        this.loaders.put(name, (ModuleClassLoader) module.getClassLoader());
      }
      module.setEnabled(true);
    }
  }

  /**
   * Disables the specified {@link Module}
   * <p>
   * Attempting to disable a module that is not enabled will have no effect
   *
   * @param module {@link Module} to enable
   */
  public void disabledModule(final Module module) {
    if (module.isEnabled()) {
      LOGGER.info("Disabling " + module.getDescription().getName());
      this.loaders.remove(module.getDescription().getName());
      module.setEnabled(false);
    }
  }

  public AbstractFetcher getAbstractFetcher() {
    return this.abstractFetcher;
  }
}
