package commands;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import geometry.Point;
import mvc.DrawingModel;

public class CmdRemoveTests {

	private CmdRemove cmdRemove;
	private DrawingModel model;
	private Point p;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		p = new Point(1, 1);
		model.add(p);
		cmdRemove = new CmdRemove(p, model);
	}
	
	@Test
	public void testExecuteExpectedTrue() throws Exception {
		cmdRemove.execute();
		assertEquals(0, model.getShapes().size());
	}

	@Test
	public void testUnexecuteExpectedTrue() throws Exception {
		cmdRemove.execute();
		cmdRemove.unexecute();
		assertEquals(1, model.getShapes().size());
	}
	
}
