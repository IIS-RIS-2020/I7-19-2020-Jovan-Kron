package mvc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commands.CmdAdd;
import geometry.Point;

public class DrawingModelTests {

	private DrawingModel model;
	Point p;
	CmdAdd cmdAdd;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		p = new Point(1, 1);
		cmdAdd = new CmdAdd(p, model);
	}
	
	@Test
	public void testAddShapeExpectedEqual() {
		model.add(p);
		assertEquals(1, model.getShapes().size());
		assertEquals(p, model.get(0));
	}
	
	@Test
	public void testRemoveShapeExpectedEqual() {
		model.add(p);
		model.remove(p);
		assertEquals(0, model.getShapes().size());
	}
	
	@Test
	public void testPushToUndoStackExpectedEqual() {
		model.pushInUndoStack(cmdAdd);
		assertEquals(1, model.getUndoStack().size());
		assertEquals(cmdAdd, model.getUndoStack().peek());
	}
	
	@Test
	public void testAddSelectedShapeExpectedEqual() {
		model.addSelected(p);
		assertEquals(1, model.getSelectedShapes().size());
		assertEquals(p, model.getSelectedShapes().get(0));
	}
	
	@Test
	public void testRemoveSelectedShapeExpectedEqual() {
		model.addSelected(p);
		model.removeSelected(p);
		assertEquals(0, model.getSelectedShapes().size());
	}
}
