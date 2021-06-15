package dialogs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.Color;
import org.junit.*;
import geometry.*;

public class DlgLineTests {
	private DlgLine dialog;
	private OptionPane optionPane;
	
	@Before
	public void setUp() {
		dialog = new DlgLine();
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
	public void testSaveWithOnlyStartXExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithOnlyStartYExpectedFalse() {
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithOnlyEndXExpectedFalse() {
		dialog.getTxtEndX().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithOnlyEndYExpectedFalse() {
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithStartExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithEndExpectedFalse() {
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithoutStartXExpectedFalse() {
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithoutStartYExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithoutEndXExpectedFalse() {
		dialog.getTxtX().setText("545");	
		dialog.getTxtY().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithoutEndYExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyEmptyInputInfoMessage();
	}
	
	@Test
	public void testSaveWithStartXLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithStartYLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithStartLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithEndXLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithEndYLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithEndLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithJustStartXPostiveExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithJustStartYPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithJustEndXPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("545");
		dialog.getTxtEndY().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
	}
	
	@Test
	public void testSaveWithJustEndYPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtEndX().setText("-545");
		dialog.getTxtEndY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
		verifyNegativeInputInfoMessage();
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
	    dialog.fillForAdd(new Point(1, 1), new Point(5, 5), Color.RED);
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
	    dialog.fillForModify(new Line(new Point(1, 1), new Point(5, 5), Color.RED));
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
	public void testCreateLineFromInputExpectedEqual() {
	    dialog.fillForModify(new Line(new Point(1, 1), new Point(5, 5), Color.RED));
	    dialog.getSaveButton().doClick();
	    Line l = dialog.createLineFromInput();
	    assertEquals(1, l.getStartPoint().getX());
		assertEquals(1, l.getStartPoint().getY());
		assertEquals(5, l.getEndPoint().getX());
		assertEquals(5, l.getEndPoint().getY());
		assertEquals(Color.RED, l.getEdgeColor());
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
		verify(optionPane).showMessageDialog(null, "Entered values must be greater than 0");
	}
	
}
