/*
 CS 321 Group 2
 Group Names: Kendall Ayers, William Horan, Montapon Sawettanan, David Van Harlingen
 Disability Calculator
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Calculator extends JFrame implements ActionListener, KeyListener{
  // Variables
  final int MAX_INPUT_LENGTH = 20;
  final int INPUT_MODE = 0;
  final int RESULT_MODE = 1;
  final int ERROR_MODE = 2;
  final static Color lowTemp = new Color(226, 153, 0);
  int displayMode;
  
  boolean clearOnNextDigit, percent, col = false, dys = false, eye = false, hideBool = false;
  double lastNumber;
  String lastOperator;
  
  private JMenu jmenuFile, jmenuHelp, jmenuAccess;
  static JMenuItem jmenuitemExit, jmenuitemAbout, jmenuitemPopout;
  static JCheckBoxMenuItem jmenuitemDys,jmenuitemCol,jmenuitemEye;
  private JDialog optPan;
  
  private JLabel jlbOutput;
  private JButton jbnButtons[];
  private JPanel jplMaster, jplBackSpace, jplControl;
  private JPanel jplButtons = new JPanel();   // container for Jbuttons
  
  /*
   * Font(String name, int style, int size)
   Creates a new Font from the specified name, style and point size.
   */
  
  Font f12 = new Font("Times New Roman", 0, 20);
  Font f121 = new Font("Times New Roman", 1, 20);
  Font fd = importFont();
  
  // Constructor 
  public Calculator() 
  {
    /* Set Up the JMenuBar.
     * Have Provided All JMenu's with Mnemonics
     * Have Provided some JMenuItem components with Keyboard Accelerators
     */ 
    
    jmenuFile = new JMenu("File");
    jmenuFile.setFont(f121);
    jmenuFile.setMnemonic(KeyEvent.VK_F);
 jmenuFile.setName("File");
    
    jmenuitemExit = new JMenuItem("Exit");
    jmenuitemExit.setFont(f12);
 jmenuitemExit.setName("Exit");
    jmenuitemExit.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_X, 
                                                        ActionEvent.CTRL_MASK));
    jmenuFile.add(jmenuitemExit);
    
    jmenuHelp = new JMenu("Info");
    jmenuHelp.setFont(f121);
    jmenuHelp.setMnemonic(KeyEvent.VK_I);
 jmenuHelp.setName("Info");
    
    jmenuitemAbout = new JMenuItem("About Calculator");
    jmenuitemAbout.setFont(f12);
    jmenuHelp.add(jmenuitemAbout);
    
    //Add Accessability Menu
    jmenuAccess = new JMenu("Accessability");
    jmenuAccess.setFont(f121);
    jmenuAccess.setMnemonic(KeyEvent.VK_A);
    
    //Add Accessability Options
    jmenuitemDys = new JCheckBoxMenuItem("Dyslexia");
    jmenuitemDys.setFont(f12);
    jmenuAccess.add(jmenuitemDys);
    
    jmenuitemCol = new JCheckBoxMenuItem("Colorblind");
    jmenuitemCol.setFont(f12);
    jmenuAccess.add(jmenuitemCol);
    
    jmenuitemEye = new JCheckBoxMenuItem("Eye Saver");
    jmenuitemEye.setFont(f12);
    jmenuAccess.add(jmenuitemEye);
    
    //Add Separator
    jmenuAccess.addSeparator();
    
    //Add Menu Toggle Option
    jmenuitemPopout = new JMenuItem("Popout Options");
    jmenuitemPopout.setFont(f12);
    jmenuAccess.add(jmenuitemPopout);
    
    JMenuBar mb = new JMenuBar();
    mb.add(jmenuFile);
    mb.add(jmenuHelp);
    mb.add(jmenuAccess);
    setJMenuBar(mb);
    
    //Set frame layout manager
    
    setBackground(Color.gray);
    
    jplMaster = new JPanel();
    
    jlbOutput = new JLabel("0");
    jlbOutput.setHorizontalTextPosition(JLabel.RIGHT);
    jlbOutput.setBackground(Color.WHITE);
    jlbOutput.setOpaque(true);
    
    // Add components to frame
    getContentPane().add(jlbOutput, BorderLayout.NORTH);
    
    jbnButtons = new JButton[23];

