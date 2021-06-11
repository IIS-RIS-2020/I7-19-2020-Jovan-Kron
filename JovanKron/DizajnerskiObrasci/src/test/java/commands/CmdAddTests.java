package commands;

import static org.junit.Assert.*;
import org.junit.*;
import geometry.*;
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
		cmdAdd = new CmdAdd(circle, model);
		cmdAdd.execute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.getShapes().contains(circle));
	}

	@Test
	public void testUnexecuteSurfaceShapeExpectedTrue() {
		cmdAdd = new CmdAdd(circle, model);
		cmdAdd.execute();
		cmdAdd.unexecute();
		assertEquals(0, model.getShapes().size());
		assertFalse(model.getShapes().contains(circle));
	}

}
