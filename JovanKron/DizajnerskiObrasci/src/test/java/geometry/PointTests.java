package geometry;

import static org.junit.Assert.*;
import org.junit.*;

import geometry.Point;
import geometry.Line;
import java.awt.Color;

public class PointTests {
	private int xCoordinate;
	private int yCoordinate;
	private Color edgeColor;
	private Point point;

	@Before
	public void setUp() {
		xCoordinate = 3;
		yCoordinate = 7;
		edgeColor = Color.RED;
		point = new Point(xCoordinate, yCoordinate, edgeColor);
	}

	@Test
	public void testContainsExpectedTrue() {
		assertTrue(point.contains(3, 7));
	}

	@Test
	public void testContainsExpectedFalse() {
		assertFalse(point.contains(0, 36));
	}
	
	@Test
	public void testContainsWrongCoordinateForXExpectedFalse() {
		assertFalse(point.contains(26, 7));
	}
	
	@Test
	public void testContainsWrongCoordinateForYExpectedFalse() {
		assertFalse(point.contains(3, 25));
	}

	@Test
	public void testEqualsExpectedTrue() {
		assertTrue(point.equals(new Point(3, 7)));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsWithDifferentTypeExpectedFalse() {
		assertFalse(point.equals(new Line(new Point(1, 1), new Point(3, 3))));
	}

}
