package dialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.AWTException;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;


public class DlgPointTests {

	private DlgPoint dialog;
	private JTextField txtInput;
	
	@Before
	public void setUp() throws AWTException {
		dialog = new DlgPoint();
		txtInput = new JTextField("545");
	}
	
	@Test
	public void testIsNullExpectedNotEqual() {
		assertNotEquals(null, dialog);
	}
	
	@Test
	public void testSaveExpectedFalse() {
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}

	@Test
	public void testSaveWithOnlySetXExpectedFalse() {
		dialog.setTxtX(txtInput);
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlySetYExpectedFalse() {
		dialog.setTxtY(txtInput);
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithXLessThanZeroExpectedFalse() {
		dialog.setTxtX(new JTextField("-5"));
		dialog.setTxtY(txtInput);
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithYLessThanZeroExpectedFalse() {
		dialog.setTxtX(txtInput);
		dialog.setTxtY(new JTextField("-5"));
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithBothCoordLessThanZeroExpectedFalse() {
		dialog.setTxtX(new JTextField("-5"));
		dialog.setTxtY(new JTextField("-5"));
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.setTxtX(txtInput);
		dialog.setTxtY(txtInput);
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isConfirmed());
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
	public void testFillForAddExpectedTrue() {
	    dialog.fillForAdd(1, 1, Color.RED);
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertFalse(dialog.getTxtX().isEditable());
	    assertFalse(dialog.getTxtY().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	}
	
	@Test
	public void testFillForModifyExpectedTrue() {
	    dialog.fillForModify(1, 1, Color.RED);
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertTrue(dialog.getTxtX().isEditable());
	    assertTrue(dialog.getTxtY().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}

}
