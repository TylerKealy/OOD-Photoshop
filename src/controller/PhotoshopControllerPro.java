package controller;

import java.util.Scanner;

import commands.KernelCommand;
import commands.TransformCommand;
import model.Kernels.BlurKernel;
import model.Kernels.GreyscaleMatrix;
import model.Kernels.SepiaMatrix;
import model.Kernels.SharpenKernel;
import model.PhotoshopModelPro;
import view.PhotoshopView;

public class PhotoshopControllerPro extends APhotoshopController {
  /**
   * Constructor to create a PhotoshopController. Runs the model.
   *
   * @param model model to run.
   * @param view  view to show program.
   * @param rd    user input.
   */
  PhotoshopModelPro pro;
  public PhotoshopControllerPro(PhotoshopModelPro model, PhotoshopView view, Readable rd) {
    super(model, view, rd);
    this.pro = model;
  }

  @Override
  void populateCommands() {
    super.populateCommands();
    this.commands.put("blur", (Scanner s) ->
            new KernelCommand((PhotoshopModelPro) this.model, new BlurKernel(), s.next(), s.next()));
    this.commands.put("sharpen", (Scanner s) ->
            new KernelCommand((PhotoshopModelPro) this.model, new SharpenKernel(), s.next(), s.next()));
    this.commands.put("sepia", (Scanner s) ->
            new TransformCommand((PhotoshopModelPro) this.model, new SepiaMatrix(), s.next(), s.next()));
    this.commands.put("greyscale", (Scanner s) ->
            new TransformCommand((PhotoshopModelPro) this.model, new GreyscaleMatrix(), s.next(), s.next()));
  }
}
