package imageprocessor.model.components.histogram;

import imageprocessor.model.imageoperations.componentaccessers.ComponentAccesser;

/**
 * Represents a histogram with a given image and frequency.
 */
public interface IHistogram extends IHistogramState {
  /**
   * Creates the histogram frequency from the given component.
   * @param accesser the accessor that is specifying the type of component
   */
  public void createFromComponent(ComponentAccesser accesser);
}