//  GridLayout(int rows, int cols, int hgap, int vgap) 
    
    
    // Create numeric Jbuttons
    for (int i=0; i<=9; i++)
    {
      // set each Jbutton label to the value of index
      jbnButtons[i] = new JButton(String.valueOf(i));
   jbnButtons[i].setName(Integer.toString(i));
    }
    
    // Create operator Jbuttons
    jbnButtons[10] = new JButton("+/-");
    jbnButtons[11] = new JButton(".");
    jbnButtons[12] = new JButton("=");
    jbnButtons[13] = new JButton("/");
    jbnButtons[14] = new JButton("*");
    jbnButtons[15] = new JButton("-");
    jbnButtons[16] = new JButton("+");
    jbnButtons[17] = new JButton("sqrt");
    jbnButtons[18] = new JButton("1/x");
    jbnButtons[19] = new JButton("%");
 
 jbnButtons[10].setName("+/-");
 jbnButtons[11].setName(".");
 jbnButtons[12].setName("=");
 jbnButtons[13].setName("/");
 jbnButtons[14].setName("*");
 jbnButtons[15].setName("-");
 jbnButtons[16].setName("+");
    jbnButtons[17].setName("sqrt");
    jbnButtons[18].setName("1/x");
    jbnButtons[19].setName("%");
    
    jplBackSpace = new JPanel();
    jplBackSpace.setLayout(new GridLayout(1, 1, 2, 2));
    
    jbnButtons[20] = new JButton("Backspace");
 jbnButtons[20].setName("Backspace");
    jplBackSpace.add(jbnButtons[20]);
    
    jplControl = new JPanel();
    jplControl.setLayout(new GridLayout(1, 2, 2 ,2));
    
    jbnButtons[21] = new JButton("CE");
    jbnButtons[22] = new JButton("C");
 
 jbnButtons[21].setName("C");
 jbnButtons[22].setName("CE");
    
    jplControl.add(jbnButtons[21]);
    jplControl.add(jbnButtons[22]);
    
//  Setting all Numbered JButton's to Blue. The rest to Red
    for (int i=0; i<jbnButtons.length; i++) {
      jbnButtons[i].setFont(f12);
      
      if (i<10)
        jbnButtons[i].setForeground(Color.blue);
      
      else
        jbnButtons[i].setForeground(Color.red);
    }
    
    // Set panel layout manager for a 4 by 5 grid
    jplButtons.setLayout(new GridLayout(4, 5, 2, 2));
    
    //Add buttons to keypad panel starting at top left
    // First row
    for(int i=7; i<=9; i++)  {
      jplButtons.add(jbnButtons[i]);
    }
    
    // add button / and sqrt
    jplButtons.add(jbnButtons[13]);
    jplButtons.add(jbnButtons[17]);
    
    // Second row
    for(int i=4; i<=6; i++)
    {
      jplButtons.add(jbnButtons[i]);
    }
    
    // add button * and x^2
    jplButtons.add(jbnButtons[14]);
    jplButtons.add(jbnButtons[18]);
    
    // Third row
    for( int i=1; i<=3; i++)
    {
      jplButtons.add(jbnButtons[i]);
    }
    
    //adds button - and %
    jplButtons.add(jbnButtons[15]);
    jplButtons.add(jbnButtons[19]);
    
    //Fourth Row
    // add 0, +/-, ., +, and =
    jplButtons.add(jbnButtons[0]);
    jplButtons.add(jbnButtons[10]);
    jplButtons.add(jbnButtons[11]);
    jplButtons.add(jbnButtons[16]);
    jplButtons.add(jbnButtons[12]);
    
    jplMaster.setLayout(new BorderLayout());
    jplMaster.add(jplBackSpace, BorderLayout.WEST);
    jplMaster.add(jplControl, BorderLayout.EAST);
    jplMaster.add(jplButtons, BorderLayout.SOUTH);
    
   
    // Add components to frame
    getContentPane().add(jplMaster, BorderLayout.SOUTH);
    requestFocus();
    
    //activate ActionListener
    for (int i=0; i<jbnButtons.length; i++){
      jbnButtons[i].addActionListener(this);
    }
    jmenuitemAbout.addActionListener(this);
    jmenuitemExit.addActionListener(this);
    jmenuitemCol.addActionListener(this);
    jmenuitemDys.addActionListener(this);
    jmenuitemEye.addActionListener(this);
    jmenuitemPopout.addActionListener(this);
    
    clearAll();
    for (int i=0; i<jbnButtons.length; i++){
        jbnButtons[i].addKeyListener(this);
      }
    
    
    //add WindowListener for closing frame and ending program
    addWindowListener(new WindowAdapter() {
      
       
     
      public void windowClosed(WindowEvent e)
      {
        System.exit(0);
      }
    }
    );
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  } //End of Contructor Calculator
  
  
  
  
  
  
  
  
  ////////////////////////////////////////////
  public void keyReleased(KeyEvent k){
   char c = k.getKeyChar();
   if (c == '0')
    jbnButtons[0].doClick();
   else if (c == '1')
    jbnButtons[1].doClick();
   else if (c == '2')
    jbnButtons[2].doClick();
   else if (c == '3')
    jbnButtons[3].doClick();
   else if (c == '4')
    jbnButtons[4].doClick();
   else if (c == '5')
    jbnButtons[5].doClick();
   else if (c == '6')
    jbnButtons[6].doClick();
   else if (c == '7')
    jbnButtons[7].doClick();
   else if (c == '8')
    jbnButtons[8].doClick();
   else if (c == '9')
    jbnButtons[9].doClick();
   
   else if (c == '.')
    jbnButtons[11].doClick();
   else if (c == '=')
    jbnButtons[12].doClick();
   else if (c == '/')
    jbnButtons[13].doClick();
   else if (c == '*')
    jbnButtons[14].doClick();
   else if (c == '-')
    jbnButtons[15].doClick();
   else if (c == '+')
    jbnButtons[16].doClick();
   else if (c == '%')
    jbnButtons[19].doClick();
   else if (c == '\b')
    jbnButtons[20].doClick();
   else if (k.getKeyCode() == KeyEvent.VK_ENTER)
    jbnButtons[12].doClick();
   
  }
