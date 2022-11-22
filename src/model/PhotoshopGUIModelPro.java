package model;

import java.awt.image.BufferedImage;

/**
 * A PhotoshopModelPro that holds the most recentImage name and Image itself.
 */
public interface PhotoshopGUIModelPro extends PhotoshopModelPro {

  String getRecentImageName();

  BufferedImage getRecentImage();
}
