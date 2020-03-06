package dev.markusk.bluelight.api.factory;

import dev.markusk.bluelight.api.objects.Article;
import dev.markusk.bluelight.api.objects.Location;
import dev.markusk.bluelight.api.objects.Topic;

public interface FetcherFactory {

  Article createArticle();

  Location createLocation();

  Topic createTopic();

}
