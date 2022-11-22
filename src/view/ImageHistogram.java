package view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.ImageUtil;
import model.enums.RGB;


/**
 * An ImageHistogram is a JPanel that displays the R G B and intesnity values of
 * an image on a histogram.
 */
public class ImageHistogram extends JPanel {

  /**
   * Converts the given BufferedImage to a histogram chart.
   * @param image image to be converted.
   */
  public ImageHistogram(BufferedImage image) {
    ImageUtil util = new ImageUtil();
    RGB[][] pixels = util.bufferedImageToRGB(image);
    red = new int[256];
    blue = new int[256];
    green = new int[256];
    intensity = new int[256];

    for (int i = 0; i < red.length; i++) {
      red[i] = 0;
      blue[i] = 0;
      green[i] = 0;
      intensity[i] = 0;
    }

    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels.length; j++) {
        red[pixels[j][i].r] += 1;
        green[pixels[j][i].g] += 1;
        blue[pixels[j][i].b] += 1;
        int intens = (pixels[j][i].r + pixels[j][i].g + pixels[j][i].b) / 3;
        intensity[Math.min(intens, 255)] += 1;
      }
    }

    int startIndex = 0;
    for (int i = 0; i < red.length; i++) {
      if (red[i] != 0 || green[i] != 0 || blue[i] != 0) {
        startIndex = i;
        break;
      }
    }

    red[startIndex] = 0;
    green[startIndex] = 0;
    blue[startIndex] = 0;

    int max = 0;
    for (int i = 0; i < red.length; i++) {
      max = Math.max(Math.max(Math.max(red[i], max), green[i]), blue[i]);
    }

    for (int i = 0; i < red.length; i++) {
      red[i] = Math.round((red[i] / (float) max) * 100);
      blue[i] = Math.round((blue[i] / (float) max) * 100);
      green[i] = Math.round((green[i] / (float) max) * 100);
      intensity[i] = Math.round((intensity[i] / (float) max) * 100);
    }



    unitX = 1f;
    unitY = endY / 100f;
  }

  private int[] red;
  private int[] green;
  private int[] blue;
  private int[] intensity;

  private int startX = 0;
  private int startY = 0;
  private int endX = 256;
  private int endY = 256;
  private float unitX;
  private float unitY;
  private int prevX = startX;
  private int prevY = endY;

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);
    for (int i = startX; i <= endX; i += unitX * 10) {
      g2d.drawLine(i, startY, i, endY);
    }
    for (int i = startY; i <= endY; i += unitY * 10) {
      g2d.drawLine(startX, i, endX, i);
    }

    g2d.setColor(Color.BLACK);
    g2d.drawLine(startX, startY, startX, endY);
    g2d.drawLine(startX, endY, endX, endY);

    g2d.setColor(Color.RED);
    drawData(red, g2d);
    g2d.setColor(Color.GREEN);
    drawData(green, g2d);
    g2d.setColor(Color.BLUE);
    drawData(blue, g2d);
    g2d.setColor(Color.GRAY);
    drawData(intensity, g2d);
  }

  private void drawData(int[] data, Graphics2D g2d) {
    for (int i = 0; i < data.length; i++) {
      int y = data[i];
      int newX = Math.round(unitX * i);
      int newY = Math.round(endY - (unitY * y));
      g2d.drawLine(prevX, prevY, newX, newY);
      prevY = newY;
      prevX = newX;
    }
    prevX = startX;
    prevY = startY;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(endX + 100, endY + 100);
  }
}


