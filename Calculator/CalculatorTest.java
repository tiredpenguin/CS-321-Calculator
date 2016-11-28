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

public class CalculatorTest {
	Calculator calculator = new Calculator();
	String output;
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
	
	@Before public void executedBeforeEach() {
		clear.doClick();
		output = "";
	}
	
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
	public void evaluateSubtraction2() {
		zero.doClick();
		four.doClick();
		minus.doClick();
		seven.doClick();
		equals.doClick();
		output = calculator.getDisplayString();
		assertEquals("-3.0", output);
	}
}