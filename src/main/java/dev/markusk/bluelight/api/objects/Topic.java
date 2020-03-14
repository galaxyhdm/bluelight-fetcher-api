package dev.markusk.bluelight.api.objects;

public class Topic {

  private String id;
  private String topicName;

  /**
   * @return a unique id as string
   */
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the topic name (example: school)
   */
  public String getTopicName() {
    return this.topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

}
