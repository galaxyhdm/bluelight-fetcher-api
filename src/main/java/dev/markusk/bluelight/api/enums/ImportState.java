package dev.markusk.bluelight.api.enums;

import dev.markusk.bluelight.api.handler.ImportContentHandler;
import dev.markusk.bluelight.api.handler.ImportLocationHandler;
import dev.markusk.bluelight.api.handler.ImportTopicHandler;
import dev.markusk.bluelight.api.interfaces.ImportHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ImportState {

  LOCATIONS((byte) 1, new ImportLocationHandler()),
  TOPICS((byte) 2, new ImportTopicHandler()),
  CONTENT((byte) 4, new ImportContentHandler());

  private final byte value;
  private final ImportHandler handler;

  ImportState(final byte value, final ImportHandler handler) {
    this.value = value;
    this.handler = handler;
  }

  public static ImportState[] reverse() {
    final List<ImportState> importStates = Arrays.asList(ImportState.values());
    Collections.reverse(importStates);
    return importStates.toArray(ImportState[]::new);
  }

  public static List<ImportState> getImportStates(final byte value) {
    final List<ImportState> importStates = new ArrayList<>();
    byte tempValue = value;
    for (final ImportState importState : ImportState.reverse()) {
      final byte stateValue = importState.getValue();
      byte newValue = (byte) (tempValue - stateValue);
      if (newValue >= 0) {
        tempValue = newValue;
        importStates.add(importState);
      }
    }
    return importStates;
  }

  public byte getValue() {
    return this.value;
  }

  public ImportHandler getHandler() {
    return this.handler;
  }
}
