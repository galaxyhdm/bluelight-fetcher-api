package dev.markusk.bluelight.api.modules;

import dev.markusk.bluelight.api.exeptions.InvalidModuleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class ModuleClassLoader extends URLClassLoader {

  private static final Logger LOGGER = LogManager.getLogger();

  private final ModuleLoader moduleLoader;
  private final ModuleDescription moduleDescription;
  private final File dataFolder;
  private final File file;

  private Module module;

  public ModuleClassLoader(final ModuleLoader moduleLoader, final ClassLoader classLoader,
      final ModuleDescription moduleDescription, final File dataFolder, final File file)
      throws IOException {
    super(new URL[]{file.toURI().toURL()}, classLoader);

    this.moduleLoader = moduleLoader;
    this.moduleDescription = moduleDescription;
    this.dataFolder = dataFolder;
    this.file = file;
  }

  public void load() throws InvalidModuleException {
    try {
      final Class<?> mainClass;
      try {
        mainClass =
            Class.forName(this.moduleDescription.getMain(), true, this);
      } catch (ClassNotFoundException e) {
        throw new InvalidModuleException("Cannot find main class `" + moduleDescription.getMain() + "'", e);
      }
      final Class<? extends Module> moduleClass;
      try {
        moduleClass = mainClass.asSubclass(Module.class);
      } catch (ClassCastException ex) {
        throw new InvalidModuleException("main class `" + moduleDescription.getMain() + "' does not extend Module", ex);
      }
      this.module = moduleClass.getDeclaredConstructor().newInstance();
      this.initialize(this.module);
    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
      throw new InvalidModuleException("No public constructor", ex);
    } catch (InstantiationException ex) {
      throw new InvalidModuleException("Abnormal module type", ex);
    }
  }

  synchronized void initialize(final Module module) {
    if (module == null) throw new NullPointerException("Initializing module cannot be null");
//    if (!(module.getClass().getClassLoader() == this))
//      throw new IllegalStateException("Cannot initialize module outside of this class loader");
    this.module
        .initialize(this.moduleLoader, this.moduleLoader.getAbstractFetcher(), this.moduleDescription, this.dataFolder,
            this.file, this);
  }

  public Module getModule() {
    return this.module;
  }

  @Override
  public String getName() {
    return "ModuleClassLoader";
  }
}
