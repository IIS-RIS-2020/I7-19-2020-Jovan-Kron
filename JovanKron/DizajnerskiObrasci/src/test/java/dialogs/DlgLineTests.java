package dialogs;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.*;

public class DlgLineTests {
	private DlgLine dialog;
	
	@Before
	public void setUp() {
		dialog = new DlgLine();
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
	public void testSaveWithOnlyStartXExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlyStartYExpectedFalse() {
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlyEndXExpectedFalse() {
		dialog.getTxtEndX().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlyEndYExpectedFalse() {
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithStartExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithEndExpectedFalse() {
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutStartXExpectedFalse() {
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutStartYExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutEndXExpectedFalse() {
		dialog.getTxtX().setText("545");	
		dialog.getTxtY().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutEndYExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithStartXLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithStartYLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithStartLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithEndXLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithEndYLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithEndLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustStartXPostiveExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustStartYPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustEndXPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustEndYPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isConfirmed());
	}
	
	@Test
	public void testFillForAddExpectedTrue() {
	    dialog.fillForAdd(1, 1, 5, 5, Color.RED);
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertEquals("5", dialog.getTxtEndX().getText());
	    assertEquals("5", dialog.getTxtEndY().getText());
	    assertFalse(dialog.getTxtX().isEditable());
	    assertFalse(dialog.getTxtY().isEditable());
	    assertFalse(dialog.getTxtEndX().isEditable());
	    assertFalse(dialog.getTxtEndY().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	}
	
	@Test
	public void testFillForModifyExpectedTrue() {
	    dialog.fillForModify(1, 1, 5, 5, Color.RED);
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertEquals("5", dialog.getTxtEndX().getText());
	    assertEquals("5", dialog.getTxtEndY().getText());
	    assertTrue(dialog.getTxtX().isEditable());
	    assertTrue(dialog.getTxtY().isEditable());
	    assertTrue(dialog.getTxtEndX().isEditable());
	    assertTrue(dialog.getTxtEndY().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}
	
}
