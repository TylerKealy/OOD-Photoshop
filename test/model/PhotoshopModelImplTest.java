package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.HashMap;
import java.util.Map;

/**
 * PhotoshopModelImpl testing.
 */
public class PhotoshopModelImplTest {

  @Test
  public void testLoad() {
    Map<String, RGB[][]> imageStorage = new HashMap<>();
    PhotoshopModelImpl model = new PhotoshopModelImpl(imageStorage);
    assertTrue(imageStorage.get("koala") == null);
    model.loadImage("images/Koala.ppm", "koala");
    assertTrue(imageStorage.get("koala") != null);
  }

  @Test
  public void testSave() {
    Map<String, RGB[][]> imageStorage = new HashMap<>();
    PhotoshopModelImpl model = new PhotoshopModelImpl(imageStorage);
    model.loadImage("images/Koala.ppm", "koala");
    RGB[][] source = imageStorage.get("koala");

    model.saveImage("images/save_test", "koala");

    model.loadImage("images/save_test.ppm", "save_test");
    RGB[][] loaded = imageStorage.get("save_test");

    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        assertEquals(source[i][j].r, loaded[i][j].r);
        assertEquals(source[i][j].g, loaded[i][j].g);
        assertEquals(source[i][j].b, loaded[i][j].b);
      }
    }
  }

  @Test
  public void testFlip() {
    Map<String, RGB[][]> imageStorage = new HashMap<>();
    PhotoshopModelImpl model = new PhotoshopModelImpl(imageStorage);
    model.loadImage("images/Koala.ppm", "koala");
    RGB[][] source = imageStorage.get("koala");

    model.flipImage(Direction.Horizontal, "koala", "h_koala");
    RGB[][] h_flip = imageStorage.get("h_koala");

    for (int col = 0; col < source.length; col++) {
      for (int row = 0; row < source[0].length; row++) {
        assertEquals(h_flip[col][source[0].length - row - 1].r, source[col][row].r);
        assertEquals(h_flip[col][source[0].length - row - 1].g, source[col][row].g);
        assertEquals(h_flip[col][source[0].length - row - 1].b, source[col][row].b);
      }
    }

    model.flipImage(Direction.Vertical, "koala", "v_koala");
    RGB[][] v_flip = imageStorage.get("v_koala");
    for (int col = 0; col < source.length; col++) {
      for (int row = 0; row < source[0].length; row++) {
        assertEquals(v_flip[source.length - col - 1][row].r, source[col][row].r);
        assertEquals(v_flip[source.length - col - 1][row].g, source[col][row].g);
        assertEquals(v_flip[source.length - col - 1][row].b, source[col][row].b);
      }
    }
  }

  @Test
  public void testBrighten() {
    Map<String, RGB[][]> imageStorage = new HashMap<>();
    PhotoshopModelImpl model = new PhotoshopModelImpl(imageStorage);
    model.loadImage("images/Koala.ppm", "koala");
    RGB[][] source = imageStorage.get("koala");

    model.brighten(10, "koala", "brighter_koala");
    RGB[][] brightened = imageStorage.get("brighter_koala");

    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        assertEquals(Math.min(source[i][j].r + 10, 255), brightened[i][j].r);
        assertEquals(Math.min(source[i][j].g + 10, 255), brightened[i][j].g);
        assertEquals(Math.min(source[i][j].b + 10, 255), brightened[i][j].b);
      }
    }

    model.brighten(300, "koala", "brighter_koala");
    brightened = imageStorage.get("brighter_koala");

    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        assertEquals(255, brightened[i][j].r);
        assertEquals(255, brightened[i][j].g);
        assertEquals(255, brightened[i][j].b);
      }
    }

    model.brighten(-10, "koala", "darker_koala");
    RGB[][] darker = imageStorage.get("darker_koala");

    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        assertEquals(Math.max(source[i][j].r - 10, 0), darker[i][j].r);
        assertEquals(Math.max(source[i][j].g - 10, 0), darker[i][j].g);
        assertEquals(Math.max(source[i][j].b - 10, 0), darker[i][j].b);
      }
    }

    model.brighten(-300, "koala", "darker_koala");
    darker = imageStorage.get("darker_koala");

    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        assertEquals(0, darker[i][j].r);
        assertEquals(0, darker[i][j].g);
        assertEquals(0, darker[i][j].b);
      }
    }
  }

  @Test
  public void testGreyscale() {
    Map<String, RGB[][]> imageStorage = new HashMap<>();
    PhotoshopModelImpl model = new PhotoshopModelImpl(imageStorage);
    model.loadImage("images/dogs.ppm", "koala");
    RGB[][] source = imageStorage.get("koala");

    model.greyscaleComponent(ComponentGreyscale.Red, "koala", "red_koala");
    RGB[][] red = imageStorage.get("red_koala");

    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        assertEquals(source[i][j].r, red[i][j].r);
        assertEquals(0, red[i][j].g);
        assertEquals(0, red[i][j].b);
      }
    }

    model.greyscaleComponent(ComponentGreyscale.Blue, "koala", "blue_koala");
    RGB[][] blue = imageStorage.get("blue_koala");
    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        assertEquals(0, blue[i][j].r);
        assertEquals(0, blue[i][j].g);
        assertEquals(source[i][j].b, blue[i][j].b);
      }
    }

    model.greyscaleComponent(ComponentGreyscale.Green, "koala", "green_koala");
    RGB[][] green = imageStorage.get("green_koala");
    for (int i = 0; i < source.length; i++) {
      for (int j = 0; j < source[0].length; j++) {
        assertEquals(0, green[i][j].r);
        assertEquals(source[i][j].g, green[i][j].g);
        assertEquals(0, green[i][j].b);

      }
    }

  }
}