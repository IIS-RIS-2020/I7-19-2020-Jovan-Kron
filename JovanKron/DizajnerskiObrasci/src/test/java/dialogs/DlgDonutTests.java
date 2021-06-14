package dialogs;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.*;

import geometry.Donut;
import geometry.Point;

public class DlgDonutTests {
	private DlgDonut dialog;
	
	@Before
	public void setUp() {
		dialog = new DlgDonut();
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
	public void testSaveWithOnlySetInnerExpectedFalse() {
		dialog.getTxtInner().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOnlySetOuterExpectedFalse() {
		dialog.getTxtOuter().setText("545");
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
		dialog.getTxtInner().setText("545");
		dialog.getTxtOuter().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutXExpectedFalse() {
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("545");
		dialog.getTxtOuter().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutYExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtInner().setText("545");
		dialog.getTxtOuter().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutInnerExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtOuter().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithoutOuterExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveInnerRadiusGreaterThenOuterExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("100");
		dialog.getTxtOuter().setText("20");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveInnerRadiusEqualToOuterExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("20");
		dialog.getTxtOuter().setText("20");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithCenterXLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("545");
		dialog.getTxtOuter().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithCenterYLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtInner().setText("545");
		dialog.getTxtOuter().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithCenterLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtInner().setText("545");
		dialog.getTxtOuter().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithInnerLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("-545");
		dialog.getTxtOuter().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithOuterLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("545");
		dialog.getTxtOuter().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithInnerAndOuterLessThanZeroExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("-545");
		dialog.getTxtOuter().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustCenterXPostiveExpectedFalse() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtInner().setText("-545");
		dialog.getTxtOuter().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustCenterYPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("-545");
		dialog.getTxtOuter().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustInnerPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtInner().setText("545");
		dialog.getTxtOuter().setText("-545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveWithJustOuterPostiveExpectedFalse() {
		dialog.getTxtX().setText("-545");
		dialog.getTxtY().setText("-545");
		dialog.getTxtInner().setText("-545");
		dialog.getTxtOuter().setText("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isConfirmed());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.getTxtX().setText("545");
		dialog.getTxtY().setText("545");
		dialog.getTxtInner().setText("20");
		dialog.getTxtOuter().setText("40");
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
	    assertTrue(dialog.getTxtInner().isEditable());
	    assertTrue(dialog.getTxtOuter().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	    assertEquals(Color.BLUE, dialog.getFillColor());
	    assertEquals(Color.BLUE, dialog.getBtnFillColor().getBackground());
	}
	
	@Test
	public void testFillForModifyExpectedTrue() {
	    dialog.fillForModify(new Donut(new Point(1, 1), 20, 10, Color.RED, Color.BLUE));
	    assertEquals("1", dialog.getTxtX().getText());
	    assertEquals("1", dialog.getTxtY().getText());
	    assertEquals("10", dialog.getTxtInner().getText());
	    assertEquals("20", dialog.getTxtOuter().getText());
	    assertTrue(dialog.getTxtX().isEditable());
	    assertTrue(dialog.getTxtY().isEditable());
	    assertTrue(dialog.getTxtInner().isEditable());
	    assertTrue(dialog.getTxtOuter().isEditable());
	    assertEquals(Color.RED, dialog.getEdgeColor());
	    assertEquals(Color.RED, dialog.getBtnEdgeColor().getBackground());
	    assertEquals(Color.BLUE, dialog.getFillColor());
	    assertEquals(Color.BLUE, dialog.getBtnFillColor().getBackground());
	}
	
	@Test
	public void testCreateDonutFromInputExpectedEqual() {
	    dialog.fillForModify(new Donut(new Point(1, 1), 20, 10, Color.RED, Color.BLUE));
	    dialog.getSaveButton().doClick();
	    Donut d = dialog.createDonutFromInput();
		assertEquals(1, d.getCenter().getX());
		assertEquals(1, d.getCenter().getY());
		assertEquals(20, d.getRadius());
		assertEquals(10, d.getInnerRadius());
		assertEquals(Color.RED, d.getEdgeColor());
		assertEquals(Color.BLUE, d.getFillColor());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}
	
}
