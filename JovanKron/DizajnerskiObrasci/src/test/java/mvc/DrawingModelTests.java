package mvc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commands.CmdAdd;
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
	public void testRemoveShapeExpectedEqual() {
		model.add(p);
		model.remove(p);
		assertEquals(0, model.getShapes().size());
	}
}
