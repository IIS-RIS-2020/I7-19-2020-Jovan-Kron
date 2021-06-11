package dialogs;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.*;

public class DlgRectangleTests {
	private DlgRectangle dialog;
	
	@Before
	public void setUp() {
		dialog = new DlgRectangle();
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
		dialog.getTxtX().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlySetYExpectedFalse() {
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlySetWidthExpectedFalse() {
		dialog.getTxtWidth().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlySetHeightExpectedFalse() {
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithCoordinatesExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithDimensonsExpectedFalse() {
		dialog.getTxtWidth().setText("545");
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutXExpectedFalse() {
		dialog.getTxtY().setText("545");
		dialog.getTxtWidth().setText("545");
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutYExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtWidth().setText("545");
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutWidthExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutHeightExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtWidth().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithUpperLeftPointXLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtWidth().setText("545");
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithUpperLeftPointYLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtWidth().setText("545");
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithUpperLeftPointLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtWidth().setText("545");
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithWidthLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtWidth().setText("-545");
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithHeightLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtWidth().setText("545");
		dialog.getTxtHeight().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithWidthAndHeightLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtWidth().setText("-545");
		dialog.getTxtHeight().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustUpperLeftPointXPostiveExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtWidth().setText("-545");
		dialog.getTxtHeight().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustUpperLeftPointYPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtWidth().setText("-545");
		dialog.getTxtHeight().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustWidthPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtWidth().setText("545");
		dialog.getTxtHeight().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustHeightPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtWidth().setText("-545");
		dialog.getTxtHeight().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtWidth().setText("20");
		dialog.getTxtHeight().setText("40");
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isConfirmed());
	}
	
	@Test
	public void testFillForAddExpectedTrue() {
	    dialog.fillForAdd(1, 1, Color.RED, Color.BLUE);
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertFalse(dialog.getTxtX().isEditable());
	    assertFalse(dialog.getTxtY().isEditable());
	    assertTrue(dialog.getTxtWidth().isEditable());
	    assertTrue(dialog.getTxtHeight().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	    assertEquals(Color.BLUE, dialog.getFillColor());
	    assertEquals(Color.BLUE, dialog.getBtnFillColor().getBackground());
	}
	
	@Test
	public void testFillForModifyExpectedTrue() {
	    dialog.fillForModify(1, 1, 10, 20, Color.RED, Color.BLUE);
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertEquals("10", dialog.getTxtWidth().getText());
	    assertEquals("20", dialog.getTxtHeight().getText());
	    assertTrue(dialog.getTxtX().isEditable());
	    assertTrue(dialog.getTxtY().isEditable());
	    assertTrue(dialog.getTxtWidth().isEditable());
	    assertTrue(dialog.getTxtHeight().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	    assertEquals(Color.BLUE, dialog.getFillColor());
	    assertEquals(Color.BLUE, dialog.getBtnFillColor().getBackground());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}
	
}
