/*
* Name: Jonathan Ho
* Login: cs11fbp
* Date: November 10, 2011
* File: ResizableBall.java
* Sources of Help: Tutor, Java API, and course textbook.
*
* The class ResizableBall can be instantiated in other programs 
* to draw balls that are resizable onto the canvas. All logic
* concerning the ActiveObject that is ResizableBall, will be
* handled within its own class. The updates and logic as well as
* GUI componenets will be considered and updated for by its own methods. 
*/
import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ResizableBall extends ActiveObject
                           implements ActionListener,
                                      ChangeListener,
                                      MouseListener,
                                      MouseMotionListener
{
  // Used to calculate the pause time
  private final static int PAUSE = 101;
  // Represents Maximum size for ball
  private final static double MAX_BALL_SIZE = 100.0;
  // Represents Minimum size for ball
  private final static double MIN_BALL_SIZE = 25.0;
  // Growth rate for resizing
  private int gRate = 2;
  // Center of the ball
  private Location ballCenter;
  // The image of the ball
  private FilledOval ballGraphic;
  // The canvas
  private DrawingCanvas canvas;
  // Value holders for the Lines passed in
  private Line vLine, hLine;
  // Boolean to determine if dragging is viable  
  private boolean canDrag = false;
  // Pixel margin
  private final static int PIXEL_MARGIN = 5;

 /*
  * Name: ResizableBall(double xLoc, double yLoc, double size,
  *                     DrawingCanvas canvas, Line hLine, Line vLine)
  * Purpose: This constructor creates a new ball where the mouse cursor
  *          clicked and draws the ball onto the canvas with an initial
  *          size. The Lines are then used to determine which quadrant
  *          the ball is in which determines its color.
  * Parameters:
  * Return: The constructor returns nothing.
  */ 
  public ResizableBall(double xLoc, double yLoc, double size,
                       DrawingCanvas canvas, Line hLine, Line vLine)
  {
    this.canvas = canvas;
    
    // Reference to Line is still kept as it updates
    this.hLine = hLine;
    this.vLine = vLine;

    this.ballCenter = new Location(xLoc, yLoc);

    this.ballGraphic = new FilledOval(xLoc - (size / 2), // Shift to center
                                      yLoc - (size / 2), // Shift to center
                                      size, 
                                      size, 
                                      this.canvas);

    // Calling this method will start the run() method 
    this.start();
  }

 /*
  * Name: actionPerformed(ActionEvent e)
  * Purpose: The ResizableBall will also listen for the event of
  *          a JButton being pressed. It will then determine whether
  *          to continue growing, stop growing, or to have its
  *          thread killed.
  * Parameters: ActionEvent e, e is the event that a JButton is pressed
  *             in ResizableBallController.
  * Return: void 
  */
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == ResizableBallController.startButton)
    {
      // Used public static boolean from controller class instead.
    }

    if(e.getSource() == ResizableBallController.stopButton)
    {
      // Used public static boolean from controller class instead.
    }

    if(e.getSource() == ResizableBallController.clearButton)
    {
      this.ballGraphic.hide();
    }
  }
  
 /*
  * Name: stateChanged(ChangeEvent e)
  * Purpose: To listen to the events of the JSlider being used.
  *          The value of the JSlider can then be referenced from
  *          the ChangeEvent e parameter.
  * Parameters: ChangeEvent e is the parameter. e holds the information
  *             of the current JSlider position.
  * Return: void
  */
  public void stateChanged(ChangeEvent e)
  {
    // The value was used referencing the public static variable
    // from the controller class
  }

 /*
  * Name: run()
  * Purpose: Throughout the runtime of the program this method will
  *          run an infinite loop and check if any necessary updates
  *          need to be made, such as changing the size or color
  *          of the ball.
  * Parameters: None
  * Return: void
  */
  public void run()
  {
    // Each iteration will be a check and update
    while (true)
    {
      this.updateBallSize();
      this.updateBallColor();
      this.pause(PAUSE - ResizableBallController.speedSlider.getValue());
      
      // Remove the ball from the canvas and delete its space in memory.
      if(this.ballGraphic.isHidden())
      {
        this.ballGraphic.removeFromCanvas();
        break; // To escape the infinite loop
      }
    }
  }

 /*
  * Name: updateBallSize()
  * Purpose: To check for when the ball size reaches a minimum or
  *          maximum in size where it then toggles its growth to 
  *          its complement. The balls only grow is it is allowable
  *          meaning that start is pressed. If stop is pressed
  * Parameters: None
  * Return: void
  */
  private void updateBallSize()
  {
    if(ResizableBallController.isStartPressed)
    {
      this.ballGraphic.move(-gRate/2, -gRate/2);
      this.ballGraphic.setSize(this.ballGraphic.getWidth() + gRate,
                               this.ballGraphic.getHeight() + gRate);

      // Toggle to shrink
      if(this.ballGraphic.getHeight() > MAX_BALL_SIZE ||
         this.ballGraphic.getWidth() > MAX_BALL_SIZE)
      {
        this.gRate = -this.gRate;
      }

      // Toggle to grow
      if(this.ballGraphic.getHeight() < MIN_BALL_SIZE ||
         this.ballGraphic.getWidth() < MIN_BALL_SIZE)
      {
        this.gRate = -this.gRate;
      }
    }

    if(ResizableBallController.isStopPressed)
    {
      // Do nothing. The balls will not grow.
    }
  }

 /*
  * Name: updateBallColor()
  * Purpose: To check for when the ball color should change and to
  *          what color depending on the quadrant the ball is in.
  * Parameters: None
  * Return: void
  */ 
  private void updateBallColor()
  {
    Location cCenter = new Location((this.ballGraphic.getX() + 
                                    this.ballGraphic.getWidth() / 2),
                                    (this.ballGraphic.getY() +
                                    this.ballGraphic.getHeight() / 2));

    // Quadrant I, MAGENTA
    if(this.vLine.getStart().getX() < cCenter.getX() &&
       this.hLine.getStart().getY() > cCenter.getY())
    {
      this.ballGraphic.setColor(Color.MAGENTA);
    }

    // Quadrant II, CYAN
    else if(this.vLine.getStart().getX() > cCenter.getX() &&
            this.hLine.getStart().getY() > cCenter.getY())
    {
      this.ballGraphic.setColor(Color.CYAN);
    }
    
    // Quadrant III, YELLOW
    else if(this.vLine.getStart().getX() > cCenter.getX() &&
            this.hLine.getStart().getY() < cCenter.getY())
    {
      this.ballGraphic.setColor(Color.YELLOW);
    }

    // Quadrant IV, BLACK
    else
    {
      // No need for additional conditionals since other 3 are checked
      this.ballGraphic.setColor(Color.BLACK);
    }
  }

 /*
  * Name: mouseClicked(MouseEvent e)
  * Purpose: Has no purpose in this program.
  * Parameters: MouseEvent e, e holds the information for the mouse cursor.
  * Return: void
  */
  public void mouseClicked(MouseEvent e)
  {
  }

 /*
  * Name: mouseDragged(MouseEvent e)
  * Purpose: If the click is on the FilledOval, then the FilledOval will
  *          be dragged according to the position of the mouse cursor.
  * Parameters: MouseEvent e, e holds the information for the mouse cursor.
  * Return: void
  */
  public void mouseDragged(MouseEvent e)
  {
    Location point = new Location(e.getX(), e.getY());
    Location midPoint = new Location((point.getX() -
                                     ballGraphic.getWidth() / 2),
                                     (point.getY() -
                                     ballGraphic.getHeight() / 2));
    if(this.canDrag)
    {
      this.ballGraphic.moveTo(midPoint);
      if(midPoint.getX() < PIXEL_MARGIN)
      {
        this.ballGraphic.moveTo(PIXEL_MARGIN, 
                                midPoint.getY());
      }
      
      if(midPoint.getX() > this.canvas.getWidth() - PIXEL_MARGIN)
      {
        this.ballGraphic.moveTo(this.canvas.getWidth() - PIXEL_MARGIN,
                                midPoint.getY());
      }

      if(midPoint.getY() < PIXEL_MARGIN)
      {
        this.ballGraphic.moveTo(midPoint.getX(), PIXEL_MARGIN);
      }
   
      if(midPoint.getY() > this.canvas.getHeight() - PIXEL_MARGIN)
      {
        this.ballGraphic.moveTo(midPoint.getX(),
                                this.canvas.getHeight() - PIXEL_MARGIN);
      }
    }
  }

 /*
  * Name: mouseEntered(MouseEvent e)
  * Purpose: Has no purpose in this program.
  * Parameters: MouseEvent e, e holds the information for the mouse cursor.
  * Return: void
  */
  public void mouseEntered(MouseEvent e)
  {
  }

 /*
  * Name: mouseExited(MouseEvent e)
  * Purpose: Has no purpose in this program.
  * Parameters: MouseEvent e, e holds the information for the mouse cursor.
  * Return: void
  */
  public void mouseExited(MouseEvent e)
  {
  }

 /*
  * Name: mousePressed(MouseEvent e)
  * Purpose: Sets the boolean flag to true so that the ball maybe dragged.
  * Parameters: MouseEvent e, e holds the information for the mouse cursor.
  * Return: void
  */
  public void mousePressed(MouseEvent e)
  {
    Location point = new Location(e.getX(), e.getY());
 
    if(this.ballGraphic.contains(point))
    {
      this.canDrag = true;
    } 
  }

 /*
  * Name: mouseReleased(MouseEvent e)
  * Purpose: Upon mouse release the boolean for dragging will be set to
  *          false so it may be set to true again for mouseClick.
  * Parameters: MouseEvent e, e holds the information for the mouse cursor.
  * Return: void
  */
  public void mouseReleased(MouseEvent e)
  {
    this.canDrag = false;
  }

 /*
  * Name: mouseMoved(MouseEvent e)
  * Purpose: Has no purpose in this program.
  * Parameters: MouseEvent e, e holds the information for the mouse cursor.
  * Return: void
  */
  public void mouseMoved(MouseEvent e)
  {
  }
}
