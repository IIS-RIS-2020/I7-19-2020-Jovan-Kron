package commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import geometry.Point;
import mvc.DrawingModel;

public class CmdRemoveTests {

	private CmdRemove cmdRemove;
	private DrawingModel model;
	private Point p;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		model.getSelectedShapes().add(p = new Point(1, 1));
		model.add(p);
		cmdRemove = new CmdRemove(model.getSelectedShapes(), model);
	}
	
	@Test
	public void testExecuteExpectedTrue() throws Exception {
		cmdRemove.execute();
		assertEquals(0, model.getSelectedShapes().size());
	}

	@Test
	public void testUnexecuteExpectedTrue() throws Exception {
		cmdRemove.execute();
		cmdRemove.unexecute();
		assertEquals(1, model.getSelectedShapes().size());
	}
}
