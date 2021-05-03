package geometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class LineTests {

	private Line line;
	
	@Before
	public void setUp() {
		Point stratPoint = new Point(1,1);
		Point endPoint = new Point(5,5);
		Color edgeColor = Color.RED;
		line = new Line(stratPoint, endPoint, edgeColor);
	}
	
	@Test
	public void testContainsExpectedTrue() {
		assertTrue(line.contains(3, 3));
	}

	@Test
	public void testContainsExpectedFalse() {
		assertFalse(line.contains(0, 36));
	}
	
	@Test
	public void testEqualsExpectedTrue() {
		assertTrue(line.equals(new Line(new Point(1, 1), new Point(5, 5))));
	}
	
	@Test
	public void testEqualsWithWrongStartPointExpectedFalse() {
		assertFalse(line.equals(new Line(new Point(4, 4), new Point(5, 5))));
	}
	
	@Test
	public void testEqualsWithWrongEndPointExpectedFalse() {
		assertFalse(line.equals(new Line(new Point(1, 1), new Point(4, 4))));
	}
	
	@SuppressWarnings("unlikely-arg-type") 
	@Test
	public void testEqualsWithDifferentTypeExpectedFalse() {
		assertFalse(line.equals(new Point(3, 3)));
	}
}
