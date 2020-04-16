package dev.markusk.bluelight.api.interfaces;

import java.util.HashMap;

public interface AbstractFetcherRegistry {

  void addInfoFetcher(final AbstractInfoFetcher infoFetcher);

  void removeInfoFetcher(final String targetUid);

  HashMap<String, AbstractInfoFetcher> getFetcherMap();

}
