package mvc;

import static org.junit.Assert.*;
import org.junit.*;
import geometry.*;

public class DrawingModelTests {
	private DrawingModel model;
	private Point point;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 1);
	}
	
	@Test
	public void testAddShapeExpectedEqual() {
		model.add(point);
		assertEquals(1, model.getShapes().size());
		assertEquals(point, model.get(0));
	}
	
	@Test
	public void testAtIndexExpectedEqual() {
		model.add(point);
		model.addAtIndex(0, new Circle(point , 10));
		assertEquals(2, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Circle);
	}
	
	@Test
	public void testGetExpectedEqual() {
		model.add(point);
		model.add(new Circle(point , 10));
		assertEquals(2, model.getShapes().size());
		assertTrue(model.get(1) instanceof Circle);
	}
	
	@Test
	public void testRemoveShapeExpectedEqual() {
		model.add(point);
		model.remove(point);
		assertEquals(0, model.getShapes().size());
	}
	
}
