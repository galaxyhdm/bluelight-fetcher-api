package dev.markusk.bluelight.api.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class Utils {

  private static final Logger LOGGER = LogManager.getLogger();

  public static <T> T getClass(final String path, final Class<T> classOfT) {
    try {
      final Class<?> baseClass = Class.forName(path);
      final Class<? extends T> subclass = baseClass.asSubclass(classOfT);
      return subclass.getDeclaredConstructor().newInstance();
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      LOGGER.error("Error", e);
    }
    return null;
  }
}
