package commands;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;

import org.junit.*;

import geometry.Circle;
import geometry.Line;
import geometry.Point;
import mvc.DrawingModel;

public class CmdRemoveTests {
	private CmdRemove cmdRemove;
	private DrawingModel model;
	private Point point;
	private Line line;
	private Circle circle;
	
	@Before
	public void setUp() {
		model = new DrawingModel();
		point = new Point(1, 1);
		line = new Line(new Point(1, 1), new Point(5, 5), Color.RED);
		circle = new Circle(new Point(1, 1), 25);
		model.add(point);
		model.add(line);
		model.add(circle);
		cmdRemove = new CmdRemove(model);
	}
	
	@Test
	public void testExecuteExpectedTrue() throws Exception {
		cmdRemove.addShapeToRemove(line);
		cmdRemove.addShapeToRemove(circle);
		cmdRemove.execute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Point);
	}

	@Test
	public void testUnexecuteExpectedTrue() throws Exception {
		cmdRemove.addShapeToRemove(line);
		cmdRemove.addShapeToRemove(circle);
		cmdRemove.execute();
		cmdRemove.unexecute();
		assertEquals(3, model.getShapes().size());
		assertTrue(model.get(0) instanceof Point);
		assertTrue(model.get(1) instanceof Line);
		assertTrue(model.get(2) instanceof Circle);
	}
	
	@Test
	public void testUnexecuteReexecuteExpectedTrue() throws Exception {
		cmdRemove.addShapeToRemove(line);
		cmdRemove.addShapeToRemove(circle);
		cmdRemove.execute();
		cmdRemove.unexecute();
		cmdRemove.execute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Point);
	}
	
	@Test
	public void testExecuteRemoveOneShapeExpectedTrue() throws Exception {
		cmdRemove.addShapeToRemove(point);
		cmdRemove.execute();
		assertEquals(2, model.getShapes().size());
		assertTrue(model.get(0) instanceof Line);
		assertTrue(model.get(1) instanceof Circle);
	}

	@Test
	public void testUnexecuteRemoveOneShapeExpectedTrue() throws Exception {
		cmdRemove.addShapeToRemove(point);
		cmdRemove.execute();
		cmdRemove.unexecute();
		assertEquals(3, model.getShapes().size());
		assertTrue(model.get(0) instanceof Point);
		assertTrue(model.get(1) instanceof Line);
		assertTrue(model.get(2) instanceof Circle);
	}
	
	@Test
	public void testUnexecuteReexecuteRemoveOneShapeExpectedTrue() throws Exception {
		cmdRemove.addShapeToRemove(point);
		cmdRemove.execute();
		cmdRemove.unexecute();
		cmdRemove.execute();
		assertEquals(2, model.getShapes().size());
		assertTrue(model.get(0) instanceof Line);
		assertTrue(model.get(1) instanceof Circle);
	}
	
	@Test
	public void testExecuteFirstAndLastShapesExpectedTrue() throws Exception {
		cmdRemove.addShapeToRemove(point);
		cmdRemove.addShapeToRemove(circle);
		cmdRemove.execute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Line);
	}

	@Test
	public void testUnexecuteFirstAndLastShapesExpectedTrue() throws Exception {
		cmdRemove.addShapeToRemove(point);
		cmdRemove.addShapeToRemove(circle);
		cmdRemove.execute();
		cmdRemove.unexecute();
		assertEquals(3, model.getShapes().size());
		assertTrue(model.get(0) instanceof Point);
		assertTrue(model.get(1) instanceof Line);
		assertTrue(model.get(2) instanceof Circle);
	}
	
	@Test
	public void testUnexecuteReexecuteFirstAndLastShapesExpectedTrue() throws Exception {
		cmdRemove.addShapeToRemove(point);
		cmdRemove.addShapeToRemove(circle);
		cmdRemove.execute();
		cmdRemove.unexecute();
		cmdRemove.execute();
		assertEquals(1, model.getShapes().size());
		assertTrue(model.get(0) instanceof Line);
	}
	
}
