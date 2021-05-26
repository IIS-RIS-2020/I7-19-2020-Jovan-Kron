package mvc;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import commands.CmdAdd;
import geometry.Circle;
import geometry.Point;

public class DrawingModelTests {

	private DrawingModel model;
	Point p;
	CmdAdd cmdAdd;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		p = new Point(1, 1);
		cmdAdd = new CmdAdd(p, model);
	}
	
	@Test
	public void testAddShapeExpectedEqual() {
		model.add(p);
		assertEquals(1, model.getShapes().size());
		assertEquals(p, model.get(0));
	}
	
	@Test
	public void testAtIndexExpectedEqual() {
		model.add(p);
		model.addAtIndex(0, new Circle(p , 10));
		assertEquals(2, model.getShapes().size());
		assertTrue(model.getShapes().get(0) instanceof Circle);
	}
	
	@Test
	public void testGetExpectedEqual() {
		model.add(p);
		model.add(new Circle(p , 10));
		assertEquals(2, model.getShapes().size());
		assertTrue(model.get(1) instanceof Circle);
	}
	
	@Test
	public void testRemoveShapeExpectedEqual() {
		model.add(p);
		model.remove(p);
		assertEquals(0, model.getShapes().size());
	}
	
	
}
