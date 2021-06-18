package commands;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import geometry.*;
import mvc.DrawingModel;

public class CmdToFrontTests {
	private CmdToFront cmdToFront;
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
		cmdToFront = new CmdToFront(0, model);
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdToFront.execute();
		assertEquals(circle, model.get(0));
		assertEquals(point, model.get(1));
	}

	@Test
	public void testUnexecuteExpectedTrue() {
		cmdToFront.execute();
		cmdToFront.unexecute();
		assertEquals(circle, model.get(1));
		assertEquals(point, model.get(0));
	}
	
}
