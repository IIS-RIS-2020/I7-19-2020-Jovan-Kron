package commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import geometry.Point;
import mvc.DrawingModel;

public class CmdRedoTests {
	
	private CmdRedo cmdRedo;
	private DrawingModel model;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		model.getRedoStack().add(new CmdAdd(new Point(1, 1), model));
		model.getListModel().add(0, "test");
		cmdRedo = new CmdRedo(model);
	}
	
	@Test
	public void testExecuteExpectedTrue() throws Exception {
		cmdRedo.execute();
		assertEquals(1, model.getUndoStack().size());
		assertEquals(0, model.getRedoStack().size());
	}

	@Test
	public void testUnexecuteExpectedTrue() throws Exception {
		cmdRedo.execute();
		cmdRedo.unexecute();
		assertEquals(0, model.getUndoStack().size());
		assertEquals(1, model.getRedoStack().size());
	}
}
