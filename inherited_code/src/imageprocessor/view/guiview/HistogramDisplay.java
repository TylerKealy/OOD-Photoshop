package imageprocessor.view.guiview;

import javax.swing.JPanel;

/**
 * Displays the histogram graph and buttons to modify the histogram graph.
 */
public interface HistogramDisplay {
  /**
   * Updates the histogram frequencies.
   * @param freq an array that represents the histogram frequencies.
   */
  public void updateFrequencies(int[] freq);

  /**
   * Get the panel that the histogram buttons will be displayed on.
   * @return this panel, which is displaying the buttons
   */
  public JPanel getPanel();
}
