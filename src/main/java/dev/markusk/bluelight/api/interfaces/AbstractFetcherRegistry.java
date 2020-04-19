package dev.markusk.bluelight.api.interfaces;

import java.util.HashMap;

public interface AbstractFetcherRegistry {

  /**
   * Adds a {@link AbstractInfoFetcher} to the registry
   *
   * @param infoFetcher a new {@link AbstractInfoFetcher} instance
   */
  void addInfoFetcher(final AbstractInfoFetcher infoFetcher);

  /**
   * Removes a {@link AbstractInfoFetcher} from the registry
   *
   * @param targetUid the linked targetUid for the {@link AbstractInfoFetcher}
   */
  void removeInfoFetcher(final String targetUid);

  /**
   * @return a {@link HashMap} with all available {@link AbstractInfoFetcher}
   * <p>
   * key: {@link String} value: {@link AbstractInfoFetcher}
   */
  HashMap<String, AbstractInfoFetcher> getFetcherMap();

  /**
   * @param targetId the targetUid with which the {@link AbstractInfoFetcher} was registered
   * @return the provided {@link AbstractInfoFetcher}
   */
  AbstractInfoFetcher getInfoFetcher(String targetId);
}
