package geometry;

import static org.junit.Assert.assertEquals;
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
	
	@Test
	public void testMoveByXWithPositiveNumberExpectedTrue() {
		line.moveBy(5, 0);
		assertEquals(6, line.getStartPoint().getX());
	}
	
	@Test
	public void testMoveByXWithNegativeNumberExpectedTrue() {
		line.moveBy(-1, 0);
		assertEquals(0, line.getStartPoint().getX());
	}
	
	@Test
	public void testMoveByYWithPositiveNumberExpectedTrue() {
		line.moveBy(0, 5);
		assertEquals(10, line.getEndPoint().getY());
	}
	
	@Test
	public void testMoveByYWithNegativeNumberExpectedTrue() {
		line.moveBy(0, -2);
		assertEquals(3, line.getEndPoint().getY());
	}
	
	@Test
	public void testMiddleOfLineExpectedTrue() {
		assertEquals(new Point(3,3), line.middleOfLine());
	}
	
	@Test
	public void testCompareToExpectedTrue() {
		Line l = new Line(new Point(2,2), new Point(8,8));
		assertEquals(-2, line.compareTo(l));
	}
	
	@Test
	public void testCompareToWithDifferentTypeExpectedTrue() {
		assertEquals(0, line.compareTo(new Point()));
	}
	
}
