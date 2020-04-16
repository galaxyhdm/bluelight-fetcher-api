package dev.markusk.bluelight.api.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class TorValidator {

  private static final Logger LOGGER = LogManager.getLogger();
  private static final int MAX_ATTEMPTS = 2;

  private boolean startTor;
  private int attempts;

  public TorValidator() {
    this(true);
  }

  public TorValidator(final boolean startTor) {
    this.startTor = startTor;
  }

  public void checkTor() {
    attempts = attempts + 1;
    final String consoleCommand = runConsoleCommand(new String[]{"curl", "ipv4.icanhazip.com"}, false).trim();
    final String torCommand = runConsoleCommand(new String[]{"torsocks", "curl", "ipv4.icanhazip.com"}, false).trim();
    LOGGER.debug("Real-Ip=" + consoleCommand);
    LOGGER.debug("Tor-Ip=" + torCommand);

    if (!torCommand.isEmpty() && !consoleCommand.equals(torCommand)) {
      LOGGER.info("tor daemon active");
    } else {
      LOGGER.warn("tor daemon not active!");
      if (!startTor) {
        System.exit(50);
        return;
      }
      if (attempts > MAX_ATTEMPTS) {
        LOGGER.warn("tor daemon not able to start!");
        System.exit(50);
        return;
      }
      runConsoleCommand(new String[]{"service", "tor", "start"}, true);
      checkTor();
    }
  }

  private String runConsoleCommand(final String[] commandArray, final boolean printError) {
    final StringBuffer outBuffer = new StringBuffer();
    final StringBuffer errBuffer = new StringBuffer();

    final ProcessBuilder processBuilder = new ProcessBuilder(commandArray);

    final Process process;

    try {
      process = processBuilder.start();
    } catch (IOException e) {
      if (printError) LOGGER.error("Error while running command", e);
      return "";
    }

    final InputStream outStream = process.getInputStream();
    final InputStream errStream = process.getErrorStream();
    new StreamGobbler(outStream, outBuffer);
    new StreamGobbler(errStream, errBuffer);

    final int exitCode;
    try {
      exitCode = process.waitFor();
    } catch (InterruptedException e) {
      if (printError) LOGGER.error("Error while running command", e);
      return "";
    }

    final String out = outBuffer.toString();
    final String err = errBuffer.toString();
    if (exitCode > 0) {
      if (printError) LOGGER.error(err);
    } else {
      return out;
    }
    return "";
  }

}