///////////////////////////////////////////// 

  // Perform action
  public void actionPerformed(ActionEvent e){
    double result = 0;
    if(e.getSource() == jmenuitemAbout){
      JDialog dlgAbout = new CustomABOUTDialog(this, "About Java Swing Calculator", true);
      dlgAbout.setVisible(true);
    }else if(e.getSource() == jmenuitemExit){
      System.exit(0);
    } 
    
    //Toggle option menu
    if(e.getSource() == jmenuitemPopout){
      hideBool = !hideBool;
      if(hideBool){
        optPan = new OptionPanel(this, "Option Panel", true);
        optPan.setVisible(true);
      }else{
        optPan.setVisible(false);
      }
    }
    
//Disability Options
    //COLORBLIND OPTION
    if(e.getSource() == jmenuitemCol){
      col = !col;
      if(col){
        if(eye){
          jmenuitemEye.doClick();
        }
        for(int i=0;i<jbnButtons.length;i++){
          if (i<10){
            jbnButtons[i].setForeground(Color.white);
            jbnButtons[i].setBackground(Color.black);
          }else{
            jbnButtons[i].setForeground(Color.black);
            jbnButtons[i].setBackground(Color.white);
          }
        }
      }else{
        for(int i=0;i<jbnButtons.length;i++){
          jbnButtons[i].setBackground(new JButton().getBackground());
          if (i<10){
            jbnButtons[i].setForeground(Color.blue);
          }else{
            jbnButtons[i].setForeground(Color.red);
          }
        }
      }
    }
    //DYSLEXIA OPTION
    if(e.getSource() == jmenuitemDys){
      dys = !dys;
      if(dys){
        jlbOutput.setFont(importFont());
        for(int i=0;i<jbnButtons.length;i++){
          jbnButtons[i].setFont(importFont());
        }
      }else{
        jlbOutput.setFont(new JLabel().getFont());
        for(int i=0;i<jbnButtons.length;i++){
          jbnButtons[i].setFont(f12);
        }
      }
    }
    //EYE SAVER
    if(e.getSource() == jmenuitemEye){
      eye = !eye;
      if(eye){
        if(col){
          jmenuitemCol.doClick();
        }
        // Set colors
        for (int i=0;i<jbnButtons.length; i++){
          jbnButtons[i].setForeground(Color.white);
          jbnButtons[i].setBackground(lowTemp);
        }
        
        jplButtons.setBackground(lowTemp);
        jplMaster.setBackground(lowTemp);
        jlbOutput.setBackground(lowTemp);
        jplControl.setBackground(lowTemp);
        getContentPane().setBackground(lowTemp);
      }else{
        for(int i=0;i<jbnButtons.length;i++){
          jbnButtons[i].setBackground(new JButton().getBackground());
          if (i<10){
            jbnButtons[i].setForeground(Color.blue);
          }else{
            jbnButtons[i].setForeground(Color.red);
          }
        }
        
        jplButtons.setBackground(new JPanel().getBackground());
        jplMaster.setBackground(new JPanel().getBackground());
        jlbOutput.setBackground(Color.white);
        jplControl.setBackground(new JPanel().getBackground());
        getContentPane().setBackground(new JPanel().getBackground());
        setBackground(Color.gray);
      }
    }
    
    
    // Search for the button pressed until end of array or key found
    for (int i=0; i<jbnButtons.length; i++)
    {  
      if(e.getSource() == jbnButtons[i])
      {
        switch(i)
        {
          case 0:
            addDigitToDisplay(i);
            break;
            
          case 1:
            addDigitToDisplay(i);
            break;
            
          case 2:
            addDigitToDisplay(i);
            break;
            
          case 3:
            addDigitToDisplay(i);
            break;
            
          case 4:
            addDigitToDisplay(i);
            break;
            
          case 5:
            addDigitToDisplay(i);
            break;
            
          case 6:
            addDigitToDisplay(i);
            break;
            
          case 7:
            addDigitToDisplay(i);
            break;
            
          case 8:
            addDigitToDisplay(i);
            break;
            
          case 9:
            addDigitToDisplay(i);
            break;
            
          case 10: // +/-
            processSignChange();
            break;
            
          case 11: // decimal point
            addDecimalPoint();
            break;
            
          case 12: // =
            processEquals();
            break;
            
          case 13: // divide
            processOperator("/");
            break;
            
          case 14: // *
            processOperator("*");
            break;
            
          case 15: // -
            processOperator("-");
            break;
            
          case 16: // +
            processOperator("+");
            break;
            
          case 17: // sqrt
            if (displayMode != ERROR_MODE)
          {
            try
            {
              if (getDisplayString().indexOf("-") == 0)
                displayError("Invalid input for function!");
              
              result = Math.sqrt(getNumberInDisplay());
              displayResult(result);
            }
            
            catch(Exception ex)
            {
              displayError("Invalid input for function!");
              displayMode = ERROR_MODE;
            }
          }
            break;
            
          case 18: // 1/x
            if (displayMode != ERROR_MODE){
            try
            {
              if (getNumberInDisplay() == 0)
                displayError("Cannot divide by zero!");
              
              result = 1 / getNumberInDisplay();
              displayResult(result);
            }
            
            catch(Exception ex) {
              displayError("Cannot divide by zero!");
              displayMode = ERROR_MODE;
            }
          }
            break;
            
          case 19: // %
            if (displayMode != ERROR_MODE){
            try {
              result = getNumberInDisplay() / 100;
              displayResult(result);
            }
            
            catch(Exception ex) {
              displayError("Invalid input for function!");
              displayMode = ERROR_MODE;
            }
          }
            break;
            
          case 20: // backspace
            if (displayMode != ERROR_MODE){
            setDisplayString(getDisplayString().substring(0,
                                                          getDisplayString().length() - 1));
            
            if (getDisplayString().length() < 1)
              setDisplayString("0");
          }
            break;
            
          case 21: // CE
            clearExisting();
            break;
            
          case 22: // C
            clearAll();
            break;
        }
      }
    }
  }
  
  void setDisplayString(String s){
    jlbOutput.setText(s);
  }
  
  String getDisplayString (){
    return jlbOutput.getText();
  }
  
  void addDigitToDisplay(int digit){
    if (clearOnNextDigit)
      setDisplayString("");
    
    String inputString = getDisplayString();
    
    if (inputString.indexOf("0") == 0){
      inputString = inputString.substring(1);
    }
    
    if ((!inputString.equals("0") || digit > 0)  && inputString.length() < MAX_INPUT_LENGTH){
      setDisplayString(inputString + digit);
    }
    
    
    displayMode = INPUT_MODE;
    clearOnNextDigit = false;
  }
  
  void addDecimalPoint(){
    displayMode = INPUT_MODE;
    
    if (clearOnNextDigit)
      setDisplayString("");
    
    String inputString = getDisplayString();
    
    // If the input string already contains a decimal point, don't
    //  do anything to it.
    if (inputString.indexOf(".") < 0)
      setDisplayString(new String(inputString + "."));
  }
  
  void processSignChange(){
    if (displayMode == INPUT_MODE)
    {
      String input = getDisplayString();
      
      if (input.length() > 0 && !input.equals("0"))
      {
        if (input.indexOf("-") == 0)
          setDisplayString(input.substring(1));
        
        else
          setDisplayString("-" + input);
      }
      
    }
    
    else if (displayMode == RESULT_MODE)
    {
      double numberInDisplay = getNumberInDisplay();
      
      if (numberInDisplay != 0)
        displayResult(-numberInDisplay);
    }
  }
  
  void clearAll() {
    setDisplayString("0");
    lastOperator = "0";
    lastNumber = 0;
    displayMode = INPUT_MODE;
    clearOnNextDigit = true;
  }
  
  void clearExisting(){
    setDisplayString("0");
    clearOnNextDigit = true;
    displayMode = INPUT_MODE;
  }
  
  double getNumberInDisplay() {
    String input = jlbOutput.getText();
    return Double.parseDouble(input);
  }
  
  void processOperator(String op) {
    if (displayMode != ERROR_MODE)
    {
      double numberInDisplay = getNumberInDisplay();
      
      if (!lastOperator.equals("0")) 
      {
        try
        {
          double result = processLastOperator();
          displayResult(result);
          lastNumber = result;
        }
        
        catch (DivideByZeroException e)
        {
        }
      }
      
      else
      {
        lastNumber = numberInDisplay;
      }
      
      clearOnNextDigit = true;
      lastOperator = op;
    }
  }
  
  void processEquals(){
    double result = 0;
    
    if (displayMode != ERROR_MODE){
      try   
      {
        result = processLastOperator();
        displayResult(result);
      }
      
      catch (DivideByZeroException e) {
        displayError("Cannot divide by zero!");
      }
      
      lastOperator = "0";
    }
  }
  
  double processLastOperator() throws DivideByZeroException {
    double result = 0;
    double numberInDisplay = getNumberInDisplay();
    
    if (lastOperator.equals("/"))
    {
      if (numberInDisplay == 0)
        throw (new DivideByZeroException());
      
      result = lastNumber / numberInDisplay;
    }
    
    if (lastOperator.equals("*"))
      result = lastNumber * numberInDisplay;
    
    if (lastOperator.equals("-"))
      result = lastNumber - numberInDisplay;
    
    if (lastOperator.equals("+"))
      result = lastNumber + numberInDisplay;
    
    return result;
  }
  
  void displayResult(double result){
    setDisplayString(Double.toString(result));
    lastNumber = result;
    displayMode = RESULT_MODE;
    clearOnNextDigit = true;
  }
  
  void displayError(String errorMessage){
    setDisplayString(errorMessage);
    lastNumber = 0;
    displayMode = ERROR_MODE;
    clearOnNextDigit = true;
  }
  
  public static void main(String args[]) {
    Calculator calci = new Calculator();
    Container contentPane = calci.getContentPane();
//  contentPane.setLayout(new BorderLayout());
    calci.setTitle("Java Swing Calculator");
    calci.setSize(241, 217);
    calci.pack();
    calci.setLocation(400, 250);
    calci.setVisible(true);
    calci.setResizable(false);
    jmenuitemPopout.doClick();
  }
  
  Font importFont(){
    try {
      //Returned font is of pt size 1
      Font font = Font.createFont(Font.TRUETYPE_FONT, new File("OpenDyslexicAlta-Regular.ttf"));
      
      //Derive and return a 12 pt version:
      //Need to use float otherwise
      //it would be interpreted as style
      
      return font.deriveFont(12f);
      
    } catch (Exception e) {
      return null;
    }
  }



