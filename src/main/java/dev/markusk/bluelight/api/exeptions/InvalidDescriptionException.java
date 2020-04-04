package dev.markusk.bluelight.api.exeptions;

public class InvalidDescriptionException extends Exception {

  public InvalidDescriptionException(final Throwable cause, final String message) {
    super(message, cause);
  }

  public InvalidDescriptionException(final Throwable cause) {
    super("IInvalid module.json", cause);
  }

  public InvalidDescriptionException(final String message) {
    super(message);
  }

  public InvalidDescriptionException() {
    super("Invalid module.json");
  }
}
