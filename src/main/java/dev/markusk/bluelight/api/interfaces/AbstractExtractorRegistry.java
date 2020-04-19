package dev.markusk.bluelight.api.interfaces;

import java.util.HashMap;

public interface AbstractExtractorRegistry {

  /**
   * @param targetUid the targetUid for the extractor, must match with the targetUid from the target
   * @param extractor a {@link Extractor} instance
   */
  void addExtractor(final String targetUid, final Extractor extractor);

  /**
   * @param targetUid the targetUid linked with the {@link Extractor}
   */
  void removeExtractor(final String targetUid);

  /**
   * @return a {@link HashMap} with all available {@link Extractor}s
   * <p>
   * key: {@link String} value: {@link Extractor}
   */
  HashMap<String, Extractor> getExtractorMap();

  /**
   * @param targetUid the targetUid with which the extractor was registered
   * @return the provided {@link Extractor} or when the result is null returning a default-extractor
   */
  Extractor getExtractor(final String targetUid);


  /**
   * @return a default-extractor which can be used when getExtractor returning null
   */
  Extractor getDefaultExtractor();
}