@Override
public void keyTyped(KeyEvent e) {
 // TODO Auto-generated method stub
 
}

@Override
public void keyPressed(KeyEvent e) {
 // TODO Auto-generated method stub
 
}
  
}  //End of Swing Calculator Class.

class DivideByZeroException extends Exception{
  public DivideByZeroException()
  {
    super();
  }
  
  public DivideByZeroException(String s)
  {
    super(s);
  }
}

class OptionPanel extends JDialog implements ActionListener{
  JButton optDys,optCol,optEye,optHide;
  Font ft = new Font("Times New Roman", 1, 30);
  OptionPanel(JFrame parent, String title, boolean tog){
    super(parent, title, tog);
    setBackground(Color.black);
    
    JPanel panelTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
    StringBuffer optTitle = new StringBuffer();
    optTitle.append("Accessability Option Menu");
    JTextArea titleArea = new JTextArea(1, 10);
    titleArea.setText(optTitle.toString());
    titleArea.setFont(new Font("Times New Roman", 1, 20));
    titleArea.setEditable(false);
    panelTitle.add(titleArea);
    getContentPane().add(panelTitle, BorderLayout.NORTH);
    
    JPanel options = new JPanel(new FlowLayout(FlowLayout.CENTER));
    optDys = new JButton("Dyslexic");
    optDys.setFont(new Font("Times New Roman", 1, 30));
    optDys.addActionListener(this);
    optCol = new JButton("Colorblind");
    optCol.setFont(new Font("Times New Roman", 1, 30));
    optCol.addActionListener(this);
    optEye = new JButton("Eye-Saver");
    optEye.setFont(new Font("Times New Roman", 1, 30));
    optEye.addActionListener(this);
    options.add(optDys);
    options.add(optCol);
    options.add(optEye);
    getContentPane().add(options, BorderLayout.CENTER);
    
    JPanel hide = new JPanel(new FlowLayout(FlowLayout.CENTER));
    optHide = new JButton("Hide Menu");
    hide.add(optHide);
    optHide.addActionListener(this);
    getContentPane().add(hide, BorderLayout.SOUTH);
    
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
        Window optionWindow = e.getWindow();
        Calculator.jmenuitemPopout.doClick();
      }
    }
    );
    
    pack();
  }
  
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == optDys){
      Calculator.jmenuitemDys.doClick();
    }else if(e.getSource() == optCol){
      Calculator.jmenuitemCol.doClick();
    }else if(e.getSource() == optEye){
      Calculator.jmenuitemEye.doClick();
    }else if(e.getSource() == optHide){
      Calculator.jmenuitemPopout.doClick();
      //this.dispose();
    }
  }
}


