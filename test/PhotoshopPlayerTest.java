import org.junit.Test;

import player.PhotoshopPlayer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Test for script parsing.
 */
public class PhotoshopPlayerTest {

  @Test
  public void testScriptParsing() {
    String scriptPath = "parsing_test.txt";
    String scriptText = "load images/dogs.png dogs save images/parsing_test.png dogs q ";
    try {
      FileWriter myWriter = new FileWriter(scriptPath);
      myWriter.write(scriptText);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    PhotoshopPlayer player = new PhotoshopPlayer();
    StringReader reader = player.getScriptSR(scriptPath);
    Scanner scanner = new Scanner(reader);
    String output = "";
    while (scanner.hasNext()) {
      output += scanner.next() + " ";
    }
    assertEquals(scriptText, output);


  }
}