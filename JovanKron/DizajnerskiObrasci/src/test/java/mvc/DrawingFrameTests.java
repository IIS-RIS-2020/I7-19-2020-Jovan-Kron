package mvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void testNew() throws Exception {
		frame.getTglBtnNew().doClick();
		verify(controller).newPainting();
	}
	
	@Test
	public void testSave() throws Exception {
		frame.getTglBtnSave().doClick();
		verify(controller).save();
	}
	
	@Test
	public void testLoad() throws Exception {
		frame.getTglBtnLoad().doClick();
		verify(controller).load();
	}
}
