package commands;

import static org.junit.Assert.assertEquals;
import java.awt.Color;
import org.junit.*;
import geometry.*;
import mvc.DrawingModel;

public class CmdUpdateTests {
	private CmdUpdate cmdUpdate;
	private DrawingModel model;
	private Circle originalCircle;

	@Before
	public void setUp() {
		model = new DrawingModel();
		originalCircle = new Circle(new Point(1, 1), 10, Color.RED, Color.YELLOW);
		model.add(originalCircle);
		Circle updatedCircle = new Circle(new Point(5, 5), 50, Color.BLACK, Color.GREEN);
		cmdUpdate = new CmdUpdate(originalCircle, updatedCircle);
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdUpdate.execute();
		assertEquals(5, originalCircle.getCenter().getX());
		assertEquals(5, originalCircle.getCenter().getY());
		assertEquals(50, originalCircle.getRadius());
		assertEquals(Color.BLACK, originalCircle.getEdgeColor());
		assertEquals(Color.GREEN, originalCircle.getFillColor());
	}

	@Test
	public void testUnexecuteExpectedTrue() {
		cmdUpdate.execute();
		cmdUpdate.unexecute();
		assertEquals(1, originalCircle.getCenter().getX());
		assertEquals(1, originalCircle.getCenter().getY());
		assertEquals(10, originalCircle.getRadius());
		assertEquals(Color.RED, originalCircle.getEdgeColor());
		assertEquals(Color.YELLOW, originalCircle.getFillColor());
	}
	
}
