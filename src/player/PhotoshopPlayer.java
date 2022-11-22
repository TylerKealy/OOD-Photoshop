package player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import controller.PhotoshopController;
import controller.PhotoshopControllerPro;
import controller.PhotoshopGUIController;
import model.PhotoshopGUIModelImpl;
import model.PhotoshopGUIModelPro;
import model.PhotoshopModelProImpl;
import view.PhotoshopGUIView;
import view.PhotoshopView;

/**
 * Used to run Photoshop.
 */
public class PhotoshopPlayer {

  /**
   * Method used to run the Photoshop program.
   *
   * @param args java stuff.
   */
  public static void main(String[] args) {
    PhotoshopModelProImpl model = new PhotoshopModelProImpl();
    PhotoshopView view = null;
    PhotoshopController controller;

    if (args.length > 0 && args[0].strip().equals("-file")) {
      String file = args[1];
      controller = new PhotoshopControllerPro(model, view, getScriptSR(file));

    } else if (args.length > 0 && args[0].strip().equals("-text")){
      controller = new PhotoshopControllerPro(model, view, new InputStreamReader(System.in));
    }else {
      PhotoshopGUIModelPro guiModelPro = new PhotoshopGUIModelImpl();
      PhotoshopGUIView gui = new PhotoshopGUIView(guiModelPro);
      PhotoshopGUIController guiController = new PhotoshopGUIController(guiModelPro, gui);
      controller = guiController;
      gui.setFeatures(guiController);
    }
    controller.run();
  }

  /**
   * Method used to get the script file, read it and perform the commands.
   *
   * @param scriptPath is the list of commands on the script file
   * @return string reader of commands
   */
  public static StringReader getScriptSR(String scriptPath) {
    try {
      File script = new File(scriptPath);
      BufferedReader br = new BufferedReader(new FileReader(script));
      String toString = "";
      for (String line; (line = br.readLine()) != null;) {
        toString += line;
      }
      return new StringReader(toString);
    } catch (IOException e) {
      throw new IllegalStateException("-file command failed.");
    }
  }


}