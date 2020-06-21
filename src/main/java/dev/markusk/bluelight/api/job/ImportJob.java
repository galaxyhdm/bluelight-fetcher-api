package dev.markusk.bluelight.api.job;

import dev.markusk.bluelight.api.AbstractFetcher;
import dev.markusk.bluelight.api.objects.Article;

import java.io.File;

public interface ImportJob extends AbstractJob{

  AbstractFetcher getFetcher();

  Article getArticle();

  File getWorkDir();

  String getTargetUid();

}
