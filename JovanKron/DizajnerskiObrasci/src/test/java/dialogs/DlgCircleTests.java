package dialogs;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.*;
import geometry.*;

public class DlgCircleTests {
	private DlgCircle dialog;
	
	@Before
	public void setUp() {
		dialog = new DlgCircle();
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
		dialog.getTxtRadius().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutRadiusExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutXExpectedFalse() {
		dialog.getTxtRadius().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutYExpectedFalse() {
		dialog.getTxtRadius().setText("545");
		dialog.getTxtX().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithCenterXLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtRadius().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithCenterYLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtRadius().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithRadiusLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtRadius().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlRadiusPositiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtRadius().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlyCenterXPositiveExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtRadius().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlyCenterYPositiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtRadius().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtRadius().setText("545");
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isConfirmed());
	}
	
	@Test
	public void testFillForAddExpectedTrue() {
	    dialog.fillForAdd(new Point(1, 1), Color.RED, Color.BLUE);
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertFalse(dialog.getTxtX().isEditable());
	    assertFalse(dialog.getTxtY().isEditable());
	    assertTrue(dialog.getTxtRadius().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	    assertEquals(Color.BLUE, dialog.getFillColor());
	    assertEquals(Color.BLUE, dialog.getBtnFillColor().getBackground());
	}
	
	@Test
	public void testFillForModifyExpectedTrue() {
	    dialog.fillForModify(new Circle(new Point(1, 1), 5, Color.RED, Color.BLUE));
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertEquals("5", dialog.getTxtRadius().getText());
	    assertTrue(dialog.getTxtX().isEditable());
	    assertTrue(dialog.getTxtY().isEditable());
	    assertTrue(dialog.getTxtRadius().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	    assertEquals(Color.BLUE, dialog.getFillColor());
	    assertEquals(Color.BLUE, dialog.getBtnFillColor().getBackground());
	}
	
	@Test
	public void testCreateCircleFromInputExpectedEqual() {
	    dialog.fillForModify(new Circle(new Point(1, 1), 5, Color.RED, Color.BLUE));
	    dialog.getSaveButton().doClick();
	    Circle c = dialog.createCircleFromInput();
	    assertEquals(1, c.getCenter().getX());
		assertEquals(1, c.getCenter().getY());
		assertEquals(5, c.getRadius());
		assertEquals(Color.RED, c.getEdgeColor());
		assertEquals(Color.BLUE, c.getFillColor());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}
	
}
