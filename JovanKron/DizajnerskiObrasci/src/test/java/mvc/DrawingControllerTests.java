package mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

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
		robot.delay(500);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Point);
		assertEquals(400, ((Point) model.getShapes().get(0)).getX());
		assertEquals(299, ((Point) model.getShapes().get(0)).getY());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
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
		robot.delay(500);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Line);
		assertEquals(400, ((Line) model.getShapes().get(0)).getStartPoint().getX());
		assertEquals(299, ((Line) model.getShapes().get(0)).getStartPoint().getY());
		assertEquals(500, ((Line) model.getShapes().get(0)).getEndPoint().getX());
		assertEquals(399, ((Line) model.getShapes().get(0)).getEndPoint().getY());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
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
		robot.delay(500);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertEquals(400, ((Rectangle) model.getShapes().get(0)).getUpperLeftPoint().getX());
		assertEquals(299, ((Rectangle) model.getShapes().get(0)).getUpperLeftPoint().getY());
		assertEquals(10, ((Rectangle) model.getShapes().get(0)).getHeight());
		assertEquals(30, ((Rectangle) model.getShapes().get(0)).getWidth());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.YELLOW, ((Rectangle) model.getShapes().get(0)).getFillColor());
	}
	
	@Test
	public void testDrawCircle() {
		frame.getTglbtnCircle().setSelected(true);
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
		robot.keyPress(KeyEvent.VK_3);  
		robot.keyRelease(KeyEvent.VK_3);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(500);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Circle);
		assertEquals(400, ((Circle) model.getShapes().get(0)).getCenter().getX());
		assertEquals(299, ((Circle) model.getShapes().get(0)).getCenter().getY());
		assertEquals(30, ((Circle) model.getShapes().get(0)).getRadius());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.YELLOW, ((Circle) model.getShapes().get(0)).getFillColor());
	}
	
	@Test
	public void testDrawDonut() {
		frame.getTglbtnDonut().setSelected(true);
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
		robot.keyPress(KeyEvent.VK_2);  
		robot.keyRelease(KeyEvent.VK_2);
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
		robot.delay(500);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Donut);
		assertEquals(400, ((Donut) model.getShapes().get(0)).getCenter().getX());
		assertEquals(299, ((Donut) model.getShapes().get(0)).getCenter().getY());
		assertEquals(20, ((Donut) model.getShapes().get(0)).getInnerRadius());
		assertEquals(30, ((Donut) model.getShapes().get(0)).getRadius());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.YELLOW, ((Donut) model.getShapes().get(0)).getFillColor());
	}
	
	@Test
	public void testDrawHexagon() {
		frame.getTglbtnHexagon().setSelected(true);
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
		robot.keyPress(KeyEvent.VK_3);  
		robot.keyRelease(KeyEvent.VK_3);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(500);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof HexagonAdapter);
		assertEquals(400, ((HexagonAdapter) model.getShapes().get(0)).getX());
		assertEquals(299, ((HexagonAdapter) model.getShapes().get(0)).getY());
		assertEquals(30, ((HexagonAdapter) model.getShapes().get(0)).getR());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.YELLOW, ((HexagonAdapter) model.getShapes().get(0)).getFillColor());
	}
	
	@Test
	public void testSelect() {
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(100);
		frame.getTglbtnSelect().setSelected(true);
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(500);
		assertEquals(1, model.getSelectedShapes().size());
		assertTrue(model.getSelectedShapes().get(0) instanceof Point);
		assertEquals(400, ((Point) model.getSelectedShapes().get(0)).getX());
		assertEquals(299, ((Point) model.getSelectedShapes().get(0)).getY());
		assertEquals(Color.BLACK, model.getSelectedShapes().get(0).getEdgeColor());
	}
	
	@Test
	public void testDeselect() {
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(100);
		frame.getTglbtnSelect().setSelected(true);
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(500);
		assertEquals(0, model.getSelectedShapes().size());
	}
	
	@Test
	public void testSelectMoreShapes() {
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(100);
		robot.mouseMove(500,500);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(100);
		frame.getTglbtnSelect().setSelected(true);
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.mouseMove(500,500);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(500);
		assertEquals(2, model.getSelectedShapes().size());
	}
	
	@Test
	public void testDeselectAllShapes() {
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(100);
		robot.mouseMove(500,500);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(100);
		frame.getTglbtnSelect().setSelected(true);
		robot.mouseMove(400,400);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.mouseMove(500,500);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.mouseMove(600,600);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(500);
		assertEquals(0, model.getSelectedShapes().size());
	}
}
