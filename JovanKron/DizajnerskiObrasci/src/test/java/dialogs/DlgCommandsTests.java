package dialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DlgCommandsTests {
	
	private DlgCommands dialog;
	
	@Before
	public void setUp() {
		dialog = new DlgCommands();
	}
	
	@Test
	public void testIsNullExpectedNotEqual() {
		assertNotEquals(null, dialog);
	}

	@Test
	public void testNextExpectedTrue() {
		dialog.getBtnNext().doClick();
		assertTrue(dialog.isConfirmed());
		assertFalse(dialog.isActive());
	}
	
	@Test
	public void testExitExpectedFalse() {
		dialog.getBtnExit().doClick();
		assertFalse(dialog.isConfirmed());
		assertFalse(dialog.isActive());
	}
}
