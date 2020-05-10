package dev.markusk.bluelight.api.util;

public class FileUtils {

  public static String buildFileSuffix(final String configSuffix) {
    final String trim = configSuffix.trim();
    return trim.isBlank() ? "" : String.format("%s%s", trim.startsWith(".") ? "" : ".", trim);
  }

}
