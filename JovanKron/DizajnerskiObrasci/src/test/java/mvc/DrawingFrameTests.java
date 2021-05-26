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
	public void testDelete() {
		frame.getTglBtnDelete().setEnabled(true);
		frame.getTglBtnDelete().doClick();
		verify(controller).deleteShapes();
	}
	
	@Test
	public void testModify() {
		frame.getTglBtnModify().setEnabled(true);
		frame.getTglBtnModify().doClick();
		verify(controller).modifyShape();
	}
	
	@Test
	public void testChooseEdgeColor() {
		frame.getTglBtnEdgeColor().doClick();
		verify(controller).chooseEdgeColor();
	}
	
	@Test
	public void testChooseFillColor() {
		frame.getTglBtnFillColor().doClick();
		verify(controller).chooseFillColor();
	}
	
	@Test
	public void testToFront() {
		frame.getTglBtnToFront().setEnabled(true);
		frame.getTglBtnToFront().doClick();
		verify(controller).positionCommand("TF");
	}
	
	@Test
	public void testToBack() {
		frame.getTglBtnToBack().setEnabled(true);
		frame.getTglBtnToBack().doClick();
		verify(controller).positionCommand("TB");
	}
	
	@Test
	public void testBringToFront() {
		frame.getTglBtnBringToFront().setEnabled(true);
		frame.getTglBtnBringToFront().doClick();
		verify(controller).positionCommand("BTF");
	}
	
	@Test
	public void testBringToBack() {
		frame.getTglBtnBringToBack().setEnabled(true);
		frame.getTglBtnBringToBack().doClick();
		verify(controller).positionCommand("BTB");
	}
	
	@Test
	public void testUndo() {
		frame.getTglBtnUndo().setEnabled(true);
		frame.getTglBtnUndo().doClick();
		verify(controller).undo();
	}
	
	@Test
	public void testRedo() {
		frame.getTglBtnRedo().setEnabled(true);
		frame.getTglBtnRedo().doClick();
		verify(controller).redo();
	}
	/*
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
	}*/
}
