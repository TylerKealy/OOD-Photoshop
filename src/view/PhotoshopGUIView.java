package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

import commands.CommandTypes;
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

  PhotoshopFeatures features;

  private enum DialogType {Combo, Text}

  ;
  private DialogType latestDialog;

  public PhotoshopGUIView(PhotoshopModel model) {
    Objects.requireNonNull(model);
    this.model = model;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setBounds(100, 0, 600, 400);
    Container c = this.getContentPane();

    image = new JLabel();
    image.setIcon(new ImageIcon("C:\\soala.jpg"));
    Dimension size = image.getPreferredSize();
    System.out.println("h: " + size.height);
    System.out.println("w: " + size.width);
    //image.setBounds(50, 30, size.width, size.height);

    ///
    JPanel terminal = new JPanel();

    // label for the field
    JLabel inputLabel = new JLabel("CMD:");
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
    // load button
    JButton loadButton;
    loadButton = new JButton("Load");
    loadButton.setActionCommand("LoadAction");
    loadButton.addActionListener(this);
    functions.add(loadButton);
    // brighten button
    JButton brightenButton;
    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("BrightenAction");
    brightenButton.addActionListener(this);
    functions.add(brightenButton);
    // flip button
    JButton flipButton;
    flipButton = new JButton("Flip");
    flipButton.setActionCommand("FlipAction");
    flipButton.addActionListener(this);
    functions.add(flipButton);
    // component button
    JButton componentButton;
    componentButton = new JButton("Component");
    componentButton.setActionCommand("ComponentAction");
    componentButton.addActionListener(this);
    functions.add(componentButton);
    // Kernel button
    JButton kernelButton;
    kernelButton = new JButton("Kernel");
    kernelButton.setActionCommand("KernelAction");
    kernelButton.addActionListener(this);
    functions.add(kernelButton);
    // Transform button
    JButton transformButton;
    transformButton = new JButton("Transform");
    transformButton.setActionCommand("TransformAction");
    transformButton.addActionListener(this);
    functions.add(transformButton);
    // quit button
    quitButton = new JButton("Quit");
    quitButton.setActionCommand("QuitAction");
    quitButton.addActionListener(this);
    functions.add(quitButton);

    c.add(BorderLayout.PAGE_START, terminal);
    c.add(BorderLayout.LINE_START, functions);
    c.add(BorderLayout.CENTER, image);
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

  String getFileLocation() {
    FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
    dialog.setMode(FileDialog.LOAD);
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


