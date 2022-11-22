package view;

import javax.swing.JTextField;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import commands.GUICommandTypes;
import controller.PhotoshopFeatures;

/**
 * PhotoshopGUIView implementation. Handles the interaction and displaying of
 * the model.
 */
public class PhotoshopGUIView extends JFrame implements PhotoshopView, ActionListener, GUIView {

  private JTextField terminalInput;
  private JDialog dialog;
  private JTextField dialogText;
  private JComboBox dialogCombo;
  private JLabel image;
  private ImageHistogram histogram;
  private JPanel container;

  PhotoshopFeatures features;

  private enum DialogType { Combo, Text }

  private DialogType latestDialog;

  /**
   * Default constructor for PhotoshopGUIView. Creates the buttons and lays them out properly.
   */
  public PhotoshopGUIView() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setBounds(100, 0, 600, 400);
    container = new JPanel();
    JScrollPane scrPane = new JScrollPane(container);
    Container c = this.getContentPane();
    c.add(scrPane);
    container.setLayout(new BorderLayout());

    image = new JLabel();
    //image.setIcon(new ImageIcon("C:\\soala.jpg"));
    Dimension size = image.getPreferredSize();
    System.out.println("h: " + size.height);
    System.out.println("w: " + size.width);
    //image.setBounds(50, 30, size.width, size.height);

    ///
    JPanel terminal = new JPanel();

    // label for the field
    JLabel inputLabel = new JLabel("CMD terminal:");
    // text input field
    terminalInput = new JTextField(15);
    // run button
    JButton terminalButton = new JButton("run!");
    terminalButton.setActionCommand("TerminalAction");
    terminalButton.addActionListener(this);

    terminal.add(inputLabel);
    terminal.add(terminalInput);
    terminal.add(terminalButton);

    JPanel functions = new JPanel();
    functions.setLayout(new BorderLayout());

    JPanel firstRow = new JPanel();
    JPanel secondRow = new JPanel();
    JPanel thirdRow = new JPanel();


    // load button
    JButton loadButton;
    loadButton = new JButton("Load");
    loadButton.setActionCommand("LoadAction");
    loadButton.addActionListener(this);
    firstRow.add(loadButton);
    //save button
    JButton saveButton;
    saveButton = new JButton("Save");
    saveButton.setActionCommand("SaveAction");
    saveButton.addActionListener(this);
    firstRow.add(saveButton);


    // brighten button
    JButton brightenButton;
    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("BrightenAction");
    brightenButton.addActionListener(this);
    secondRow.add(brightenButton);
    // flip button
    JButton flipButton;
    flipButton = new JButton("Flip");
    flipButton.setActionCommand("FlipAction");
    flipButton.addActionListener(this);
    secondRow.add(flipButton);
    // component button
    JButton componentButton;
    componentButton = new JButton("Component");
    componentButton.setActionCommand("ComponentAction");
    componentButton.addActionListener(this);
    secondRow.add(componentButton);

    // Kernel button
    JButton kernelButton;
    kernelButton = new JButton("Kernel");
    kernelButton.setActionCommand("KernelAction");
    kernelButton.addActionListener(this);
    secondRow.add(kernelButton);
    // Transform button
    JButton transformButton;
    transformButton = new JButton("Transform");
    transformButton.setActionCommand("TransformAction");
    transformButton.addActionListener(this);
    secondRow.add(transformButton);


    // quit button
    JButton quitButton = new JButton("Quit");
    quitButton.setActionCommand("QuitAction");
    quitButton.addActionListener(this);
    thirdRow.add(quitButton);

    functions.add(BorderLayout.PAGE_START, firstRow);
    functions.add(BorderLayout.CENTER, secondRow);
    functions.add(BorderLayout.PAGE_END, thirdRow);

    container.add(BorderLayout.PAGE_START, terminal);
    container.add(BorderLayout.LINE_START, functions);
    container.add(BorderLayout.CENTER, image);
    this.setVisible(true);

