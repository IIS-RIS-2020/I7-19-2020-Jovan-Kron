package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Before;
import org.junit.Test;

import geometry.Point;
import mvc.DrawingModel;

public class CmdSelectTests {

	private CmdSelect cmdSelect;
	private DrawingModel model;
	private Point point; 

	@Before
	public void setUp() {
		cmdSelect = new CmdSelect(point = new Point(1, 1), model = new DrawingModel());
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdSelect.execute();
		assertEquals(1, model.getSelectedShapes().size());
		assertTrue(model.getSelectedShapes().contains(point));
	}

	@Test
	public void testUnexecuteExpectedTrue() {
		cmdSelect.execute();
		cmdSelect.unexecute();
		assertEquals(0, model.getSelectedShapes().size());
		assertFalse(model.getSelectedShapes().contains(point));
	}
}