class CustomABOUTDialog extends JDialog implements ActionListener {
  JButton jbnOk;
  
  CustomABOUTDialog(JFrame parent, String title, boolean modal){
    super(parent, title, modal);
    setBackground(Color.black);
    
    JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
    StringBuffer text = new StringBuffer();
    text.append("Disability Calculator\n\n");
    text.append("Frame Developer: Hemanth\n");
    text.append("Feature Developers: Kendall Ayers, William Horan, Montapon Sawettanan, David Van Harlingen\n");
    text.append("Version: 1.0");
    
    JTextArea jtAreaAbout = new JTextArea(5, 50);
    jtAreaAbout.setText(text.toString());
    jtAreaAbout.setFont(new Font("Times New Roman", 1, 13));
    jtAreaAbout.setEditable(false);
    
    p1.add(jtAreaAbout);
    p1.setBackground(Color.red);
    getContentPane().add(p1, BorderLayout.CENTER);
    
    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    jbnOk = new JButton(" OK ");
    jbnOk.addActionListener(this);
    
    p2.add(jbnOk);
    getContentPane().add(p2, BorderLayout.SOUTH);
    
    setLocation(408, 270);
    setResizable(false);
    
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e)
      {
        Window aboutDialog = e.getWindow();
        aboutDialog.dispose();
      }
    }
    );
    
    pack();
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == jbnOk) {
      this.dispose();
    }
  }
	
}
