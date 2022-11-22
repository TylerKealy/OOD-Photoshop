An overview and some description about the program.

Description:all parts of the program are complete:all commands work, and the
histogram is functional. Design wise we modified 
the model for GUI to save the previosuly used image, there is the usage of
ActionEvents now, and we made a new AGUICommand abstract class that listens
for when new information is provided by the user. This needed to happen since
we need to wait for user input and also keep abstraction.

Citation for Images: https://www.pexels.com/photo/two-yellow-labrador-retriever-puppies-1108099/

previously:

Interfaces: 

PhotoshopController
	runs the program. Takes in a Model, View, and readable.
PhotoshopModel
	holds the functionality/logic of the program. Has methods like brigthen,flip,load,save etc.
PhotoshopView
	currently empty. Will hold functionality for viewing/displaying the program.
PhotoshopCommand
	using command design pattern. These commands are used through the controller.
	Saved in a hashmap, and runs corresponding methods in the given model.
IKernel
	Holds instructions to filter an image using a 2D array.
	simply has an apply function that takes in a source image, and returns a filtered output image.

Classes:

PhotoshopModelImpl
	does as the interface says. Simply an implementation of the interfacec as detailed. may
	pass in your own hashmap to use for testing purposes and allows for accessing outside of the
	class.
PhotoshopModelProImpl
	extends PhotoshopModelImpl and adds functionality for blur, sharpen, sepia and greyscale.
MockPhotoshopModel
	this class simply holds a log of which functions are being called by the controller. Used to
	test the controller.

APhotoshopController
	does as the interface says. Uses command design pattern in a Hashmap of commands. Uses purely
	text based commands. Holds all starter commands
PhotoshopControllerStarter
	extends the APhotoshopController but does not add anything. Relies on all of its base functionality.
PhotoshopControllerPro
	extends the APhotoshopController and adds a couple of commands from the Pro edition. The commands are:
	blur, sharpen, sepia, and greyscale.



AKernal
	Implemets IKernel and holds some base functionality. Has an abstract method applyPixel that all
	derived classes must implement.
Kernel
	Extends AKernel. Holds a 2D array of floats which filters a pixel using its neighbors RGB values. 
AMatrx
	Extends from AKernel, but doesn't use neighbors pixel values, just filters one to one... pixel to pixel.


Direction
	An enum that is either horiztonal or vertical. used in the model for Flip.
RGB
	A class that holds data for RGB componenets of each pixel. Has a field for red, green, blue.
ComponentGreyscale
	An enum of all possible greyscale options supported by the model. Red,Green,Blue,Luma,Value,Intensity.