    this.pack();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (dialog != null) {
      dialog.setVisible(false);
    }
    switch (e.getActionCommand()) {
      case "TerminalAction":
        this.features.runTerminalCommand();
        break;
      case "LoadAction":
        this.features.runGUICommand(GUICommandTypes.Load);
        break;
      case "SaveAction":
        this.features.runGUICommand(GUICommandTypes.Save);
        break;
      case "BrightenAction":
        this.features.runGUICommand(GUICommandTypes.Brighten);
        break;
      case "FlipAction":
        this.features.runGUICommand(GUICommandTypes.Flip);
        break;
      case "ComponentAction":
        this.features.runGUICommand(GUICommandTypes.Component);
        break;
      case "KernelAction":
        this.features.runGUICommand(GUICommandTypes.Kernel);
        break;
      case "TransformAction":
        this.features.runGUICommand(GUICommandTypes.Transform);
        break;
      case "DialogAction":
        dialog.setVisible(false);
        break;
      case "QuitAction":
      default:
        System.exit(0);
    }
  }

  /**
   * Provides a file directory view for either loading or saving an image.
   *
   * @param message message to be displayed to the user.
   * @param save    if true, does save view, if false, does load view.
   * @return
   */
  public String fileDirectory(String message, boolean save) {
    FileDialog dialog = new FileDialog((Frame) null, message);
    dialog.setMode(FileDialog.LOAD);
    if (save) {
      dialog.setMode(FileDialog.SAVE);
    }
    dialog.setVisible(true);
    String file = dialog.getDirectory() + dialog.getFile();
    System.out.println(file + " chosen.");
    return file;
  }

  @Override
  public String getTerminalInput() {
    return this.terminalInput.getText();
  }

  @Override
  public void setFeatures(PhotoshopFeatures features) {
    this.features = features;
  }

  @Override
  public void resetTerminalText() {
    this.terminalInput.setText("");
  }

  @Override
  public void setImage(BufferedImage image) {
    this.image.setIcon(new ImageIcon(image));
    if (this.histogram != null) {
      this.histogram.setVisible(false);
    }
    this.histogram = new ImageHistogram(image);
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(BorderLayout.CENTER, histogram);
    container.add(BorderLayout.LINE_END, histogram);
    this.pack();
  }

  @Override
  public String getDialogInput() {
    return this.latestDialog == DialogType.Text ? this.dialogText.getText() :
            this.dialogCombo.getSelectedItem().toString();
  }

  /**
   * Creates a Dialog Dropdown menu with the given options, and adds the given listener to the
   * done button.
   *
   * @param name     Message to be displayed to user.
   * @param options  the options for the dropdown.
   * @param listener listener for when the done button is hit.
   */
  public void dialogDropdown(String name, String[] options, ActionListener listener) {
    latestDialog = DialogType.Combo;

    // create a dialog Box
    dialog = new JDialog(this, "Dialog Box");


    //Dropdown
    dialogCombo = new JComboBox(options);
    //Button
    JButton button = new JButton("Enter.");
    button.setActionCommand("DialogAction");
    button.addActionListener(this);
    button.addActionListener(listener);

    // create a panel
    JPanel p = new JPanel();
    JLabel l = new JLabel(name);
    p.add(dialogCombo);
    p.add(l);
    p.add(button);

    // adding panel, and setting it visible.
    dialog.add(p);
    dialog.setSize(200, 200);
    dialog.pack();
    dialog.setVisible(true);
  }


  /**
   * Diplays Dialog Text field titled the given name, and subscribes the done button
   * to the given listener.
   *
   * @param name     message to be displayed to the user.
   * @param listener the listener to be called when the done button is hit.
   */
  public void dialogTextField(String name, ActionListener listener) {

    latestDialog = DialogType.Text;

    // create a dialog Box
    dialog = new JDialog(this, "Dialog Box");

    //button and adding listeners
    JButton button = new JButton("Enter.");
    button.setActionCommand("DialogAction");
    button.addActionListener(this);
    button.addActionListener(listener);

    // create a panel
    JPanel p = new JPanel();
    JLabel l = new JLabel(name);
    dialogText = new JTextField(5);
    p.add(dialogText);
    p.add(l);
    p.add(button);

    // adds panel to dialog box and sets it visible.
    dialog.add(p);
    dialog.setSize(200, 200);
    dialog.pack();
    dialog.setVisible(true);
  }
}


