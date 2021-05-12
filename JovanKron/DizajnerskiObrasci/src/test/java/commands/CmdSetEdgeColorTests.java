package commands;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingModel;

public class CmdSetEdgeColorTests {

	private CmdSetEdgeColor cmdSetEdgeColor;
	private DrawingModel model;

	@Before
	public void setUp() {
		model = new DrawingModel();
		cmdSetEdgeColor = new CmdSetEdgeColor(Color.RED, Color.BLUE);
		cmdSetEdgeColor.setModel(model);
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdSetEdgeColor.execute();
		assertEquals(Color.BLUE, model.getEdgeColor());
	}

	@Test
	public void testUnexecuteExpectedTrue() {
		cmdSetEdgeColor.execute();
		cmdSetEdgeColor.unexecute();
		assertEquals(Color.RED, model.getEdgeColor());
	}
}
