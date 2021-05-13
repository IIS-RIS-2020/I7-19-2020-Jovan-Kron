package dialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithOnlyStartXExpectedFalse() {
		dialog.setTxtStartX("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithOnlyStartYExpectedFalse() {
		dialog.setTxtStartY("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithOnlyEndXExpectedFalse() {
		dialog.setTxtEndX("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithOnlyEndYExpectedFalse() {
		dialog.setTxtEndY("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithStartExpectedFalse() {
		dialog.setTxtStartX("100");
		dialog.setTxtStartY("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithEndExpectedFalse() {
		dialog.setTxtEndX("100");
		dialog.setTxtEndY("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutStartXExpectedFalse() {
		dialog.setTxtStartY("100");
		dialog.setTxtEndX("100");
		dialog.setTxtEndY("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutStartYExpectedFalse() {
		dialog.setTxtStartX("100");
		dialog.setTxtEndX("100");
		dialog.setTxtEndY("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutEndXExpectedFalse() {
		dialog.setTxtStartX("100");	
		dialog.setTxtStartY("100");
		dialog.setTxtEndY("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutEndYExpectedFalse() {
		dialog.setTxtStartX("100");	
		dialog.setTxtStartY("100");
		dialog.setTxtEndX("100");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.setTxtStartX("100");
		dialog.setTxtStartY("100");
		dialog.setTxtEndX("200");
		dialog.setTxtEndY("200");
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isOk());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}
}
