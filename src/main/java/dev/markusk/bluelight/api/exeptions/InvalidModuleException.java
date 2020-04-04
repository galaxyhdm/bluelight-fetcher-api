package dev.markusk.bluelight.api.exeptions;

public class InvalidModuleException extends Exception {

  public InvalidModuleException(final Throwable cause) {
    super(cause);
  }

  public InvalidModuleException() {
  }

  public InvalidModuleException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public InvalidModuleException(final String message) {
    super(message);
  }

}
