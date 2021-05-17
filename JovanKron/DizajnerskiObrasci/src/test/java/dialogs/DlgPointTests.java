package dialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.AWTException;

import org.junit.Before;
import org.junit.Test;


public class DlgPointTests {

	private DlgPoint dialog;
	
	@Before
	public void setUp() throws AWTException {
		dialog = new DlgPoint();
	}
	
	@Test
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
	public void testSaveExpectedTrue() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isOk());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}

}
