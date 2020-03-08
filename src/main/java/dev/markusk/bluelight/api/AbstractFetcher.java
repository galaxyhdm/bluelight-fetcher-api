package dev.markusk.bluelight.api;

import dev.markusk.bluelight.api.factory.FetcherFactory;

public interface AbstractFetcher {

  void initialize();

  FetcherFactory getFetcherFactory();

}
