package mvc;

import static org.junit.Assert.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.JFrame;
import org.junit.*;
import geometry.*;
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
        controller = new DrawingController(model, frame);
        frame.setController(controller);
        frame.setTitle("Dejan Tosenberger IT21/2017");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getTglBtnPoint().doClick();
        frame.setVisible(true);
        frame.setResizable(false);
	}
	
	@After
	public void tearDown() {
		frame.dispose();
	}
	
	@Test
	public void testNewPainting() {
		drawCircle();
		pressSpace(2);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testSaveSerializableFile() {
		drawCircle();
		pressTab(1);
		pressSpace(1);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_A);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_A);
		pressTab(1);
		pressSpace(1);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_UP);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_UP);
		robot.delay(1000);
		pressSpace(1);
		robot.delay(1000);
		pressEnter(2);
		robot.delay(2000);
		assertTrue(new File("src/main/resources/a.ser").delete());
	}
	
	@Test
	public void tesLoadSerializableFile() {
		pressTab(2);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
		pressSpace(1);
		pressTab(9);
		pressSpace(1);
		pressEnter(2);
		robot.delay(2000);
		assertEquals(6, model.getShapes().size());
		assertTrue(model.get(0) instanceof Point);
		assertTrue(model.get(1) instanceof Line);
		assertTrue(model.get(2) instanceof Donut);
		assertTrue(model.get(3) instanceof Circle);
		assertTrue(model.get(4) instanceof Rectangle);
		assertTrue(model.get(5) instanceof HexagonAdapter);
	}
	
	@Test
	public void testSaveLogFile() {
		drawCircle();
		pressTab(1);
		pressSpace(1);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		pressEnter(2);
		robot.delay(500);
		assertTrue(new File("src/main/resources/a.log").delete());
	}
	
	@Test
	public void testLoadLogFile() {
		pressTab(2);
		pressSpace(1);
		pressTab(10);
		pressSpace(1);
		pressEnter(1);
		pressSpace(6);
		assertEquals(3, model.getShapes().size());
		assertTrue(model.get(0) instanceof Line);
		assertTrue(model.get(1) instanceof HexagonAdapter);
		assertTrue(model.get(2) instanceof Circle);
	}
	
	@Test
	public void testDrawPoint() {
		click(400, 400);
		pressEnter(1);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Point);
		assertEquals(405, ((Point) model.getShapes().get(0)).getX());
		assertEquals(350, ((Point) model.getShapes().get(0)).getY());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
	}
	
	@Test
	public void testDrawLine() {
		frame.getTglBtnLine().doClick();
		click(400, 400);
		click(500, 500);
		pressEnter(1);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Line);
		assertEquals(405, ((Line) model.getShapes().get(0)).getStartPoint().getX());
		assertEquals(350, ((Line) model.getShapes().get(0)).getStartPoint().getY());
		assertEquals(505, ((Line) model.getShapes().get(0)).getEndPoint().getX());
		assertEquals(450, ((Line) model.getShapes().get(0)).getEndPoint().getY());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
	}
	
	@Test
	public void testDrawRectangle() {
		drawRectangle();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertEquals(405, ((Rectangle) model.getShapes().get(0)).getUpperLeftPoint().getX());
		assertEquals(350, ((Rectangle) model.getShapes().get(0)).getUpperLeftPoint().getY());
		assertEquals(30, ((Rectangle) model.getShapes().get(0)).getHeight());
		assertEquals(60, ((Rectangle) model.getShapes().get(0)).getWidth());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.YELLOW, ((Rectangle) model.getShapes().get(0)).getFillColor());
	}
	
	@Test
	public void testDrawCircle() {
		drawCircle();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Circle);
		assertEquals(405, ((Circle) model.getShapes().get(0)).getCenter().getX());
		assertEquals(350, ((Circle) model.getShapes().get(0)).getCenter().getY());
		assertEquals(30, ((Circle) model.getShapes().get(0)).getRadius());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.YELLOW, ((Circle) model.getShapes().get(0)).getFillColor());
	}

	@Test
	public void testDrawDonut() {
		drawDonut();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Donut);
		assertEquals(405, ((Donut) model.getShapes().get(0)).getCenter().getX());
		assertEquals(350, ((Donut) model.getShapes().get(0)).getCenter().getY());
		assertEquals(20, ((Donut) model.getShapes().get(0)).getInnerRadius());
		assertEquals(30, ((Donut) model.getShapes().get(0)).getRadius());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.YELLOW, ((Donut) model.getShapes().get(0)).getFillColor());
	}

	@Test
	public void testDrawHexagon() {
		drawHexagon();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof HexagonAdapter);
		assertEquals(405, ((HexagonAdapter) model.getShapes().get(0)).getX());
		assertEquals(350, ((HexagonAdapter) model.getShapes().get(0)).getY());
		assertEquals(40, ((HexagonAdapter) model.getShapes().get(0)).getR());
		assertEquals(Color.BLACK, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.YELLOW, ((HexagonAdapter) model.getShapes().get(0)).getFillColor());
	}
	
	@Test
	public void testSelect() {
		click(400, 400);
		pressEnter(1);
		frame.getTglBtnSelect().doClick();
		click(400, 400);		
		assertTrue(model.get(0).isSelected());
		assertTrue(model.get(0) instanceof Point);
		assertEquals(405, ((Point) model.get(0)).getX());
		assertEquals(350, ((Point) model.get(0)).getY());
		assertEquals(Color.BLACK, model.get(0).getEdgeColor());
	}
	
	@Test
	public void testDeselect() {
		click(400, 400);
		pressEnter(1);
		frame.getTglBtnSelect().doClick();
		click(400, 400);
		click(400, 400);
		assertFalse(model.get(0).isSelected());
		assertTrue(model.get(0) instanceof Point);
		assertEquals(405, ((Point) model.get(0)).getX());
		assertEquals(350, ((Point) model.get(0)).getY());
		assertEquals(Color.BLACK, model.get(0).getEdgeColor());
	}
	
	@Test
	public void testSelectMoreShapes() {
		click(400, 400);
		pressEnter(1);
		click(500, 500);
		pressEnter(1);
		frame.getTglBtnSelect().doClick();
		click(400, 400);
		click(500, 500);		
		assertTrue(model.get(0).isSelected());
		assertTrue(model.get(0) instanceof Point);
		assertEquals(405, ((Point) model.get(0)).getX());
		assertEquals(350, ((Point) model.get(0)).getY());
		assertEquals(Color.BLACK, model.get(0).getEdgeColor());
		assertTrue(model.get(1).isSelected());
		assertTrue(model.get(1) instanceof Point);
		assertEquals(505, ((Point) model.get(1)).getX());
		assertEquals(450, ((Point) model.get(1)).getY());
		assertEquals(Color.BLACK, model.get(1).getEdgeColor());
	}

	@Test
	public void testDeselectAllShapes() {
		click(400, 400);
		pressEnter(1);
		click(500, 500);		
		pressEnter(1);
		frame.getTglBtnSelect().doClick();
		click(400, 400);		
		click(500, 500);
		frame.getTglBtnSelect().doClick();
		assertFalse(model.get(0).isSelected());
		assertFalse(model.get(1).isSelected());
	}
	
	@Test
	public void testDeleteOneShape() {
		click(400, 400);		
		pressEnter(1);
		robot.delay(500);
		frame.getTglBtnSelect().doClick();
		robot.delay(500);
		click(400, 400);
		robot.delay(500);
		pressTab(6);
		pressSpace(2);		
		robot.delay(500);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testDeleteTwoShapes() {
		click(400, 400);
		pressEnter(1);		
		click(500, 500);
		pressEnter(1);		
		frame.getTglBtnSelect().doClick();
		click(400, 400);
		click(500, 500);				
		pressTab(5);
		pressSpace(2);				
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testModifyPoint() {
		click(400, 400);		
		pressEnter(1);
		frame.getTglBtnSelect().doClick();
		robot.delay(200);
		click(400, 400);						
		pressTab(5);
		pressSpace(1);
	    pressBackspace(1);
	    pressTab(1);
	    pressBackspace(1);
	    pressTab(1);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(2);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Point);
		assertEquals(40, ((Point) model.getShapes().get(0)).getX());
		assertEquals(35, ((Point) model.getShapes().get(0)).getY());
		assertEquals(Color.WHITE, model.getShapes().get(0).getEdgeColor());
	}
	
	@Test
	public void testModifyLine() {
		frame.getTglBtnLine().doClick();
		click(400, 400);		
		click(500, 500);
		pressEnter(1);
		frame.getTglBtnSelect().doClick();	
		click(450, 450);						
		pressTab(5);
		pressSpace(1);
	    pressBackspace(1);
	    pressTab(1);
	    pressBackspace(1);
	    pressTab(3);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(2);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Line);
		assertEquals(40, ((Line) model.getShapes().get(0)).getStartPoint().getX());
		assertEquals(35, ((Line) model.getShapes().get(0)).getStartPoint().getY());
		assertEquals(505, ((Line) model.getShapes().get(0)).getEndPoint().getX());
		assertEquals(450, ((Line) model.getShapes().get(0)).getEndPoint().getY());
		assertEquals(Color.WHITE, model.getShapes().get(0).getEdgeColor());
	}
	
	@Test
	public void testModifyRectangle() {
		drawRectangle();
		robot.delay(500);
		frame.getTglBtnSelect().doClick();		
		click(410, 410);
		robot.delay(500);
		pressTab(5);
		pressSpace(1);
	    pressBackspace(1);
	    pressTab(1);
	    pressBackspace(1);
	    pressTab(1);
		robot.keyPress(KeyEvent.VK_0);
	    robot.keyRelease(KeyEvent.VK_0);
	    pressTab(2);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(1);
	    pressTab(1);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(2);
	    robot.delay(500);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertEquals(40, ((Rectangle) model.getShapes().get(0)).getUpperLeftPoint().getX());
		assertEquals(35, ((Rectangle) model.getShapes().get(0)).getUpperLeftPoint().getY());
		assertEquals(300, ((Rectangle) model.getShapes().get(0)).getHeight());
		assertEquals(60, ((Rectangle) model.getShapes().get(0)).getWidth());
		assertEquals(Color.WHITE, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.WHITE, ((Rectangle) model.getShapes().get(0)).getFillColor());
	}
	
	@Test
	public void testModifyDonut() {
		drawDonut();
		frame.getTglBtnSelect().doClick();
		click(425, 400);						
		pressTab(5);
		pressSpace(1);
	    pressBackspace(1);
	    pressTab(1);
		pressBackspace(1);
	    pressTab(2);
		robot.keyPress(KeyEvent.VK_0);
	    robot.keyRelease(KeyEvent.VK_0);
	    pressTab(1);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(1);
	    pressTab(1);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(2);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Donut);
		assertEquals(40, ((Donut) model.getShapes().get(0)).getCenter().getX());
		assertEquals(35, ((Donut) model.getShapes().get(0)).getCenter().getY());
		assertEquals(20, ((Donut) model.getShapes().get(0)).getInnerRadius());
		assertEquals(300, ((Donut) model.getShapes().get(0)).getRadius());
		assertEquals(Color.WHITE, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.WHITE, ((Donut) model.getShapes().get(0)).getFillColor());
	}
	
	@Test
	public void testModifyCircle() {
		drawCircle();		
		frame.getTglBtnSelect().doClick();
		click(400, 400);				
		pressTab(5);
		pressSpace(1);
	    pressBackspace(1);
	    pressTab(1);
		pressBackspace(1);
	    pressTab(1);
		robot.keyPress(KeyEvent.VK_0);
	    robot.keyRelease(KeyEvent.VK_0);
	    pressTab(1);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(1);
	    pressTab(1);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(2);				
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Circle);
		assertEquals(40, ((Circle) model.getShapes().get(0)).getCenter().getX());
		assertEquals(35, ((Circle) model.getShapes().get(0)).getCenter().getY());
		assertEquals(300, ((Circle) model.getShapes().get(0)).getRadius());
		assertEquals(Color.WHITE, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.WHITE, ((Circle) model.getShapes().get(0)).getFillColor());
	}
	
	@Test
	public void testModifyHexagon() {
		drawHexagon();
		frame.getTglBtnSelect().doClick();		
		click(400, 400);				
		pressTab(5);
		pressSpace(1);
	    pressBackspace(1);
	    pressTab(1);
		pressBackspace(1);
	    pressTab(1);
		robot.keyPress(KeyEvent.VK_0);
	    robot.keyRelease(KeyEvent.VK_0);
	    pressTab(1);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(1);
	    pressTab(1);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    pressEnter(2);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof HexagonAdapter);
		assertEquals(40, ((HexagonAdapter) model.getShapes().get(0)).getX());
		assertEquals(35, ((HexagonAdapter) model.getShapes().get(0)).getY());
		assertEquals(400, ((HexagonAdapter) model.getShapes().get(0)).getR());
		assertEquals(Color.WHITE, model.getShapes().get(0).getEdgeColor());
		assertEquals(Color.WHITE, ((HexagonAdapter) model.getShapes().get(0)).getFillColor());
	}

	@Test
	public void testToFront() {
		drawRectangle();
		drawCircle();
		drawHexagon();		
		frame.getTglBtnSelect().doClick();	
		click(450, 420);				
		pressTab(9);		
		pressSpace(1);
	    assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(1) instanceof Rectangle);
		assertTrue(model.getShapes().get(0) instanceof Circle);
		assertTrue(model.getShapes().get(2) instanceof HexagonAdapter);
	}
	
	@Test
	public void testToFrontWhenShapeAlreadyOnFront() {
		drawRectangle();
		drawCircle();
		drawHexagon();		
		frame.getTglBtnSelect().doClick();	
		click(400, 400);				
		pressTab(9);		
		pressSpace(1);
		pressEnter(1);
	    assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertTrue(model.getShapes().get(1) instanceof Circle);
		assertTrue(model.getShapes().get(2) instanceof HexagonAdapter);
	}
	
	@Test
	public void testToBack() {
		drawRectangle();
		drawCircle();
		drawHexagon();			
		frame.getTglBtnSelect().doClick();		
		click(400, 400);		
		pressTab(8);
		pressSpace(1);
	    assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertTrue(model.getShapes().get(2) instanceof Circle);
		assertTrue(model.getShapes().get(1) instanceof HexagonAdapter);
	}
	
	@Test
	public void testToBackWhenShapeAlreadyOnBack() {
		drawRectangle();
		drawCircle();
		drawHexagon();			
		frame.getTglBtnSelect().doClick();		
		click(450, 420);		
		pressTab(8);
		pressSpace(1);
		pressEnter(1);
	    assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertTrue(model.getShapes().get(1) instanceof Circle);
		assertTrue(model.getShapes().get(2) instanceof HexagonAdapter);
	}
	
	@Test
	public void testBringToFront() {
		drawRectangle();
		drawCircle();
		drawHexagon();			
		frame.getTglBtnSelect().doClick();	
		click(459, 429);				
		pressTab(10);		
		pressSpace(1);
	    assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(2) instanceof Rectangle);
		assertTrue(model.getShapes().get(0) instanceof Circle);
		assertTrue(model.getShapes().get(1) instanceof HexagonAdapter);
	}
	
	@Test
	public void testBringToFrontWhenShapeAlreadyOnFront() {
		drawRectangle();
		drawCircle();
		drawHexagon();			
		frame.getTglBtnSelect().doClick();	
		click(400, 400);				
		pressTab(10);		
		pressSpace(1);
		pressEnter(1);
	    assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertTrue(model.getShapes().get(1) instanceof Circle);
		assertTrue(model.getShapes().get(2) instanceof HexagonAdapter);
	}
	
	@Test
	public void testBringToBack() {
		drawRectangle();
		drawCircle();
		drawHexagon();	
		frame.getTglBtnSelect().doClick();	
		click(400, 400);		
		pressTab(7);
		pressSpace(1);
	    assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(1) instanceof Rectangle);
		assertTrue(model.getShapes().get(2) instanceof Circle);
		assertTrue(model.getShapes().get(0) instanceof HexagonAdapter);
	}
	
	@Test
	public void testBringToBackWhenShapeAlreadyOnBack() {
		drawRectangle();
		drawCircle();
		drawHexagon();	
		frame.getTglBtnSelect().doClick();	
		click(450, 420);		
		pressTab(7);
		pressSpace(1);
		pressEnter(1);
	    assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertTrue(model.getShapes().get(1) instanceof Circle);
		assertTrue(model.getShapes().get(2) instanceof HexagonAdapter);
	}
	
	@Test
	public void testUndoOnceForAdd() {
		drawRectangle();
		drawCircle();
		drawHexagon();
		pressTab(3);
		pressSpace(1);
	    assertEquals(2, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertTrue(model.getShapes().get(1) instanceof Circle);
	}
	
	@Test
	public void testUndoAllCommands() {
		drawRectangle();
		drawCircle();
		drawHexagon();
		pressTab(3);
		pressSpace(3);
	    assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testRedoOnceForAdd() {
		drawRectangle();
		drawCircle();
		drawHexagon();	
		pressTab(3);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
	    assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertTrue(model.getShapes().get(1) instanceof Circle);
		assertTrue(model.getShapes().get(2) instanceof HexagonAdapter);
	}
	
	@Test
	public void testUndoRemoveOneShape() {
		drawRectangle();
		drawCircle();
		drawHexagon();
		frame.getTglBtnSelect().doClick();	
		click(459, 429);
		pressTab(6);
		pressSpace(2);
		robot.delay(300);
		pressTab(3);
		pressSpace(1);
		assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertTrue(model.getShapes().get(1) instanceof Circle);
		assertTrue(model.getShapes().get(2) instanceof HexagonAdapter);
	}
	
	@Test
	public void testRedoRemoveOneShape() {
		drawRectangle();
		drawCircle();
		drawHexagon();
		frame.getTglBtnSelect().doClick();	
		click(459, 429);
		pressTab(6);
		pressSpace(2);
		robot.delay(300);
		pressTab(3);
		pressSpace(1);
		robot.delay(300);
		pressTab(1);
		pressSpace(1);
		assertEquals(2, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Circle);
		assertTrue(model.getShapes().get(1) instanceof HexagonAdapter);
	}

	@Test
	public void testUndoRemoveTwoShape() {
		drawRectangle();
		drawCircle();
		drawHexagon();
		frame.getTglBtnSelect().doClick();
		click(400, 400);
		click(459, 429);
		pressTab(5);
		pressSpace(2);
		robot.delay(300);
		pressTab(3);
		pressSpace(1);
		assertEquals(3, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Rectangle);
		assertTrue(model.getShapes().get(1) instanceof Circle);
		assertTrue(model.getShapes().get(2) instanceof HexagonAdapter);
	}
	
	@Test
	public void testRedoRemoveTwoShape() {
		drawRectangle();
		drawCircle();
		drawHexagon();
		frame.getTglBtnSelect().doClick();
		click(400, 400);
		click(459, 429);
		pressTab(5);
		pressSpace(2);
		robot.delay(300);
		pressTab(3);
		pressSpace(1);
		robot.delay(300);
		pressTab(1);
		pressSpace(1);
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Circle);
	}
	
	@Test
	public void testChangeEdgeColor() {
		pressTab(10);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
		pressEnter(1);
	    assertEquals(Color.WHITE, controller.getCurrentEdgeColor());
	}
	
	@Test
	public void testChangeFillColor() {
		pressTab(11);
		pressSpace(1);
		pressTab(1);
		pressSpace(1);
		pressEnter(1);
	    assertEquals(Color.WHITE, controller.getCurrentFillColor());
	}
	
	private void drawRectangle() {
		frame.getTglBtnRectangle().doClick();
		click(400, 400);
		pressTab(2);
		robot.keyPress(KeyEvent.VK_3);  
		robot.keyRelease(KeyEvent.VK_3);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		pressTab(1);
		robot.keyPress(KeyEvent.VK_6);  
		robot.keyRelease(KeyEvent.VK_6);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		pressEnter(1);
		robot.delay(500);
	}
	
	private void drawDonut() {
		frame.getTglBtnDonut().doClick();
		click(400, 400);
		pressTab(2);
		robot.keyPress(KeyEvent.VK_2);  
		robot.keyRelease(KeyEvent.VK_2);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		pressTab(1);
		robot.keyPress(KeyEvent.VK_3);  
		robot.keyRelease(KeyEvent.VK_3);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		pressEnter(1);
		robot.delay(500);
		
	}
	
	private void drawCircle() {
		frame.getTglBtnCircle().doClick();
		click(400, 400);
		pressTab(2);
		robot.keyPress(KeyEvent.VK_3);  
		robot.keyRelease(KeyEvent.VK_3);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		pressEnter(1);
		robot.delay(500);
	}
	
	private void drawHexagon() {
		frame.getTglBtnHexagon().doClick();
		click(400, 400);
		pressTab(2);
		robot.keyPress(KeyEvent.VK_4);  
		robot.keyRelease(KeyEvent.VK_4);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_0);  
		robot.keyRelease(KeyEvent.VK_0);
		robot.delay(100);
		pressEnter(1);
		robot.delay(500);
	}
	
	private void click(int x, int y) {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); 
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(200);
	}
	
	private void pressTab(int numberOfPresses) {
		for (int i = 0; i < numberOfPresses; i++) {
			robot.keyPress(KeyEvent.VK_TAB);
		    robot.keyRelease(KeyEvent.VK_TAB);
		    robot.delay(150);
		}
	}
	
	private void pressSpace(int numberOfPresses) {
		for (int i = 0; i < numberOfPresses; i++) {
			robot.keyPress(KeyEvent.VK_SPACE);
		    robot.keyRelease(KeyEvent.VK_SPACE);
		    robot.delay(100);
		}
	}
	
	private void pressBackspace(int numberOfPresses) {
		for (int i = 0; i < numberOfPresses; i++) {
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
		    robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		    robot.delay(100);
		}
	}
	
	private void pressEnter(int numberOfPresses) {
		for (int i = 0; i < numberOfPresses; i++) {
			robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    robot.delay(300);
		}
	}
	
}
