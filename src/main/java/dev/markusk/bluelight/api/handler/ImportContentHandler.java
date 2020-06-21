package dev.markusk.bluelight.api.handler;

import dev.markusk.bluelight.api.data.AbstractDataManager;
import dev.markusk.bluelight.api.interfaces.Extractor;
import dev.markusk.bluelight.api.interfaces.ImportHandler;
import dev.markusk.bluelight.api.objects.Article;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

public class ImportContentHandler implements ImportHandler { // TODO: 21.06.2020 look for make in implementation

  @Override
  public void handel(final Logger logger, final Article article, final AbstractDataManager dataManager,
      final Extractor extractor, final Document parse) {

    logger.debug("Indexing article content for: " + article.getId());
    final String content = extractor.getContent(parse);
    if (content != null) {
      article.setContent(content);
      dataManager.updateArticleContent(article);
    } else {
      logger.warn("Content for article %s is null. Indexing skipped!");
    }
  }

}
