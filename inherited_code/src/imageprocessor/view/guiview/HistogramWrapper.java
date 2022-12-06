package imageprocessor.view.guiview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Displays the histogram graph and buttons to modify the histogram graph.
 */
public class HistogramWrapper implements HistogramDisplay {
  private int[] freq;
  private JPanel panel;

  /**
   * Displays the histogram graph and buttons to modify the histogram graph.
   */
  public HistogramWrapper() {
    this.freq = null;
    this.panel = new HistogramPanel();
    this.panel.setPreferredSize(new Dimension(520, 250));
  }

  /**
   * Updates the histogram frequencies.
   * @param freq an array that represents the histogram frequencies.
   */
  @Override
  public void updateFrequencies(int[] freq) {
    this.freq = freq;
    this.panel.repaint();
  }

  /**
   * Get the panel that the histogram buttons will be displayed on.
   * @return this panel, which is displaying the buttons
   */
  @Override
  public JPanel getPanel() {
    return this.panel;
  }

  /**
   * Paints the histogram graph.
   */
  public class HistogramPanel extends JPanel {
    private final int RECT_WIDTH = 2;
    private final int MAX_RECT_HEIGHT = 250;
    private final Color RECT_COLOR = Color.blue;

    /**
     * Constructs a new Histogram Panel.
     */
    public HistogramPanel() {
      // no code necessary.
    }

    /**
     * Takes in a graphics and paints the histogram bar graph on it.
     * Each histogram frequency is a rectangle, and size of the graph is
     * dependent on the max value.
     * @param g the <code>Graphics</code> object to protect
     */
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (freq != null) {
        int x = 1;
        g.setColor(this.RECT_COLOR);
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < freq.length; j++) {
          if (freq[j] > max) {
            max = freq[j];
          }
        }
        for (int i = 0; i < freq.length; i++) {
          // gets max
          int rectHeight = (int) (MAX_RECT_HEIGHT * (((double) freq[i]) / max));
          g.fillRect(x, MAX_RECT_HEIGHT - rectHeight, this.RECT_WIDTH, rectHeight);
          x += this.RECT_WIDTH;
        }
      }
    }
  }
}
