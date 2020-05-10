package dev.markusk.bluelight.api.impl;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.config.TargetConfiguration;
import dev.markusk.bluelight.api.data.AbstractDataManager;
import dev.markusk.bluelight.api.enums.ImportState;
import dev.markusk.bluelight.api.interfaces.Extractor;
import dev.markusk.bluelight.api.job.AbstractImportJob;
import dev.markusk.bluelight.api.objects.Article;
import dev.markusk.bluelight.api.objects.Location;
import dev.markusk.bluelight.api.objects.Topic;
import dev.markusk.bluelight.api.util.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

public class ImportJob extends AbstractImportJob {

  private static final Logger LOGGER = LogManager.getLogger();

  public ImportJob(final Article article, final AbstractFetcher fetcher, final File targetWorkDir,
      final String targetUid) {
    super(article, fetcher, targetWorkDir, targetUid);
  }

  @Override
  public void run() {
    final Article article = this.getArticle();
    LOGGER.info(String.format("%s | Indexing article %s with identification: %s", this.getTargetUid(), article.getId(),
        article.getFileIdentification()));

    final TargetConfiguration configuration = this.getFetcher().getTargetConfiguration(this.getTargetUid());
    final List<ImportState> importStates = ImportState.getImportStates(configuration.getIndexType());

    final AbstractDataManager dataManager = this.getFetcher().getDataRegistry().getDataManager(this.getTargetUid());
    if (dataManager == null) return;
    if (!dataManager.hasArticle(article.getId()))
      dataManager.addArticle(article);

    if (importStates.isEmpty()) return;
    final Extractor extractor = this.getFetcher().getExtractorRegistry().getExtractor(this.getTargetUid());
    final File articleFile = new File(this.getWorkDir(),
        String.format("%s%s", article.getFileIdentification(), FileUtils.buildFileSuffix(configuration.getSuffix())));

    try {
      final Document parse = Jsoup.parse(articleFile, StandardCharsets.UTF_8.name());

      if (importStates.contains(ImportState.CONTENT)) {
        this.indexContent(dataManager, extractor, parse);
      }
      if (importStates.contains(ImportState.TOPICS)) {
        this.indexTopics(dataManager, extractor, parse);
      }
      if (importStates.contains(ImportState.LOCATIONS)) {
        this.indexLocations(dataManager, extractor, parse);
      }

    } catch (IOException e) {
      LOGGER.error("Error while indexing file", e);
    }
  }

  private void indexContent(final AbstractDataManager dataManager, final Extractor extractor, final Document parse) {
    LOGGER.debug("Indexing article content for: " + this.getArticle().getId());
    final String content = extractor.getContent(parse);
    if (content != null) {
      this.getArticle().setContent(content);
      dataManager.updateArticleContent(this.getArticle());
    } else {
      LOGGER.warn("Content for article %s is null. Indexing skipped!");
    }
  }

  private void indexTopics(final AbstractDataManager dataManager, final Extractor extractor, final Document parse) {
    LOGGER.debug("Indexing topics for: " + this.getArticle().getId());
    final Set<Topic> topics = extractor.getTopics(parse);
    if (topics != null) {
      this.getArticle().setTopicTags(topics);
      dataManager.updateTopicLinks(this.getArticle());
    } else {
      LOGGER.warn("Topics for article %s are null. Indexing skipped!");
    }
  }

  private void indexLocations(final AbstractDataManager dataManager, final Extractor extractor, final Document parse) {
    LOGGER.debug("Indexing locations for: " + this.getArticle().getId());
    final Set<Location> locations = extractor.getLocations(parse);
    if (locations != null) {
      this.getArticle().setLocationTags(locations);
      dataManager.updateLocationLinks(this.getArticle());
    } else {
      LOGGER.warn("Locations for article %s are null. Indexing skipped!");
    }
  }

}
