package dev.markusk.bluelight.api.interfaces;

import dev.markusk.bluelight.api.data.AbstractDataManager;
import dev.markusk.bluelight.api.objects.Article;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

public interface ImportHandler {

  void handel(Logger logger, Article article, AbstractDataManager dataManager, Extractor extractor, Document parse);

}
