package model;

import java.awt.*;

/**
 * Stores the RGB value of a pixel.
 */
public class RGB {
    public int r;
    public int g;
    public int b;

    /**
     * Create an object to store RGB values.
     *
     * @param r red
     * @param g green
     * @param b blue
     */
    public RGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }


    /**
     * Takes in a singular int that represents RGB value and converts to a RGB object.
     *
     * @param rgbValue The output from a BufferImage.getRGB()
     */
    public RGB(int rgbValue) {
        Color pColor = new Color(rgbValue);
        this.r = pColor.getRed();
        this.g = pColor.getGreen();
        this.b = pColor.getBlue();

    }
}
