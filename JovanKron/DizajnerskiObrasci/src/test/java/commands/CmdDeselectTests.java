package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Before;
import org.junit.Test;

import geometry.Point;
import mvc.DrawingModel;

public class CmdDeselectTests {

	private CmdDeselect cmdDeselect;
	private DrawingModel model;
	private Point point; 
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 1);
		model.addSelected(point);
		cmdDeselect = new CmdDeselect(model.getSelectedShapes(), model);
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdDeselect.execute();
		assertEquals(0, model.getSelectedShapes().size());
		assertFalse(model.getSelectedShapes().contains(point));
	}

	@Test
	public void testUnexecuteExpectedTrue() {
		cmdDeselect.execute();
		cmdDeselect.unexecute();
		assertEquals(1, model.getSelectedShapes().size());
		assertTrue(model.getSelectedShapes().contains(point));
	}
}
