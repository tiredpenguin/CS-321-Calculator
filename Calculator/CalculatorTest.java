import org.junit.*;
import static org.junit.Assert.*;
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
import javax.swing.JSeparator;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class CalculatorTest{
  
  //Test instantiates a Calculator and uses the same object for every test
  Calculator calculator = new Calculator();
  String output;
  
  //Arithmetic Buttons
  JButton one = (JButton)TestUtils.getChildNamed(calculator, "1");
  JButton two = (JButton)TestUtils.getChildNamed(calculator, "2");
  JButton three = (JButton)TestUtils.getChildNamed(calculator, "3");
  JButton four = (JButton)TestUtils.getChildNamed(calculator, "4");
  JButton five = (JButton)TestUtils.getChildNamed(calculator, "5");
  JButton six = (JButton)TestUtils.getChildNamed(calculator, "6");
  JButton seven = (JButton)TestUtils.getChildNamed(calculator, "7");
  JButton eight = (JButton)TestUtils.getChildNamed(calculator, "8");
  JButton nine = (JButton)TestUtils.getChildNamed(calculator, "9");
  JButton zero = (JButton)TestUtils.getChildNamed(calculator, "0");
  JButton sign = (JButton)TestUtils.getChildNamed(calculator, "+/-");
  JButton decimal = (JButton)TestUtils.getChildNamed(calculator, ".");
  JButton divide = (JButton)TestUtils.getChildNamed(calculator, "/");
  JButton multiply = (JButton)TestUtils.getChildNamed(calculator, "*");
  JButton minus = (JButton)TestUtils.getChildNamed(calculator, "-");
  JButton sqrt = (JButton)TestUtils.getChildNamed(calculator, "sqrt");
  JButton plus = (JButton)TestUtils.getChildNamed(calculator, "+");
  JButton reciprocal = (JButton)TestUtils.getChildNamed(calculator, "1/x");
  JButton percent = (JButton)TestUtils.getChildNamed(calculator, "%");
  JButton equals = (JButton)TestUtils.getChildNamed(calculator, "=");
  JButton clear = (JButton)TestUtils.getChildNamed(calculator, "C");
  JButton clearentry = (JButton)TestUtils.getChildNamed(calculator, "CE");
  
  //Menu Buttons
  JMenu accessmenu = (JMenu)TestUtils.getChildNamed(calculator, "Accessibility");
  JCheckBoxMenuItem dyslexia = (JCheckBoxMenuItem)TestUtils.getChildNamed(calculator, "Dyslexia");
  JCheckBoxMenuItem colorblind = (JCheckBoxMenuItem)TestUtils.getChildNamed(calculator, "Colorblind");
  JCheckBoxMenuItem eyesaver = (JCheckBoxMenuItem)TestUtils.getChildNamed(calculator, "Eye Saver");
  
  //clear calculator inputs and resets any turned on disability options before
  //next test
  @Before public void executedBeforeEach() {
    clear.doClick();
    output = "";
    if (calculator.col == true){
      colorblind.doClick();
    }
    if (calculator.dys == true){
      dyslexia.doClick();
    }
    if (calculator.eye == true){
      eyesaver.doClick();
    }
  }
  
  //Addition Tests
  @Test
  public void evaluateAddition1() {
    one.doClick();
    plus.doClick();
    two.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("3.0", output);
  }
  @Test
  public void evaluateAddition2() {
    nine.doClick();
    nine.doClick();
    plus.doClick();
    seven.doClick();
    five.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("174.0", output);
  }
  @Test
  public void evaluateAddition3() {
    nine.doClick();
    sign.doClick();
    plus.doClick();
    seven.doClick();
    five.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("66.0", output);
  }
  @Test
  public void evaluateAddition4() {
    zero.doClick();
    plus.doClick();
    zero.doClick();
    five.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("5.0", output);
  }
  @Test
  public void evaluateAddition5() {
    zero.doClick();
    plus.doClick();
    zero.doClick();
    five.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("5.0", output);
  }
  
  //Subtraction Tests
  @Test
  public void evaluateSubtraction1() {
    seven.doClick();
    minus.doClick();
    two.doClick();
    five.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("-18.0", output);
  }
  @Test
  public void evaluateSubtraction2() {
    eight.doClick();
    four.doClick();
    minus.doClick();
    six.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("78.0", output);
  }
  @Test
  public void evaluateSubtraction3() {
    zero.doClick();
    four.doClick();
    minus.doClick();
    seven.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("-3.0", output);
  }
  
  //Multiplication Tests
  @Test
  public void evaluateMultiply1() {
    zero.doClick();
    four.doClick();
    multiply.doClick();
    seven.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("28.0", output);
  }
  @Test
  public void evaluateMultiply2() {
    zero.doClick();
    multiply.doClick();
    zero.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("0.0", output);
  }
  @Test
  public void evaluateMultiply3() {
    seven.doClick();
    sign.doClick();
    multiply.doClick();
    seven.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("-49.0", output);
  }
  
  //Division Tests
  @Test
  public void evaluateDivision1() {
    one.doClick();
    five.doClick();
    divide.doClick();
    five.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("3.0", output);
  }
  @Test
  public void evaluateDivision2() {
    two.doClick();
    divide.doClick();
    three.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("0.6666666666666666", output);
  }
  //Division Errors
  @Test
  public void ArithmeticError1() {
    zero.doClick();
    divide.doClick();
    zero.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("Cannot divide by zero!", output);
  }
  @Test
  public void ArithmeticError2() {
    zero.doClick();
    reciprocal.doClick();
    output = calculator.getDisplayString();
    assertEquals("Cannot divide by zero!", output);
  }
  
  //Square Root Tests
  @Test
  public void EvaluateSquareRoot(){
    eight.doClick();
    one.doClick();
    sqrt.doClick();
    output = calculator.getDisplayString();
    assertEquals("9.0", output);
  }
  @Test
  public void NegativeSquareRootError(){
    one.doClick();
    sign.doClick();
    sqrt.doClick();
    output = calculator.getDisplayString();
    assertEquals("Invalid input for function!", output);
  }
    
  //Sign Test
  @Test
  public void EvaluateSignedNumber(){
    three.doClick();
    plus.doClick();
    three.doClick();
    sign.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("0.0", output);
  }
  
  //Reciprocal Test
  @Test
  public void EvaluateReciprocal(){
    one.doClick();
    zero.doClick();
    reciprocal.doClick();
    output = calculator.getDisplayString();
    assertEquals("0.1", output);
  }
  
  //Decimal Point Test
  @Test
  public void EvaluateDecimalPoint(){
    zero.doClick();
    decimal.doClick();
    five.doClick();
    one.doClick();
    plus.doClick();
    zero.doClick();
    decimal.doClick();
    seven.doClick();
    five.doClick();
    equals.doClick();
    output = calculator.getDisplayString();
    assertEquals("1.26", output);
  }
  
  //Percent Test
  @Test
  public void EvaluatePercent(){
    one.doClick();
    five.doClick();
    seven.doClick();
    decimal.doClick();
    eight.doClick();
    nine.doClick();
    percent.doClick();
    output = calculator.getDisplayString();
    assertEquals("1.5789", output);
  }
    
    
  //Accesibility Options
  @Test
  public void DyslexicOption() {
    output = calculator.getCurrentFont();
    assertEquals("Times New Roman", output);
    dyslexia.doClick();
    output = calculator.getCurrentFont();
    assertEquals(true, dyslexia.getState());
    assertEquals("OpenDyslexicAlta", output);
  }
  @Test
  public void ColorblindOption() {
    colorblind.doClick();
    output = calculator.getButtonColor();
    assertEquals(true, colorblind.getState());
    assertEquals("java.awt.Color[r=0,g=0,b=0]", output);
  }
  @Test
  public void EyesaverOption() {
    eyesaver.doClick();
    output = calculator.getButtonColor();
    assertEquals(true, eyesaver.getState());
    assertEquals("java.awt.Color[r=226,g=153,b=0]", output);
  }
  @Test
  public void DyslexicAndColorblindOption() {
    dyslexia.doClick();
    colorblind.doClick();
    output = calculator.getButtonColor();
    String output2 = calculator.getCurrentFont();
    assertEquals(true, dyslexia.getState());
    assertEquals(true, colorblind.getState());
    assertEquals("java.awt.Color[r=0,g=0,b=0]", output);
    assertEquals("OpenDyslexicAlta", output2);
  }
  @Test
  public void DyslexicAndEyesaverOption() {
    dyslexia.doClick();
    eyesaver.doClick();
    output = calculator.getButtonColor();
    String output2 = calculator.getCurrentFont();
    assertEquals(true, dyslexia.getState());
    assertEquals(true, eyesaver.getState());
    assertEquals("java.awt.Color[r=226,g=153,b=0]", output);
    assertEquals("OpenDyslexicAlta", output2);
  }
  @Test
  public void EyesaverOverwriteOption() {
    colorblind.doClick();
    output = calculator.getButtonColor();
    assertEquals("java.awt.Color[r=0,g=0,b=0]", output);
    eyesaver.doClick();
    output = calculator.getButtonColor();
    assertEquals(true, eyesaver.getState());
    assertEquals(false, colorblind.getState());
    assertEquals("java.awt.Color[r=226,g=153,b=0]", output);
  }
  @Test
  public void ColorblindOverwriteOption() {
    eyesaver.doClick();
    output = calculator.getButtonColor();
    assertEquals("java.awt.Color[r=226,g=153,b=0]", output);
    colorblind.doClick();
    output = calculator.getButtonColor();
    assertEquals(false, eyesaver.getState());
    assertEquals(true, colorblind.getState());
    assertEquals("java.awt.Color[r=0,g=0,b=0]", output);
  }
  
  //Keyboard Implementation Tests
  @Test
  public void KeyboardAddition(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_2, '2'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PLUS,'+'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_1, '1'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
    output = calculator.getDisplayString();
    assertEquals("3.0", output);
  }
  @Test
  public void KeyboardSubtraction(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_7, '7'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_MINUS,'-'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_2, '2'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_5, '5'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
    output = calculator.getDisplayString();
    assertEquals("-18.0", output);
  }
  @Test
  public void KeyboardMultiplication(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_4, '4'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_MULTIPLY,'*'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_7, '7'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
    output = calculator.getDisplayString();
    assertEquals("28.0", output);
  }
  @Test
  public void KeyboardDivision(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_1, '1'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_5, '5'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DIVIDE,'/'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_5, '5'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
    output = calculator.getDisplayString();
    assertEquals("3.0", output);
  }
  @Test
  public void KeyboardPercent(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_2, '2'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_9, '9'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_8, '8'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, '%'));
    output = calculator.getDisplayString();
    assertEquals("2.98", output);
  }
  @Test
  public void KeyboardBackspace(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_1, '1'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_0, '0'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_0, '0'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PLUS, '+'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_1, '1'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_0, '0'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_0, '0'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_BACK_SPACE, '\b'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
    output = calculator.getDisplayString();
    assertEquals("110.0", output);
  }
  @Test
  public void KeyboardClear(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_1, '1'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_C, 'c'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PLUS, '+'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_2, '2'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
    output = calculator.getDisplayString();
    assertEquals("2.0", output);
  }
  @Test
  public void KeyboardClearEntry(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_1, '1'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PLUS, '+'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_1, '1'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_X, 'x'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_2, '2'));
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
    output = calculator.getDisplayString();
    assertEquals("3.0", output);
  }
  
  //Keyboard Accessibility
  @Test
  public void KeyboardDyslexic(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Q, 'q'));
    output = calculator.getCurrentFont();
    assertEquals(true, dyslexia.getState());
    assertEquals("OpenDyslexicAlta", output);
  }
  @Test
  public void KeyboardColorblind(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'w'));
    output = calculator.getButtonColor();
    assertEquals(true, colorblind.getState());
    assertEquals("java.awt.Color[r=0,g=0,b=0]", output);
  }
  @Test
  public void KeyboardEyesaver(){
    calculator.keyPressed(new KeyEvent(calculator,KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_E, 'e'));
    output = calculator.getButtonColor();
    assertEquals(true, eyesaver.getState());
    assertEquals("java.awt.Color[r=226,g=153,b=0]", output);
  }
}
