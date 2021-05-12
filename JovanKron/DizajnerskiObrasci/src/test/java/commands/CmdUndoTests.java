package commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import geometry.Point;
import mvc.DrawingModel;

public class CmdUndoTests {

	private CmdUndo cmdUndo;
	private DrawingModel model;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		model.getUndoStack().add(new CmdAdd(new Point(1, 1), model));
		cmdUndo = new CmdUndo(model);
	}
	
	@Test
	public void testExecuteExpectedTrue() throws Exception {
		cmdUndo.execute();
		assertEquals(0, model.getUndoStack().size());
		assertEquals(1, model.getRedoStack().size());
	}

	@Test
	public void testUnexecuteExpectedTrue() throws Exception {
		cmdUndo.execute();
		cmdUndo.unexecute();
		assertEquals(1, model.getUndoStack().size());
		assertEquals(0, model.getRedoStack().size());
	}
}
