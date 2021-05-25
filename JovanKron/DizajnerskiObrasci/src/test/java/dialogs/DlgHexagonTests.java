package dialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DlgHexagonTests {

	private DlgHexagon dialog;
	
	@Before
	public void setUp() {
		dialog = new DlgHexagon();
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
		dialog.setTxtRadius("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutRadiusExpectedFalse() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutXExpectedFalse() {
		dialog.setTxtRadius("545");
		dialog.setTxtY("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutYExpectedFalse() {
		dialog.setTxtRadius("545");
		dialog.setTxtX("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.setTxtRadius("100");
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isOk());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}*/
	
}
