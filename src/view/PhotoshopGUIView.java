package view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

import commands.CommandTypes;
import controller.PhotoshopFeatures;
import controller.PhotoshopGUIController;
import model.ImageUtil;
import model.PhotoshopGUIModelImpl;
import model.PhotoshopModel;

public class PhotoshopGUIView extends JFrame implements PhotoshopView, ActionListener, GUIView {

  private PhotoshopModel model;
  private JTextField terminalInput;

  private JDialog dialog;
  private JTextField dialogText;
  private JComboBox dialogCombo;
  private JLabel image;
  private JButton terminalButton;
  private JButton quitButton;
  private ImageHistogram histogram;
  private JPanel container;

  PhotoshopFeatures features;

  private enum DialogType {Combo, Text}

  private DialogType latestDialog;

  public PhotoshopGUIView(PhotoshopModel model) {
    Objects.requireNonNull(model);
    this.model = model;

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
    terminalButton = new JButton("run!");
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
    brightenButton.addActionListener( this);
    secondRow.add(brightenButton);
    // flip button
    JButton flipButton;
    flipButton = new JButton("Flip");
    flipButton.setActionCommand("FlipAction");
    flipButton.addActionListener(this);
    secondRow.add( flipButton);
    // component button
    JButton componentButton;
    componentButton = new JButton("Component");
    componentButton.setActionCommand("ComponentAction");
    componentButton.addActionListener(this);
    secondRow.add( componentButton);

    // Kernel button
    JButton kernelButton;
    kernelButton = new JButton("Kernel");
    kernelButton.setActionCommand("KernelAction");
    kernelButton.addActionListener(this);
    secondRow.add( kernelButton);
    // Transform button
    JButton transformButton;
    transformButton = new JButton("Transform");
    transformButton.setActionCommand("TransformAction");
    transformButton.addActionListener(this);
    secondRow.add( transformButton);


    // quit button
    quitButton = new JButton("Quit");
    quitButton.setActionCommand("QuitAction");
    quitButton.addActionListener(this);
    thirdRow.add(quitButton);

    functions.add(BorderLayout.PAGE_START, firstRow);
    functions.add(BorderLayout.CENTER, secondRow);
    functions.add(BorderLayout.PAGE_END, thirdRow);

    ImageUtil util =new ImageUtil();

/*    histogram = new ImageHistogram(util.RGBToBufferedImage(
            util.read("C:\\soala.jpg"), BufferedImage.TYPE_4BYTE_ABGR));
    container.add(BorderLayout.LINE_END, histogram);*/

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
        this.features.runGUICommand(CommandTypes.Load);
        break;
      case "SaveAction":
        this.features.runGUICommand(CommandTypes.Save);
        break;
      case "BrightenAction":
        this.features.runGUICommand(CommandTypes.Brighten);
        break;
      case "FlipAction":
        this.features.runGUICommand(CommandTypes.Flip);
        break;
      case "ComponentAction":
        this.features.runGUICommand(CommandTypes.Component);
        break;
      case "KernelAction":
        this.features.runGUICommand(CommandTypes.Kernel);
        break;
      case "TransformAction":
        this.features.runGUICommand(CommandTypes.Transform);
        break;
      case "DialogAction":
        dialog.setVisible(false);
        break;
      case "QuitAction":
        System.exit(0);
        break;
    }
  }

  public String fileDirectory(String message, boolean save) {
    FileDialog dialog = new FileDialog((Frame) null, message);
    dialog.setMode(FileDialog.LOAD);
    if(save) {
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
    if(this.histogram != null) {
      this.histogram.setVisible(false);
    }
    this.histogram = new ImageHistogram(image);
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(BorderLayout.CENTER, histogram);
    container.add(BorderLayout.LINE_END, histogram);
  }

  @Override
  public String getDialogInput() {
    return this.latestDialog == DialogType.Text ? this.dialogText.getText() :
            this.dialogCombo.getSelectedItem().toString();
  }

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


  public static void main(String[] args) {
    PhotoshopGUIModelImpl model = new PhotoshopGUIModelImpl();
    PhotoshopGUIView view = new PhotoshopGUIView(model);
    PhotoshopGUIController controller = new PhotoshopGUIController(model, view);
    view.setFeatures(controller);
    controller.run();
  }
}


