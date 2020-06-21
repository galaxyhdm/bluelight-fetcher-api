package dev.markusk.bluelight.api.handler;

import dev.markusk.bluelight.api.data.AbstractDataManager;
import dev.markusk.bluelight.api.interfaces.Extractor;
import dev.markusk.bluelight.api.interfaces.ImportHandler;
import dev.markusk.bluelight.api.objects.Article;
import dev.markusk.bluelight.api.objects.Location;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import java.util.Set;

public class ImportLocationHandler implements ImportHandler { // TODO: 21.06.2020 look for make in implementation

  @Override
  public void handel(final Logger logger, final Article article, final AbstractDataManager dataManager,
      final Extractor extractor, final Document parse) {

    logger.debug("Indexing locations for: " + article.getId());
    final Set<Location> locations = extractor.getLocations(parse);
    if (locations != null) {
      article.setLocationTags(locations);
      dataManager.updateLocationLinks(article);
    } else {
      logger.warn(String.format("Locations for article %s are null. Indexing skipped!", article.getId()));
    }
  }
}
