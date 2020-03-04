package dev.markusk.bluelight.api.factory;

import dev.markusk.bluelight.api.Article;
import dev.markusk.bluelight.api.Location;
import dev.markusk.bluelight.api.Topic;

public interface FetcherFactory {

  Article createArticle();

  Location createLocation();

  Topic createTopic();

}
