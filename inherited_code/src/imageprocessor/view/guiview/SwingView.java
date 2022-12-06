package imageprocessor.view.guiview;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import imageprocessor.controller.GUIController;
import imageprocessor.controller.IGUIController;
import imageprocessor.model.commands.ICommand;
import imageprocessor.model.commands.LoadImage;
import imageprocessor.model.commands.SaveImage;
import imageprocessor.model.components.histogram.IHistogram;
import imageprocessor.model.components.histogram.RGBHistogram;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.data.ImageCollectionState;
import imageprocessor.model.imageoperations.componentaccessers.BlueComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.ComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.GreenComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.IntensityComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.RedComponentAccesser;

/**
 * Represents a gui view that uses the swing application to display an interactable interface.
 */
public class SwingView extends JFrame implements IGUIView, ActionListener, ItemListener,
        ListSelectionListener {
  private ImageCollectionState model;
  private JPanel mainPanel;
  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private IGUIController controller;
  private JPanel dialogBoxesPanel;
  protected Map<String, JButton> effects;  //REVISION: made protected to allow for extension.
  private JLabel imageLabel;
  private ComponentAccesser histogramState;
  private HistogramDisplay hist;

  /**
   * Represents a class that uses the swing application to display an interactable interface
   * that allows the user to load, view, modify, and save an image. The interface works by
   * providing buttons which the user clicks in order to send commands, which will result in the
   * associated change being made to the application view.
   * @param model the provided model which the user can modify to
   */
  public SwingView(ImageCollectionState model) {
    super();
    this.model = model;
    setTitle("Image Processor");
    setSize(800, 800);

    this.mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    this.histogramState = new RedComponentAccesser();
    //scroll bars around this main panel
    this.imageScroll();
    this.createFileOpenDisplay();
    this.histogramButton();

    this.loadEffects();
    this.createEffectButtons();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setDisplayListener(this);
  }

  /**
   * Displays an image with a scrollbar.
   */
  public void imageScroll() {
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    imagePanel.setLayout(new FlowLayout());
    //imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    this.imageLabel = new JLabel();
    imagePanel.add(this.imageLabel);
    JScrollPane imageScrollPane = new JScrollPane(imagePanel);
    imageLabel.setIcon(new ImageIcon());
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    mainPanel.add(imageScrollPane);
  }

  /**
   * Make a panel with four buttons to modify the histogram bar graph.
   */
  public void histogramButton() {
    JPanel histogramPanel = new JPanel();

    JPanel histogramWrapper = new JPanel();
    hist = new HistogramWrapper();

    histogramWrapper.add(hist.getPanel());
    histogramWrapper.setBorder(BorderFactory.createTitledBorder("Histogram"));
    histogramWrapper.setPreferredSize(new Dimension(520, 250));
    histogramWrapper.setLayout(new FlowLayout());

    JPanel histogramButtonPanel = new JPanel();
    this.createHistogramButton("Histogram Red", histogramButtonPanel);
    this.createHistogramButton("Histogram Green", histogramButtonPanel);
    this.createHistogramButton("Histogram Blue", histogramButtonPanel);
    this.createHistogramButton("Histogram Intensity", histogramButtonPanel);

    histogramButtonPanel.setPreferredSize(new Dimension(200, 250));

    histogramPanel.add(histogramWrapper);
    histogramPanel.add(histogramButtonPanel);
    histogramPanel.setLayout(new FlowLayout());

    mainPanel.add(histogramPanel);

  }

  /**
   * Creates the histogram buttons and adds them to the panel.
   * @param a the action command
   * @param panel the panel to add the buttons to
   */
  public void createHistogramButton(String a, JPanel panel) {
    JButton histogramButton = new JButton(a);
    histogramButton.setActionCommand(a);
    histogramButton.addActionListener(this);
    panel.add(histogramButton);
  }

  /**
   * Creates a file open panel with the open file and save file buttons.
   */
  private void createFileOpenDisplay() {
    // open image
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());

    this.fileOpenButton = new JButton("Open file");
    fileOpenButton.setActionCommand("Open file");
    fileopenPanel.add(fileOpenButton);

    this.fileSaveButton = new JButton("Save file");
    fileSaveButton.setActionCommand("Save file");
    fileopenPanel.add(fileSaveButton);

    mainPanel.add(fileopenPanel);

  }

  /**
   * Loads the effects into a hashmap.
   *
   * REVISION: made protected to allow for extension.
   */
  protected void loadEffects() {
    this.effects = new LinkedHashMap<String, JButton>();

    this.effects.put("Flip Vertically", null);
    this.effects.put("Flip Horizontally", null);
    this.effects.put("Brighten +10", null);
    this.effects.put("Darken +10", null);
    this.effects.put("Red Greyscale", null);
    this.effects.put("Green Greyscale", null);
    this.effects.put("Blue Greyscale", null);
    this.effects.put("Intensity Greyscale", null);
    this.effects.put("Luma Greyscale", null);
    this.effects.put("Value Greyscale", null);
    this.effects.put("Greyscale", null);
    this.effects.put("Sepia Tone", null);
    this.effects.put("Gaussian Blur", null);
    this.effects.put("Sharpen", null);
  }

  /**
   * Creates a create effects panel with each of the effects in the hashmap.
   */
  public void createEffectButtons() {
    JPanel effectsPanel = new JPanel();
    effectsPanel.setLayout(new FlowLayout());
    effectsPanel.setPreferredSize(new Dimension(100, 100));
    mainPanel.add(effectsPanel);

    for (var entrySet : this.effects.entrySet()) {
      String name = entrySet.getKey();
      JButton effectButton = new JButton(name);
      effectButton.setActionCommand(name);
      effectsPanel.add(effectButton);
      entrySet.setValue(effectButton);
    }
  }

  /**
   * Takes in an action listener and sets a listener for each effect button.
   * The action listener listens for when the button is clicked.
   * @param listener the given action listener
   */
  public void setActionListener(ActionListener listener) {
    this.controller = (GUIController) listener;
    for (var entrySet : this.effects.entrySet()) {
      JButton effectButton = entrySet.getValue();
      effectButton.addActionListener(listener);
    }
  }

  /**
   * Takes in an action listener and sets a listener for the file open and file save buttons.
   * The action listener listens for when the button is clicked.
   * @param listener the given action listener
   */
  public void setDisplayListener(ActionListener listener) {
    this.fileOpenButton.addActionListener(listener);
    this.fileSaveButton.addActionListener(listener);
  }

  /**
   * Opens a file using the JFileChooser and load command.
   * Throws an exception if it's not a valid file.
   */
  private void openFile() {
    try {
      final JFileChooser fchooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG, JPEG, PNG, BMP, PPM Images", "jpg", "png", "jpeg", "bmp", "ppm");
      fchooser.setFileFilter(filter);
      int retvalue = fchooser.showOpenDialog(SwingView.this);
      ICommand load = null;
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        ImageCollection collection = (ImageCollection) this.model;
        load = new LoadImage(collection, f.getAbsolutePath(), f.getName());
        this.controller.acceptCommand(load);
        this.updateData();
      }
    }
    catch (IllegalStateException e) {
      JOptionPane.showMessageDialog(null,
              "Not a valid file type!",
              "Error", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  /**
   * Saves a file using the JFileChooser and save command.
   * Throws an exception if it's not a valid file.
   */
  private void saveFile() {
    try {
      final JFileChooser fchooser = new JFileChooser(".");
      int retvalue = fchooser.showSaveDialog(SwingView.this);
      ICommand save = null;
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        ImageCollection collection = (ImageCollection) this.model;
        save = new SaveImage(collection, f.getAbsolutePath(), this.model.getSelectedImage());
        this.controller.acceptCommand(save);
      }
    }
    catch (IllegalStateException e) {
      JOptionPane.showMessageDialog(null,
              "Could not save to a file of that type!",
              "Error", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  /**
   * Takes in an event and performs teh corresponding change.
   * Example: Call open file to open a file when given action event "open file".
   * @param argument the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent argument) {
    switch (argument.getActionCommand()) {
      case "Open file": {
        this.openFile();
        break;
      }
      case "Save file": {
        this.saveFile();
        break;
      }
      case "Histogram Red": {
        this.updateHistogram(new RedComponentAccesser());
        break;
      }
      case "Histogram Green": {
        this.updateHistogram(new GreenComponentAccesser());
        break;
      }
      case "Histogram Blue": {
        this.updateHistogram(new BlueComponentAccesser());
        break;
      }
      case "Histogram Intensity": {
        this.updateHistogram(new IntensityComponentAccesser());
        break;
      }
      default: {
        // not possible under normal circumstances due to button based gui.
      }
    }
  }

  /**
   * Update this histogram and its frequencies with the given component accessor.
   * @param accesser the given component accessor (ex: red component accessor)
   */
  public void updateHistogram(ComponentAccesser accesser) {
    this.histogramState = accesser;
    this.hist.updateFrequencies(this.componentToFreq(this.histogramState));
  }

  /**
   * Takes in a component accessor and converts it to frequency.
   * @param accesser the given component accessor
   * @return the new frequency
   */
  public int[] componentToFreq(ComponentAccesser accesser) {
    //get the image that is being worked on and use it to make a new histogram
    String name = this.model.getSelectedImage();
    int [] freq = null;

    if (name != null) {
      IHistogram histogram = new RGBHistogram(this.model.getImage(name));
      // get the histogram freq
      histogram.createFromComponent(accesser);
      freq = histogram.getHistogram();

    }
    return freq;
  }



  /**
   * Takes in data from the controller in order to update the data of the image.
   */
  public void updateData() {
    String imgName = this.model.getSelectedImage();
    IImage img = this.model.getImage(imgName);
    BufferedImage bufImg = img.toBufferedImage(BufferedImage.TYPE_INT_RGB);
    this.imageLabel.setIcon(new ImageIcon(bufImg));
    this.updateHistogram(this.histogramState);
  }

  /**
   * An empty method.
   * @param e the event to be processed
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
    // No code necessary.
  }

  /**
   * An empty method.
   * @param e the event that characterizes the change.
   */
  @Override
  public void valueChanged(ListSelectionEvent e) {
    // No code necessary.
  }
}
