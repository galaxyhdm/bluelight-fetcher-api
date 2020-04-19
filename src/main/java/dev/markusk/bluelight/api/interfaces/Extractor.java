package dev.markusk.bluelight.api.interfaces;

import dev.markusk.bluelight.api.objects.Location;
import dev.markusk.bluelight.api.objects.Topic;
import org.jsoup.nodes.Document;

import java.util.Set;

public interface Extractor {

  /**
   * @param url the article url
   * @return a unique id provided by the url. For example when the url ends with a unique id.
   * <p>
   * When returning null getUniqueId is calling
   */
  String getIdFromUrl(String url);

  /**
   * This method is called when getIdFromUrl returns null.
   *
   * @return a unique id.
   */
  String getUniqueId();

  /**
   * This method is used to extract the location from the downloaded website.
   *
   * @param document a jsoup document with the html source.
   * @return a set with all {@link Location}s. This can be null to provide no data.
   */
  Set<Location> getLocations(Document document);

  /**
   * This method is used to extract the topics from the downloaded website.
   *
   * @param document a jsoup document with the html source.
   * @return a set with all {@link Topic}s. This can be null to provide no data.
   */
  Set<Topic> getTopics(Document document);

  /**
   * This method is used to extract the content from the downloaded website.
   *
   * @param document a jsoup document with the html source.
   * @return a string with the main article content. This can be null to provide no data.
   */
  String getContent(Document document);

}
