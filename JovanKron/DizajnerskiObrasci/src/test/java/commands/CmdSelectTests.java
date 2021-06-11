package commands;

import static org.junit.Assert.*;
import org.junit.*;
import geometry.Point;

public class CmdSelectTests {
	private CmdSelect cmdSelect;
	private Point point; 

	@Before
	public void setUp() {
		cmdSelect = new CmdSelect(point = new Point(1, 1), true);
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdSelect.execute();
		assertTrue(point.isSelected());
	}

	@Test
	public void testUnexecuteWhenSelectedExpectedTrue() {
		cmdSelect.execute();
		cmdSelect.unexecute();
		assertFalse(point.isSelected());
	}
	
	@Test
	public void testUnexecuteWhenNotSelectedExpectedTrue() {
		cmdSelect.unexecute();
		assertTrue(point.isSelected());
	}
	
}
