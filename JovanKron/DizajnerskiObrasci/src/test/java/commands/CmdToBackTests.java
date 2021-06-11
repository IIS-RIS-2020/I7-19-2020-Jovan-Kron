package commands;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import geometry.*;
import mvc.DrawingModel;

public class CmdToBackTests {
	private CmdToBack cmdToBack;
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
		cmdToBack = new CmdToBack(circle, model);
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdToBack.execute();
		assertEquals(circle, model.get(0));
		assertEquals(point, model.get(1));
	}

	@Test
	public void testUnexecuteExpectedTrue() throws Exception {
		cmdToBack.execute();
		cmdToBack.unexecute();
		assertEquals(circle, model.get(1));
		assertEquals(point, model.get(0));
	}
	
}
