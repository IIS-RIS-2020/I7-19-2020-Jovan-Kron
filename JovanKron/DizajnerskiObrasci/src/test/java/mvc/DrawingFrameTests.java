package mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		verify(controller).toFront();
	}
	
	@Test
	public void testToBack() {
		frame.getTglBtnToBack().setEnabled(true);
		frame.getTglBtnToBack().doClick();
		verify(controller).toBack();
	}
	
	@Test
	public void testBringToFront() {
		frame.getTglBtnBringToFront().setEnabled(true);
		frame.getTglBtnBringToFront().doClick();
		verify(controller).bringToFront();
	}
	
	@Test
	public void testBringToBack() {
		frame.getTglBtnBringToBack().setEnabled(true);
		frame.getTglBtnBringToBack().doClick();
		verify(controller).bringToBack();
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
	public void testNew() {
		frame.getTglBtnNew().doClick();
		verify(controller).newPainting();
	}
	
	@Test
	public void testSave() {
		frame.getTglBtnSave().doClick();
		verify(controller).save();
	}
	
	@Test
	public void testLoad() {
		frame.getTglBtnLoad().doClick();
		verify(controller).load();
	}
	
	@Test
	public void testSelectTglBtnClicked() {
		frame.getTglBtnSelect().doClick();
		verify(controller).unselectAll();
		assertEquals(frame.getTglBtnSelect(), frame.getClickedButton());
	}
	
	@Test
	public void testPointTglBtnClicked() {
		frame.getTglBtnPoint().doClick();
		verify(controller).unselectAll();
		assertEquals(frame.getTglBtnPoint(), frame.getClickedButton());
	}
	
	@Test
	public void testLineTglBtnClicked() {
		frame.getTglBtnLine().doClick();
		verify(controller).unselectAll();
		verify(controller).setFlagForLine(true);
		assertEquals(frame.getTglBtnLine(), frame.getClickedButton());
	}
	
	@Test
	public void testCircleTglBtnClicked() {
		frame.getTglBtnCircle().doClick();
		verify(controller).unselectAll();
		assertEquals(frame.getTglBtnCircle(), frame.getClickedButton());
	}
	
	@Test
	public void testRectangleTglBtnClicked() {
		frame.getTglBtnRectangle().doClick();
		verify(controller).unselectAll();
		assertEquals(frame.getTglBtnRectangle(), frame.getClickedButton());
	}
	
	@Test
	public void testDonutTglBtnClicked() {
		frame.getTglBtnDonut().doClick();
		verify(controller).unselectAll();
		assertEquals(frame.getTglBtnDonut(), frame.getClickedButton());
	}
	
	@Test
	public void testHexagonTglBtnClicked() {
		frame.getTglBtnHexagon().doClick();
		verify(controller).unselectAll();
		assertEquals(frame.getTglBtnHexagon(), frame.getClickedButton());
	}
	
}
