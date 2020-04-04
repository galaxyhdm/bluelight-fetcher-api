package dev.markusk.bluelight.api.modules;

public class ModuleDescription {

  private String main;
  private String name;
  private String version;
  private String author;

  public String getMain() {
    return main;
  }

  public void setMain(final String main) {
    this.main = main;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(final String version) {
    this.version = version;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(final String author) {
    this.author = author;
  }
}
