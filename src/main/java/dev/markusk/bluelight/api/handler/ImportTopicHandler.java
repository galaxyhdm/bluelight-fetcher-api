package dev.markusk.bluelight.api.handler;

import dev.markusk.bluelight.api.data.AbstractDataManager;
import dev.markusk.bluelight.api.interfaces.Extractor;
import dev.markusk.bluelight.api.interfaces.ImportHandler;
import dev.markusk.bluelight.api.objects.Article;
import dev.markusk.bluelight.api.objects.Topic;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import java.util.Set;

public class ImportTopicHandler implements ImportHandler {

  @Override
  public void handel(final Logger logger, final Article article, final AbstractDataManager dataManager,
      final Extractor extractor, final Document parse) {

    logger.debug("Indexing topics for: " + article.getId());
    final Set<Topic> topics = extractor.getTopics(parse);
    if (topics != null) {
      article.setTopicTags(topics);
      dataManager.updateTopicLinks(article);
    } else {
      logger.warn(String.format("Topics for article %s are null. Indexing skipped!", article.getId()));
    }
  }
}
