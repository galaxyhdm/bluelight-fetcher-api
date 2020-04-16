package dev.markusk.bluelight.api.interfaces;

import java.util.HashMap;

public interface AbstractExtractorRegistry {

  void addExtractor(final String targetId, final Extractor extractor);

  void removeExtractor(final String targetUid);

  HashMap<String, Extractor> getExtractorMap();

  Extractor getExtractor(final String targetUid);

}
