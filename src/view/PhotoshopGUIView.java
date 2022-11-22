package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

import model.PhotoshopGUIModelImpl;
import model.PhotoshopModel;

public class PhotoshopGUIView extends JFrame implements PhotoshopView, ActionListener, GUIView {

  private PhotoshopModel model;
  private JTextField terminalInput;
  private JTextField dialogInput;
  private JLabel image;
  private JButton terminalButton;
  private JButton quitButton;

  PhotoshopFeatures features;


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

  public void dialogPopup(String name) {
    // create a dialog Box
    JDialog d = new JDialog(this, "Dialog Box");

    // create a label
    JLabel l = new JLabel(name);
    // create a button
    dialogInput = new JTextField(5);
    //button
    JButton button = new JButton("Enter.");

    button.setActionCommand("DialogAction");
    button.addActionListener(this);

    // create a panel
    JPanel p = new JPanel();

    p.add(dialogInput);
    p.add(l);
    p.add(button);
    // add panel to dialog
    d.add(p);
    // setsize of dialog
    d.setSize(200, 200);
    d.pack();
    // set visibility of dialog
    d.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "TerminalAction":
        this.features.performCommand();
        break;
      case "LoadAction":
        this.features.loadImage();
        break;
      case "BrightenAction":
        this.features.brightenImage();
        break;
      case "DialogAction":
        System.out.println("dialog action");
        break;
      case "QuitAction":
        System.exit(0);
        break;
    }
  }

  String getFileLoaction() {
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

  public static void main(String[] args) {
    PhotoshopGUIModelImpl model = new PhotoshopGUIModelImpl();
    PhotoshopGUIView view = new PhotoshopGUIView(model);
    PhotoshopGUIController controller = new PhotoshopGUIController(model, view);
    view.setFeatures(controller);
    controller.run();
  }
}


