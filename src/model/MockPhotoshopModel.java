package model;

import java.io.IOException;
import java.util.Objects;

/**
 * MockModel for PhotoshopModel used for testing.
 */
public class MockPhotoshopModel implements PhotoshopModel {

  private final Appendable log;

  public MockPhotoshopModel(Appendable log) {

    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void loadImage(String imagePath, String imageName) {
    try {
      log.append("loadImage imagePath:" + imagePath + " imageName: " + imageName);
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed");
    }
  }

  @Override
  public void saveImage(String imagePath, String imageName) {
    try {
      log.append("saveImage imagePath:" + imagePath + " imageName: " + imageName);
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed");
    }
  }

  @Override
  public void flipImage(Direction flipDirection, String imageName, String destImageName) {
    try {
      log.append("flipImage flipDirection:" + flipDirection + " imageName: "
              + imageName + " destImageName: " + destImageName);
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed");
    }
  }

  @Override
  public void greyscaleComponent(ComponentGreyscale color, String imageName, String destImageName) {
    try {
      log.append("greyscaleComponent color:" + color + " imageName: "
              + imageName + " destImageName: " + destImageName);
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed");
    }
  }

  @Override
  public void brighten(int increment, String imageName, String destImageName) {
    try {
      log.append("brighten increment:" + increment + " imageName: "
              + imageName + " destImageName: " + destImageName);
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed");
    }
  }
}
