package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import model.ImageUtil;
import model.enums.RGB;


public class ImageHistogram extends JPanel {

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

    //System.out.println("pixels squared: " + pixels.length * pixels.length);
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels.length; j++) {
        red[pixels[j][i].r] += 1;
        green[pixels[j][i].g] += 1;
        blue[pixels[j][i].b] += 1;
        int intens = (pixels[j][i].r + pixels[j][i].g + pixels[j][i].b) /3;
        intensity[Math.min(intens,255)] +=1;
        //System.out.println("r: " + red[pixels[i][j].r] + " g: " + green[pixels[i][j].g] + " b: " + blue[pixels[i][j].b]);
      }
    }

    int startIndex = 0;
    for (int i = 0; i < red.length; i++) {
      if(red[i] != 0 || green[i] != 0 || blue[i] != 0) {
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
      //System.out.println("r: " + red[i] + " g: " + green[i] + " b: " + blue[i]);
      //System.out.print("max: " + max);
    }

    for (int i = 0; i < red.length; i++) {
      red[i] = Math.round((red[i] / (float) max) * 100);
      blue[i] = Math.round((blue[i] / (float) max) * 100);
      green[i] = Math.round((green[i] / (float) max) * 100);
      intensity[i] = Math.round((intensity[i]/(float) max) * 100);
    }

/*    for (int i = 0; i < red.length; i++) {
      System.out.println("intensity[" + i + "]" + intensity[i]);
    }*/

    unitX = 1f;
    unitY = endY / 100f;
    //System.out.println("highest value: " + max + ", unitY: " + unitY);
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

    //We draw in the following 2 loops the grid so it's visible what I explained before about each "unit"
    g2d.setColor(Color.BLUE);
    for (int i = startX; i <= endX; i += unitX * 10) {
      g2d.drawLine(i, startY, i, endY);
    }

    for (int i = startY; i <= endY; i += unitY * 10) {
      g2d.drawLine(startX, i, endX, i);
    }

    //We draw the axis here instead of before because otherwise they would become blue colored.
    g2d.setColor(Color.BLACK);
    g2d.drawLine(startX, startY, startX, endY);
    g2d.drawLine(startX, endY, endX, endY);

    //We draw each of our coords in red color
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
      //System.out.println("line: (" + prevX + "," + prevY + ") -> (" + newX + "," + newY + ")");
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


