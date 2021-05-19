package mvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import org.junit.Before;
import org.junit.Test;

import commands.CmdAdd;
import geometry.Point;

public class DrawingFrameTests {
	
	private DrawingFrame frame;
	private DrawingController controller;

	@Before
	public void setUp() {
		controller = mock(DrawingController.class);
		frame = new DrawingFrame();
		frame.setController(controller);
	}
	
	@Test
	public void testDelete() throws Exception {
		frame.getTglbtnDelete().setEnabled(true);
		frame.getTglbtnDelete().doClick();
		verify(controller).onDelete();
	}
	
	@Test
	public void testEdgeColor() throws Exception {
		frame.getTglbtnEdgeColor().doClick();
		verify(controller).setEdgeColor();
	}
	
	@Test
	public void testFillColor() throws Exception {
		frame.getTglbtnFillColor().doClick();
		verify(controller).setFillColor();
	}
	
	@Test
	public void testModify() throws Exception {
		frame.getTglbtnModify().setEnabled(true);
		frame.getTglbtnModify().doClick();
		verify(controller).onModify();
	}
	
	@Test
	public void testToFront() throws Exception {
		frame.getTglbtnToFront().setEnabled(true);
		frame.getTglbtnToFront().doClick();
		verify(controller).onToFront();
	}
	
	@Test
	public void testToBack() throws Exception {
		frame.getTglbtnToBack().setEnabled(true);
		frame.getTglbtnToBack().doClick();
		verify(controller).onToBack();
	}
	
	@Test
	public void testBringToFront() throws Exception {
		frame.getTglbtnBringToFront().setEnabled(true);
		frame.getTglbtnBringToFront().doClick();
		verify(controller).onBringToFront();
	}
	
	@Test
	public void testBringToBack() throws Exception {
		frame.getTglbtnBringToBack().setEnabled(true);
		frame.getTglbtnBringToBack().doClick();
		verify(controller).onBringToBack();
	}
	
	@Test
	public void testUndo() throws Exception {
		frame.getTglbtnUndo().setEnabled(true);
		frame.getTglbtnUndo().doClick();
		verify(controller).onUndo();
	}
	
	@Test
	public void testRedo() throws Exception {
		frame.getTglbtnRedo().setEnabled(true);
		frame.getTglbtnRedo().doClick();
		verify(controller).onRedo();
	}
	
	@Test
	public void testSaveShapes() throws Exception {
		frame.getView().getModel().getUndoStack().push(new CmdAdd(new Point(), frame.getView().getModel()));
		frame.getTglbtnSaveShapes().doClick();
		verify(controller).onSaveShapes();
	}
	
	@Test
	public void testSaveShapesWitnEmptyUndoStack() throws Exception {
		frame.getTglbtnSaveShapes().doClick();
		//never() is used to check if method was not called
		verify(controller, never()).onSaveShapes();
	}
	
	@Test
	public void testOpenShapes() throws Exception {
		frame.getTglbtnOpenShapes().doClick();
		verify(controller).onOpenShapes();
	}
	
	@Test
	public void testSaveCommands() throws Exception {
		frame.getView().getModel().getUndoStack().push(new CmdAdd(new Point(), frame.getView().getModel()));
		frame.getTglbtnSaveCommands().doClick();
		verify(controller).onSaveCommands();
	}
	
	@Test
	public void testSaveCommandsWitnEmptyUndoStack() throws Exception {
		frame.getTglbtnSaveCommands().doClick();
		//never() is used to check if method was not called
		verify(controller, never()).onSaveCommands();
	}
	
	@Test
	public void testOpenCommands() throws Exception {
		frame.getTglbtnOpenCommands().doClick();
		verify(controller).onOpenCommands();
	}
	
	@Test
	public void testNext() throws Exception {
		frame.getTglbtnNext().doClick();
		verify(controller).next();
	}
}
