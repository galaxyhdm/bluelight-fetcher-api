package dev.markusk.bluelight.api.job;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.objects.Article;

import java.io.File;
import java.util.UUID;

public abstract class AbstractImportJob implements AbstractJob {

  private final AbstractFetcher fetcher;
  private final String jobId;
  private final JobPriority priority;
  private final Article article;
  private final File workDir;
  private final String targetUid;

  public AbstractImportJob(final Article article, final AbstractFetcher fetcher, final File targetWorkDir,
      final String targetUid) {
    this(UUID.randomUUID().toString(), JobPriority.NORMAL, article, fetcher, targetWorkDir, targetUid);
  }

  public AbstractImportJob(final String jobId, final JobPriority priority, final Article article,
      final AbstractFetcher fetcher,
      final File targetWorkDir, final String targetUid) {
    this.jobId = jobId;
    this.priority = priority;
    this.article = article;
    this.fetcher = fetcher;
    this.workDir = targetWorkDir;
    this.targetUid = targetUid;
  }

  public AbstractFetcher getFetcher() {
    return this.fetcher;
  }

  public Article getArticle() {
    return this.article;
  }

  public File getWorkDir() {
    return this.workDir;
  }

  public String getTargetUid() {
    return this.targetUid;
  }

  @Override
  public String getJobId() {
    return this.jobId;
  }

  @Override
  public JobPriority getPriority() {
    return this.priority;
  }

}
