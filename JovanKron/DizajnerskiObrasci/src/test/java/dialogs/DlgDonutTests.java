package dialogs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DlgDonutTests {

private DlgDonut dialog;
	
	@Before
	public void setUp() {
		dialog = new DlgDonut();
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
	public void testSaveWithOnlySetInnerExpectedFalse() {
		dialog.setTxtInner("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithOnlySetOuterExpectedFalse() {
		dialog.setTxtOuter("545");
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
		dialog.setTxtInner("545");
		dialog.setTxtOuter("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutXExpectedFalse() {
		dialog.setTxtY("545");
		dialog.setTxtInner("545");
		dialog.setTxtOuter("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutYExpectedFalse() {
		dialog.setTxtX("545");
		dialog.setTxtInner("545");
		dialog.setTxtOuter("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutInnerExpectedFalse() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.setTxtOuter("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveWithoutOuterExpectedFalse() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.setTxtInner("545");
		dialog.getSaveButton().doClick();
		assertFalse(dialog.isOk());
	}
	
	@Test
	public void testSaveExpectedTrue() {
		dialog.setTxtX("545");
		dialog.setTxtY("545");
		dialog.setTxtInner("100");
		dialog.setTxtOuter("20");
		dialog.getSaveButton().doClick();
		assertTrue(dialog.isOk());
	}
	
	@Test
	public void testCancelExpectedFalse() {
		dialog.getCancelButton().doClick();
		assertFalse(dialog.isActive());
	}*/
	
}
