package commands;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.junit.Test;
import geometry.*;
import mvc.DrawingModel;

public class CmdBringToBackTests {
	private CmdBringToBack cmdBringToBack;
	private DrawingModel model;
	private Point point;
	private Circle circle;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 1);
		circle = new Circle(new Point(1, 1), 10);
		model.add(point);
		model.add(circle);
		cmdBringToBack = new CmdBringToBack(1, model);
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdBringToBack.execute();
		assertEquals(circle, model.get(0));
		assertEquals(point, model.get(1));
	}

	@Test
	public void testUnexecuteExpectedTrue() {
		cmdBringToBack.execute();
		cmdBringToBack.unexecute();
		assertEquals(circle, model.get(1));
		assertEquals(point, model.get(0));
	}
	
}
