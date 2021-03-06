package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  public void mirrorTopButtom() {
	  Pixel[][] pixel = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel buttomPixel = null;
	  int height = pixel.length;
	  for (int col = 0; col < pixel[0].length;col++) {
		  for (int row = 0; row < height;row++) {
			  buttomPixel = pixel[row][col];
			  topPixel = pixel[height - 1 - row][col];
			  buttomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  public void somethingFilter(int startRow, int startCol) {
	  Pixel fromPixel = null;
	  Pixel toPixel = null;
	  Picture something = new Picture("kitten2.jpg");
	  Pixel[][] toPixels = this.getPixels2D();
	  Pixel[][] fromPixels = something.getPixels2D();
	  int fromRow = 0;
	  for (int toRow = startRow; toRow < toPixels.length && fromRow < fromPixels.length;toRow++) 
	  {
		  int fromCol = 0;
		  for (int toCol = 0; toCol < toPixels[0].length && fromCol < fromPixels[0].length; toCol++) {
			  fromPixel = fromPixels[fromRow][fromCol];
			  toPixel = toPixels[toRow][toCol];
			  if (!fromPixel.isClear()) {
				  toPixel.setRed(fromPixel.getRed());
				  toPixel.setBlue(fromPixel.getBlue());
				  toPixel.setGreen(fromPixel.getGreen());
			  }
			  fromCol++;
		  }
		  fromRow++;
	  }
  }
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
public void filter(int startRow, int startCol) {
	Pixel fromPixel = null;
	Pixel toPixel = null;
	Picture replace = new Picture("picture.png");
}
  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void glitchArt() {
	  Pixel[][] pixels = this.getPixels2D();
	  int width = pixels[0].length;
	  int shift = (int)(pixels[0].length * .2);
	  int greenInt = 0;
	  int redInt = 0;
	  int blueInt = 0;
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  Color [] currentColors = new Color[width];
		  	for (int col = 0; col < pixels[row].length; col++)
	    		{
	    			currentColors[col] = pixels[row][col].getColor();
	    		}
	    		for (int col = 0; col < width; col++)
	    		{
	    			pixels[row][col].setColor(currentColors[(col+ shift) % width]);
	    		}
	   }
	   for (int row = 25; row < 130; row++)
	   {
		   for (int col = 35; col < 190; col++)
		   {
			   redInt = pixels[row][col].getRed();
			   blueInt = pixels[row][col].getBlue();
			   pixels[row][col].setColor(new Color(redInt, 0, blueInt));
			}
		}
	   for (int row = 200 ; row < 240; row++)
	   {
		   for (int col = 250; col < 300; col ++)
		   {
			   redInt = (int) (Math.random() * 255);
			   blueInt = (int) (Math.random() * 255);
			   greenInt = (int) (Math.random() * 255);
			   pixels[row][col].setColor(new Color(redInt, greenInt, blueInt));
			}
		}
		for (int row = 250 ; row < 290; row++)
		{
			for (int col = 100; col < 150; col ++)
			{
				redInt = (int) (Math.random() * 255);
				blueInt = (int) (Math.random() * 255);
				greenInt = (int) (Math.random() * 255);
				pixels[row][col].setColor(new Color(redInt, greenInt, blueInt));
			}
		}

  }
  public void shift() {
	  Pixel[][] pixels = this.getPixels2D();
	  int rows = pixels.length;
	  int cols = pixels[0].length;
	  Color[] firstColor = new Color[cols];
	  int shiftAmount = (int)(.2 * pixels[0].length);
	  for (int row = 0; row < rows; row++) {
		  for (int col = 0; col < cols; col++) {
			  pixels[row][(col + shiftAmount) % cols].setColor(pixels[row][col].getColor());
		  }
	  }
  }
  public void classFilter() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (int row = 0; row < 20; row++) {
		  for (int col = 0; col < pixels[0].length;col++) {
			  pixels[row][col].setColor(pixels[row+20][col].getColor()); 
		  }
	  }
	  addMessage("JUAN DELACRUZ - AM CLASS - CLASS FILTER",100,100,Color.RED);
	  write("JuanDelacruzClassFilter.jpg");
  }
  public void blueGet() {
	  Pixel[][] pixels = this.getPixels2D();
	  int row = pixels.length;
	  int col = pixels[0].length;
	  Color[][] blueColor = new Color[row][col];
	  for(int indexX = 0; indexX < row; indexX++) {
		  
	  }
	  
  }
  public static void testGlitch() {
	  Picture beach = new Picture("beach.jpg");
	  beach.glitchArt();
  }
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    //beach.glitchArt();
    //beach.shift();
    beach.explore();
    //beach.somethingFilter(50, 30);
    ///Users/jdel8359/Downloads/originalArt.pngbeach.zeroBlue();
    beach.classFilter();
    beach.explore();
    //testGlitch();
 
  }
  
} // this } is the end of class Picture, put all new methods before this
