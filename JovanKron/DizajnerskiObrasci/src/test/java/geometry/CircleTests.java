package geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class CircleTests {

	private Circle circle;
	
	@Before
	public void setUp() {
		circle = new Circle(new Point(5,5), 25, Color.RED, Color.YELLOW);
	}
	
	@Test
	public void testContainsExpectedTrue() {
		assertTrue(circle.contains(3, 3));
	}

	@Test
	public void testContainsExpectedFalse() {
		assertFalse(circle.contains(0, 36));
	}
	
	@Test
	public void testEqualsExpectedTrue() {
		assertTrue(circle.equals(new Circle(new Point(5, 5), 25)));
	}
	
	@Test
	public void testEqualsWithWrongCenterExpectedFalse() {
		assertFalse(circle.equals(new Circle(new Point(1, 1), 25)));
	}
	
	@Test
	public void testEqualsWithWrongRadiusExpectedFalse() {
		assertFalse(circle.equals(new Circle(new Point(5, 5), 10)));
	}
	
	@SuppressWarnings("unlikely-arg-type") 
	@Test
	public void testEqualsWithDifferentTypeExpectedFalse() {
		assertFalse(circle.equals(new Point(3, 3)));
	}
	
	@Test
	public void testMoveByXWithPositiveNumberExpectedEqual() {
		circle.moveBy(5, 0);
		assertEquals(10, circle.getCenter().getX());
	}
	
	@Test
	public void testMoveByXWithNegativeNumberExpectedEqual() {
		circle.moveBy(-1, 0);
		assertEquals(4, circle.getCenter().getX());
	}
	
	@Test
	public void testMoveByYWithPositiveNumberExpectedEqual() {
		circle.moveBy(0, 5);
		assertEquals(10, circle.getCenter().getY());
	}
	
	@Test
	public void testMoveByYWithNegativeNumberExpectedEqual() {
		circle.moveBy(0, -2);
		assertEquals(3, circle.getCenter().getY());
	}
	
	@Test
	public void testCompareToExpectedEqual() {
		Circle c = new Circle(new Point(2,2), 10);
		assertEquals(15, circle.compareTo(c));
	}
	
	@Test
	public void testCompareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, circle.compareTo(new Point()));
	}
	
	@Test
	public void testAreaExpectedEqual() {
		//the last parameter is the maximum delta between expected and actual for which both numbers are still considered equal
		assertEquals(1963.4954084936207, circle.area(), 1e-15);
	}
	
}
