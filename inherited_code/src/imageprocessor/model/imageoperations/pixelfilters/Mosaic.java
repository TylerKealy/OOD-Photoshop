package imageprocessor.model.imageoperations.pixelfilters;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;

public class Mosaic implements PixelFilter {

  private HashMap<Posn, ArrayList> seeds = new HashMap<Posn, ArrayList>();
  private IImage image;

  public Mosaic(int numSeeds, IImage image) {
    this.image = image;
    for (int i = 0; i < numSeeds; i++) {
      int randX = (int) (Math.random() * image.getWidth());
      int randY = (int) (Math.random() * image.getHeight());
      Posn seed = new Posn(randX, randY);
      seeds.put(seed, new ArrayList());
    }

    for(int row = 0; row < image.getHeight(); row++) {
      for(int col = 0; col < image.getWidth(); col++) {
        Posn seed = findNearestSeed(row, col);
        seeds.get(seed).add(new Posn(row, col));
      }
    }

  }

  private Posn findNearestSeed(int r, int c) {
    float closestDistance = 100000000;
    Posn nearest = null;
    for (Posn seed : seeds.keySet()) {
      float thisDistance = (float) Math.pow(Math.abs(seed.r - r) + Math.abs(seed.c - c), 2);
      if (thisDistance < closestDistance) {
        closestDistance = thisDistance;
        nearest = seed;
      }
    }
    return nearest;
  }

  private Color averageColor(ArrayList<Posn> seedValues) {
    int totalR = 0;
    int totalG = 0;
    int totalB = 0;
    for (int i = 0; i < seedValues.size(); i++) {
      IPixel pixel = image.getPixelAt(seedValues.get(i).r, seedValues.get(i).c);
      totalR += pixel.getRedComponent();
      totalG += pixel.getGreenComponent();
      totalB += pixel.getBlueComponent();
    }
    return new Color(totalR / seedValues.size(), totalG / seedValues.size(), totalB / seedValues.size());
  }

  @Override
  public void apply(IPixel pixel, IImage image, int r, int c) {
    Posn seed = findNearestSeed(r, c);
    if(seeds.get(seed) != null) {
      Color color = averageColor(seeds.get(seed));
      pixel.setComponents(color.getRed(), color.getGreen(), color.getBlue());
    }
  }
}
