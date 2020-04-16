package dev.markusk.bluelight.api.impl;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.Constants;
import dev.markusk.bluelight.api.builder.ArticleBuilder;
import dev.markusk.bluelight.api.config.TargetConfiguration;
import dev.markusk.bluelight.api.interfaces.Extractor;
import dev.markusk.bluelight.api.job.AbstractDownloadJob;
import dev.markusk.bluelight.api.objects.Article;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DownloadJob extends AbstractDownloadJob {

  private static final Logger LOGGER = LogManager.getLogger();

  public DownloadJob(final BaseFetchInfo baseInfo, final AbstractFetcher fetcher) {
    super(baseInfo, fetcher);
  }

  @Override
  public void run() {
    final String fileIdentification = this.getFileIdentification();
    final Extractor extractor = this.getExtractor();
    final TargetConfiguration configuration = this.getTargetConfiguration();

    String id = extractor.getIdFromUrl(this.getBaseInfo().getUrl());
    if (id == null) id = extractor.getUniqueId();

    final Article article = this.getArticle(fileIdentification, id);

    final File targetWorkDir = new File(this.getFetcher().getWorkDir(), configuration.getWorkDir());

    String[] commandArray =
        {"curl", "-A", "'" + pickUserAgent() + "'", this.getBaseInfo().getUrl(), "-L", "-o",
            article.getFileIdentification() + ".html"};

    if (configuration.isTor()) {
      String[] torCommand = {"torsocks", "-i"};
      commandArray = Stream.of(torCommand, commandArray).flatMap(Stream::of).toArray(String[]::new);
    }

    LOGGER.info(String
        .format("%s | Downloading article %s with identification: %s", this.getBaseInfo().getTargetUid(),
            article.getId(),
            article.getFileIdentification()));
    LOGGER.debug(this.getBaseInfo().getTargetUid() + " | " + Arrays.toString(commandArray));

    try {
      final Process process =
          new ProcessBuilder(commandArray).redirectErrorStream(true).directory(targetWorkDir).start();
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      LOGGER.error("Error while downloading", e);
    }

    if (configuration.isAutoIndex()) {
      this.getFetcher().getImportScheduler()
          .scheduleJob(new ImportJob(article, this.getFetcher(), targetWorkDir, this.getBaseInfo().getTargetUid()));
    }
  }

  private Article getArticle(final String fileIdentification, final String id) {
    return new ArticleBuilder()
        .id(id).url(this.getBaseInfo().getUrl()).title(this.getBaseInfo().getTitle())
        .fetchTime(this.getBaseInfo().getFetchTime()).releaseTime(this.getBaseInfo().getReleaseTime())
        .fileIdentification(fileIdentification).createArticle();
  }

  private Extractor getExtractor() {
    return this.getFetcher().getExtractorRegistry().getExtractor(this.getBaseInfo().getTargetUid());
  }

  private TargetConfiguration getTargetConfiguration() {
    return this.getFetcher().getTargetConfiguration(this.getBaseInfo().getTargetUid());
  }

  private String getFileIdentification() {
    final String fileIdentificationRaw = String
        .format("%s%s%s%s", this.getBaseInfo().getTargetUid(), this.getBaseInfo().getFetchTime(),
            this.getBaseInfo().getTitle(),
            this.getBaseInfo().getUrl());
    return DigestUtils.sha256Hex(fileIdentificationRaw);
  }

  public String pickUserAgent() {
    final List<String> userAgents = this.getFetcher().getConfiguration().getUserAgents();
    return userAgents.get(Constants.RANDOM.nextInt(userAgents.size()));
  }

}
