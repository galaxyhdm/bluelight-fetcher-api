package dev.markusk.bluelight.api.impl;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.config.TargetConfiguration;
import dev.markusk.bluelight.api.data.AbstractDataManager;
import dev.markusk.bluelight.api.enums.ImportState;
import dev.markusk.bluelight.api.interfaces.Extractor;
import dev.markusk.bluelight.api.job.AbstractImportJob;
import dev.markusk.bluelight.api.objects.Article;
import dev.markusk.bluelight.api.util.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
    if (configuration.getImportStates() == null) {
      configuration.setImportStates(ImportState.getImportStates(configuration.getIndexType()));
    }
    final List<ImportState> importStates = configuration.getImportStates();

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

      importStates.forEach(
          importState -> importState.getHandler().handel(LOGGER, this.getArticle(), dataManager, extractor, parse));
    } catch (IOException e) {
      LOGGER.error("Error while indexing file", e);
    }
  }

}
