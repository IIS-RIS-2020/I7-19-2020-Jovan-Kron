package dialogs;

import static org.junit.Assert.*;
import org.junit.*;

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
