package dev.markusk.bluelight.api.util;

import java.io.IOException;
import java.io.InputStream;

public class StreamGobbler extends Thread {

  private InputStream stream;
  private StringBuffer buffer;

  public StreamGobbler(final InputStream stream, final StringBuffer buffer) {
    this.stream = stream;
    this.buffer = buffer;
    this.start();
  }

  @Override
  public void run() {
    while (true) {
      try {
        int nextChar;
        if ((nextChar = this.stream.read()) != -1) {
          this.buffer.append((char) nextChar);
          continue;
        }
      } catch (IOException ignored) {
      }
      return;
    }
  }
}
