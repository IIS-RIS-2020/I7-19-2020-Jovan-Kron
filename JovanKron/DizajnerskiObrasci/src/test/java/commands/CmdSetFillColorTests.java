package commands;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import mvc.DrawingModel;

public class CmdSetFillColorTests {

	private CmdSetFillColor cmdSetFillColor;
	private DrawingModel model;

	@Before
	public void setUp() {
		model = new DrawingModel();
		cmdSetFillColor = new CmdSetFillColor(Color.RED, Color.BLUE);
		cmdSetFillColor.setModel(model);
	}
	
	@Test
	public void testExecuteExpectedTrue() {
		cmdSetFillColor.execute();
		assertEquals(Color.BLUE, model.getFillColor());
	}

	@Test
	public void testUnexecuteExpectedTrue() {
		cmdSetFillColor.execute();
		cmdSetFillColor.unexecute();
		assertEquals(Color.RED, model.getFillColor());
	}
}
