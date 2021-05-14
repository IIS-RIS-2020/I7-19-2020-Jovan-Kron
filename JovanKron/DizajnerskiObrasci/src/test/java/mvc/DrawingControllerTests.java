package mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

public class DrawingControllerTests {

	private DrawingFrame frame;
	private DrawingModel model;
	private DrawingController controller;
	private Robot robot;

	@Before
	public void setUp() throws AWTException {
		robot = new Robot();
		model = new DrawingModel();
        frame = new DrawingFrame();
        frame.getView().setModel(model);
        frame.getList().setModel(model.getListModel());
        controller = new DrawingController(model, frame);
        frame.setController(controller);
        model.addPropertyChangeListener(frame);
        frame.setTitle("Dejan Tosenberger IT21/2017");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	@Test
	public void testDrawPoint() {
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		assertEquals(1, model.getShapes().size());
	}
	
	/*@Test
	public void testCancelDrawPoint() {
		robot.mouseMove(600,600);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_SPACE);
	    robot.keyRelease(KeyEvent.VK_SPACE);
		robot.delay(100);
		assertEquals(0, model.getShapes().size());
	}*/
	
	@Test
	public void testDrawLine() {
		frame.getTglbtnLine().setSelected(true);
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.mouseMove(500,500);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		assertEquals(1, model.getShapes().size());
	}
	
	@Test
	public void testDrawRectangle() {
		frame.getTglbtnRectangle().setSelected(true);
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_1);  
		robot.keyRelease(KeyEvent.VK_1);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_3);  
		robot.keyRelease(KeyEvent.VK_3);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		assertEquals(1, model.getShapes().size());
	}
	
}
