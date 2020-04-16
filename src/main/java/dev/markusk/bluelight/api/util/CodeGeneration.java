package dev.markusk.bluelight.api.util;

public class CodeGeneration {

  public static String getCodeString() {
    return getCodeString(4);
  }

  public static String getCodeString(int appendCount) {
    return getCodeString(appendCount, 4);
  }

  public static String getCodeString(int appendCount, int appendLength) {
    return getCodeString(appendCount, appendLength, '-');
  }

  public static String getCodeString(int appendCount, int appendLength, char appendChar) {
    return getCodeString(appendCount, appendLength, appendChar, Mode.ALPHANUMERIC);
  }

  public static String getCodeString(Mode mode) {
    return getCodeString(4, mode);
  }

  public static String getCodeString(int appendCount, Mode mode) {
    return getCodeString(appendCount, 4, mode);
  }

  public static String getCodeString(int appendCount, int appendLength, Mode mode) {
    return getCodeString(appendCount, appendLength, '-', mode);
  }

  public static String getCodeString(
      int appendCount, int appendLength, char appendChar, Mode mode) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < appendCount; i++)
      stringBuilder.append(generateRandomString(appendLength, mode)).append(appendChar);
    return stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
  }

  public static void main(String[] args) {
    System.out.println(getCodeString(3, 4, ':', Mode.ALPHANUMRICUPPER));
  }

  private static String generateRandomString(int length, Mode mode) {
    if (mode == null) return "";
    StringBuilder buffer = new StringBuilder();
    String characters = mode.getCharacters();
    int charactersLength = characters.length();
    for (int i = 0; i < length; i++) {
      double index = Math.random() * charactersLength;
      buffer.append(characters.charAt((int) index));
    }
    return buffer.toString();
  }

  public enum Mode {
    ALPHA("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    ALPHANUMERIC("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"),
    ALPHANUMRICUPPER("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"),
    NUMERIC("1234567890");
    private String characters;

    Mode(final String characters) {
      this.characters = characters;
    }

    public String getCharacters() {
      return characters;
    }
  }
}
