package dialogs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import org.junit.*;
import geometry.Point;

public class DlgPointTests {
	private DlgPoint dialog;
	private OptionPane optionPane;
	
	@Before
	public void setUp() {
		dialog = new DlgPoint();
		optionPane = mock(OptionPane.class);
		dialog.setOptionPane(optionPane);
	}
	
	@Test
	public void testIsNullExpectedNotEqual() {
		assertNotEquals(null, dialog);
	}
	
	@Test
	public void testSaveExpectedFalse() {
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}

	@Test
	public void testSaveWithOnlySetXExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithOnlySetYExpectedFalse() {
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithXLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-5");
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithYLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-5");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithBothCoordLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-5");
		dialog.getTxtY().setText("-5");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testCheckInputTextWithLetterExpectedConsumedTrue() {
	    KeyEvent e = new KeyEvent(new Button("click"), 1, 20, 1, 10, 'a');
		dialog.checkInputText(e);
		assertTrue(e.isConsumed());
	}
	
	@Test
	public void testCheckInputTextExpectedConsumedFalse() {
	    KeyEvent e = new KeyEvent(new Button("click"), 1, 20, 1, 10, '7');
		dialog.checkInputText(e);
		assertFalse(e.isConsumed());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isConfirmed());
	}
	
	@Test
	public void testFillForAddExpectedTrue() {
	    dialog.fillForAdd(new Point(1, 1), Color.RED);
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertFalse(dialog.getTxtX().isEditable());
	    assertFalse(dialog.getTxtY().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	}
	
	@Test
	public void testFillForModifyExpectedTrue() {
	    dialog.fillForModify(new Point(1, 1), Color.RED);
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertTrue(dialog.getTxtX().isEditable());
	    assertTrue(dialog.getTxtY().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	}
	
	@Test
	public void testCreateBasePointFromInputExpectedEqual() {
	    dialog.fillForModify(new Point(1, 1), Color.RED);
	    dialog.getSaveButton().doClick();
	    Point p = dialog.createBasePointFromInput();
	    assertEquals(1, p.getX());
		assertEquals(1, p.getY());
		assertEquals(Color.RED, p.getEdgeColor());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}

	private void verifyEmptyInputInfoMessage() {
		verify(optionPane).showMessageDialog(null, "Fault in entering values for numbers");
	}
	
	private void verifyNegativeInputInfoMessage() {
		verify(optionPane).showMessageDialog(null, "Entered values must be greater than 0!");
	}
	
}
