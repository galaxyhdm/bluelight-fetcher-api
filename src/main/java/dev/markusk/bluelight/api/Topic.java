package dev.markusk.bluelight.api;

public interface Topic {


  /**
   * @return a unique id as string
   */
  String getId();

  void setId(String id);

  /**
   * @return the topic name (example: school)
   */
  String getTopicName();

  void setTopicName(String topicName);

}
