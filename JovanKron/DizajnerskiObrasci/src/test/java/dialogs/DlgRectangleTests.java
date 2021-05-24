package dialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DlgRectangleTests {

	private DlgRectangle dialog;
	
	@Before
	public void setUp() {
		dialog = new DlgRectangle();
	}
	
	/*@Test
	public void testIsNullExpectedNotEqual() {
		assertNotEquals(null, dialog);
	}
	
	@Test
	public void testSaveExpectedFalse() {
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithOnlySetXExpectedFalse() {
		dialog.setTxtX("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithOnlySetYExpectedFalse() {
		dialog.setTxtY("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithOnlySetWidthExpectedFalse() {
		dialog.setTxtWidth("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithOnlySetHeightExpectedFalse() {
		dialog.setTxtHeight("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithCoordinatesExpectedFalse() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithDimensonsExpectedFalse() {
		dialog.setTxtWidth("545");
		dialog.setTxtHeight("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutXExpectedFalse() {
		dialog.setTxtY("545");
		dialog.setTxtWidth("545");
		dialog.setTxtHeight("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutYExpectedFalse() {
		dialog.setTxtX("545");
		dialog.setTxtWidth("545");
		dialog.setTxtHeight("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutWidthExpectedFalse() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.setTxtHeight("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutHeightExpectedFalse() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.setTxtWidth("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.setTxtWidth("100");
		dialog.setTxtHeight("20");
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isOk());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}*/
}
