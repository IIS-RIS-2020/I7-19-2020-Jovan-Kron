package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Before;
import org.junit.Test;

import geometry.Circle;
import geometry.Point;
import mvc.DrawingModel;

public class CmdAddTests {
	
	private CmdAdd cmdAdd;
	private DrawingModel model;
	private Point point;
	private Circle circle;
	
	@Before
	public void setUp() {
		circle = new Circle(new Point(1, 1), 10);
		cmdAdd = new CmdAdd(point = new Point(1, 1), model = new DrawingModel());
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdAdd.execute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().contains(point));
	}

	@Test
	public void testUnexecuteExpectedTrue() {
		cmdAdd.execute();
		cmdAdd.unexecute();
		assertEquals(0, model.getShapes().size());
		assertFalse(model.getShapes().contains(point));
	}
	
	@Test
	public void testExecuteSurfaceShapeExpectedTrue() {
		cmdAdd.setShape(circle);
		cmdAdd.execute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().contains(circle));
	}

	@Test
	public void testUnexecuteSurfaceShapeExpectedTrue() {
		cmdAdd.setShape(circle);
		cmdAdd.execute();
		cmdAdd.unexecute();
		assertEquals(0, model.getShapes().size());
		assertFalse(model.getShapes().contains(circle));
	}

}
