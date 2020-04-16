package dev.markusk.bluelight.api.enums;

import java.util.ArrayList;
import java.util.List;

public enum ImportState {

  LOCATIONS((byte) 1),
  TOPICS((byte) 2),
  CONTENT((byte) 4);

  private final byte value;

  ImportState(final byte value) {
    this.value = value;
  }

  public static ImportState[] reverse() {
    ImportState[] importStates = new ImportState[ImportState.values().length];
    int j = ImportState.values().length;
    for (int i = 0; i < ImportState.values().length; i++) {
      importStates[j - 1] = ImportState.values()[i];
      j = j - 1;
    }
    return importStates;
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
    return value;
  }

}
