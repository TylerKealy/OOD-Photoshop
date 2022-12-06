package imageprocessor.model.components.histogram;

/**
 * An interface that can retreive the histogram's data.
 */
public interface IHistogramState {
  /**
   * Retrieves the frequency of the histogram.
   * @return the frequency of the histogram.
   */
  public int[] getHistogram();
}
