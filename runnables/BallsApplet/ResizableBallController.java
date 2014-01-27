/*
* Name: Jonathan Ho
* Login: cs11fbp
* Date: November 10, 2011
* File: ResizableBallController.java
* Sources of Help: Tutor, Java API, and course textbook.
*
* This class is a WindowController object as well so it is ran as
* an applet and handles drawing objects onto the canvas. In this class
* it will handle all the events of the mouse and its actions with applet.
* This class also uses the swing API and handles events associated with
* it with event handlers. The GUI components affect the behavior of
* the resizable balls that will be drawn onto the WinowController canvas.
*/

import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ResizableBallController extends WindowController
                                     implements ActionListener,
                                                ChangeListener,
                                                MouseListener,
                                                MouseMotionListener
{ 
  private final static double PIXEL_MARGIN = 5.0;
  private final static double BALL_SIZE = 50.0;
  private final int DEFAULT_SPEED = 50;
  private final int MIN_SPEED = 1;
  private final int MAX_SPEED = 100;
  private double widthProportion, heightProportion;
  private boolean onVLine = false; // Boolean flag for vertical line
  private boolean onHLine = false; // Boolean flag for horizontal line
  private Line hLine; // Length of line depends on applet width
  private Line vLine; // Length of line depends on applet height

  // Boolean flags for the state of the button (is/not pressed)
  public static boolean isStartPressed;
  public static boolean isStopPressed;
  public static boolean isClearPressed;

  // Java GUI components
  private Container contentPane;
  private JPanel panelNorth, panelNorthLabel, panelNorthButton, panelSouth;
  public static JButton startButton, stopButton, clearButton;
  private JLabel sliderLabel, buttonLabel;
  public static JSlider speedSlider; // To be used by ResizabelBall as well
    
  // To hold varying x/y positions according to proportions
  private double propX, propY;

  // To pass in the listeners
  ResizableBall temp;

 /*
  * Name: begin()
  * Purpose: begin() is called when the applet runs. It basically
  *          initiates the variables and objects onto the canvas.
  *          and sets up the GUI components to be visible.
  * Parameters: None
  * Return: void
  */
  public void begin()
  {  
    this.widthProportion = this.heightProportion = 0.5;

    // Initialize at the default position
    this.propX = this.getWidth() / 2;
    this.propY = this.getHeight() / 2;
    
    // Drawing the horizontal and vertical lines onto the canvas
    hLine = new Line(0, 
                     this.propY,
                     canvas.getWidth(), 
                     this.propY, 
                     canvas);
    vLine = new Line(this.propX,
                     0, 
                     this.propX, 
                     canvas.getHeight(), 
                     canvas);

    isStartPressed = isStopPressed = isClearPressed = false;

    // Begin instantiation of GUI components
    contentPane = getContentPane();

    // Instantiate JPanels
    panelNorth = new JPanel(new BorderLayout());
    panelNorthLabel = new JPanel();
    panelNorthButton = new JPanel();
    panelSouth = new JPanel();

    // Instantiate JLabels
    buttonLabel = new JLabel("Resizable Balls Controls");
    sliderLabel = new JLabel("The speed is " + DEFAULT_SPEED);
    panelNorthLabel.add(buttonLabel);
    panelSouth.add(sliderLabel);

    // Instantiate JSlider
    speedSlider = new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
    speedSlider.addChangeListener(this);
    panelSouth.add(speedSlider);

    // Instantiate JButtons
    startButton = new JButton("Start");
    stopButton = new JButton("Stop");
    clearButton = new JButton("Clear All");
    panelNorthButton.add(startButton);
    panelNorthButton.add(stopButton);
    panelNorthButton.add(clearButton);
    startButton.addActionListener(this);
    stopButton.addActionListener(this);
    clearButton.addActionListener(this);

    // Add listeners to canvas
    canvas.addMouseListener(this);
    canvas.addMouseMotionListener(this);

    // Add JPanels to northern area of contentPane
    panelNorth.add(panelNorthLabel, BorderLayout.NORTH);
    panelNorth.add(panelNorthButton, BorderLayout.SOUTH);

    // Add listeners to canvas
    canvas.addMouseListener(this);
    canvas.addMouseMotionListener(this);

    // Add JPanels to northern area of contentPane
    panelNorth.add(panelNorthLabel, BorderLayout.NORTH);
    panelNorth.add(panelNorthButton, BorderLayout.SOUTH);

    // Add JPanels to the content pane
    contentPane.add(panelNorth, BorderLayout.NORTH);
    contentPane.add(panelSouth, BorderLayout.SOUTH); 
    contentPane.validate();
  }

 /*
  * Name: paint(java.awt.Graphics g)
  * Purpose: This method redraws the lines and keeps the proportion
  *          the same as the canvas size changes. This method runs
  *          continuously in the background refreshing/updating the
  *          canvas with each execution.
  * Parameters: java.awt.Graphics g
  * Return: void
  */
  public void paint(java.awt.Graphics g)
  { 
    super.paint(g);
 
    // Getting the new position correct with proportion
    this.propX = this.canvas.getWidth() * this.widthProportion;
    this.propY = this.canvas.getHeight() * this.heightProportion;

    // Setting the lines appropriately to proportions
    this.vLine.setEndPoints(this.propX, 
                            0,
                            this.propX, 
                            this.canvas.getHeight());
    this.hLine.setEndPoints(0,
                            this.propY,
                            this.canvas.getWidth(),
                            this.propY);
  }

 /*
  * Name: actionPerformed(ActionEvent e)
  * Purpose: This is the event handling method that is overriden
  *          when a class implements the ActionListener interface.
  *          The body code will then execute the appropriate action
  *          according to the source of that event. It will set
  *          the boolean flag to true which will be used for logic in
  *          the ResizableBall class.
  * Parameters: The parameter is the ActionEvent e. e holds the source
  *             of the event and from which it was called.
  * Return: void
  */
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == startButton)
    {
      isStartPressed = true;
      isStopPressed = false; 
      isClearPressed = false;
    }

    if(e.getSource() == stopButton)
    {
      isStopPressed = true;
      isStartPressed = false;
      isClearPressed = false;
    }

    if(e.getSource() == clearButton)
    {
      isClearPressed = true;
      isStartPressed = false;
      isStopPressed = false;
    }
  }

 /*
  * Name: stateChanged(ChangeEvent e)
  * Purpose: This method changes the value of the printed speed
  *          so that the user may know at what speed the ResizableBall
  *          is changing size.
  * Parameters: ChangeEvent e is the parameter which is sent once the
  *             JSlider is used, this EventHandler will then listen
  *             for that ChangeEvent e.
  * Return: void
  */
  public void stateChanged(ChangeEvent e)
  {
    this.sliderLabel.setText("The speed is " +
                             this.speedSlider.getValue());
  }

 /*
  * Name: mouseClicked(MouseEvent e)
  * Purpose: This method's purpose is to create a new ResizableBall
  *          at the cursor location if it is on the canvas and not
  *          on top of the vertical line or the horizontal line. 
  * Parameters: MouseEvent e, e holds the information of the mouse cursor
  *             at the time the event occurs.
  * Return: void
  */
  public void mouseClicked(MouseEvent e)
  {
    Location point = new Location(e.getX(), e.getY());
    
    // Do not make a ball on top of a line.
    if(this.onVLine || this.onHLine)
    {
      // Do nothing. Do not instantiate a new ResizableBall.
    }
    else
    {
      temp = new ResizableBall(point.getX(), point.getY(), BALL_SIZE,
                               canvas, hLine, vLine);
      startButton.addActionListener(temp);
      stopButton.addActionListener(temp);
      clearButton.addActionListener(temp);
      speedSlider.addChangeListener(temp);
      canvas.addMouseListener(temp);
      canvas.addMouseMotionListener(temp);
    }
  }
  
 /*
  * Name: mouseEntered(MouseEvent e)
  * Purpose: It has no purpose in this program.
  * Parameters: MouseEvent e, e holds the information of the mouse cursor
  *             at the time the event occurs.
  * Return: void
  */
  public void mouseEntered(MouseEvent e)
  {
  }

 /*
  * Name: mouseExited(MouseEvent e)
  * Purpose: It has no purpose in this program.
  * Parameters: MouseEvent e, e holds the information of the mouse cursor
  *             at the time the event occurs.
  * Return: void
  */
  public void mouseExited(MouseEvent e)
  {
  }

 /*
  * Name: mousePressed(MouseEvent e)
  * Purpose: This sets the boolean flag value to true/false depending
  *          on the position of the mouse cursor with respect to the lines.
  * Parameters: MouseEvent e, e holds the information of the mouse cursor
  *             at the time the event occurs.
  * Return: void
  */
  public void mousePressed(MouseEvent e)
  {
    Location point = new Location(e.getX(), e.getY());
    this.onHLine = this.hLine.contains(point);
    this.onVLine = this.vLine.contains(point);
  }

 /*
  * Name: mouseReleased(MouseEvent e)
  * Purpose: This will set the boolean flag for the lines on the canvas
  *          to false because it is no longer clicked on.
  * Parameters: MouseEvent e, e holds the information for the mouse
  *             cursor at the time the event occurs.
  * Return: void
  */
  public void mouseReleased(MouseEvent e)
  {
    this.onVLine = this.onHLine = false; 
  }

 /*
  * Name: mouseDragged(MouseEvent e)
  * Purpose: Checking if the mouse cursor is on a line, it'll then
  *          drag that line wherever the mouse cursor takes it.
  * Parameters: MouseEvent e, e holds the position of the cursor at
  *             the time the event occurs.
  * Return: void
  */
  public void mouseDragged(MouseEvent e)
  {
    Location point = new Location(e.getX(), e.getY());

    // Checks for horizontal line touching
    if(this.onHLine)
    {
      this.hLine.setEndPoints(0, 
                              point.getY(), 
                              this.canvas.getWidth(), 
                              point.getY());

      // Pixel margin conditions
      if(this.hLine.getStart().getY() < 5)
      {
        this.hLine.setEndPoints(0,
                                PIXEL_MARGIN,
                                this.canvas.getWidth(),
                                PIXEL_MARGIN);
      }

      else if(this.hLine.getEnd().getY() > this.canvas.getHeight() -
                                           PIXEL_MARGIN)
      {
        this.hLine.setEndPoints(0,
                                this.canvas.getHeight() - PIXEL_MARGIN,
                                this.canvas.getWidth(),
                                this.canvas.getHeight() - PIXEL_MARGIN); 
      }

      this.heightProportion = (this.hLine.getStart().getY() /
                               this.canvas.getHeight());
    }

    // Checks for vertical line touching
    if(this.onVLine)
    {
      this.vLine.setEndPoints(point.getX(),
                              0,
                              point.getX(),
                              this.canvas.getHeight());

      // Pixel margin conditions
      if(this.vLine.getEnd().getX() > this.canvas.getWidth() - PIXEL_MARGIN)
      {
        this.vLine.setEndPoints(this.canvas.getWidth() - PIXEL_MARGIN,
                                0,
                                this.canvas.getWidth() - PIXEL_MARGIN,
                                this.canvas.getHeight());
      }
      
      else if(this.vLine.getStart().getX() < PIXEL_MARGIN)
      {
        this.vLine.setEndPoints(PIXEL_MARGIN,
                                0,
                                PIXEL_MARGIN,
                                this.canvas.getHeight());
      }

      this.widthProportion = (this.vLine.getStart().getX() /
                              this.canvas.getWidth());
    } 
  }

 /*
  * Name: mouseMoved(MouseEvent e)
  * Purpose: No purpose in this program.
  * Parameters: MouseEvent e, it holds the source of the mouse cursor.
  * Return: void
  */
  public void mouseMoved(MouseEvent e)
  {
  }
}
