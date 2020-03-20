package dev.markusk.bluelight.api.util;

import dev.markusk.bluelight.api.interfaces.Extractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class Utils {

  private static final Logger LOGGER = LogManager.getLogger();

  public static Extractor getExtractor(final String path) {
    try {
      final Class<?> aClass = Class.forName(path);
      final Object o = aClass.getDeclaredConstructor().newInstance();
      if (o instanceof Extractor) return (Extractor) o;
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      LOGGER.error("Error", e);
    }
    return null;
  }

}
