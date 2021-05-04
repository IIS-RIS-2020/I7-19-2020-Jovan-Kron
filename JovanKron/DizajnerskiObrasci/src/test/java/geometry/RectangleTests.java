package geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class RectangleTests {

private Rectangle rectangle;
	
	@Before
	public void setUp() {
		rectangle = new Rectangle(new Point(5,5), 20, 10, Color.RED, Color.YELLOW);
	}
	
	@Test
	public void testContainsExpectedTrue() {
		assertTrue(rectangle.contains(6,6));
	}

	@Test
	public void testContainsExpectedFalse() {
		assertFalse(rectangle.contains(0, 100));
	}
	
	@Test
	public void testContainsWithWrongXCoordinateExpectedFalse() {
		assertFalse(rectangle.contains(100,6));
	}
	
	@Test
	public void testContainsWithWrongYCoordinateExpectedFalse() {
		assertFalse(rectangle.contains(6,100));
	}
	
	@Test
	public void testContainsWithNegativeYCoordinateExpectedFalse() {
		assertFalse(rectangle.contains(6, -10));
	}
	
	
	@Test
	public void testEqualsExpectedTrue() {
		assertTrue(rectangle.equals(new Rectangle(new Point(5, 5), 20, 10)));
	}
	
	@Test
	public void testEqualsWithWrongUpperLeftPointExpectedFalse() {
		assertFalse(rectangle.equals(new Rectangle(new Point(1, 1), 20, 10)));
	}
	
	@Test
	public void testEqualsWithWrongWidthExpectedFalse() {
		assertFalse(rectangle.equals(new Rectangle(new Point(5, 5), 99, 10)));
	}
	
	@Test
	public void testEqualsWithWrongHeightExpectedFalse() {
		assertFalse(rectangle.equals(new Rectangle(new Point(5, 5), 20, 99)));
	}
	
	@SuppressWarnings("unlikely-arg-type") 
	@Test
	public void testEqualsWithDifferentTypeExpectedFalse() {
		assertFalse(rectangle.equals(new Point(3, 3)));
	}
	
	@Test
	public void testMoveByXWithPositiveNumberExpectedTrue() {
		rectangle.moveBy(5, 0);
		assertEquals(10, rectangle.getUpperLeftPoint().getX());
	}
	
	@Test
	public void testMoveByXWithNegativeNumberExpectedTrue() {
		rectangle.moveBy(-1, 0);
		assertEquals(4, rectangle.getUpperLeftPoint().getX());
	}
	
	@Test
	public void testMoveByYWithPositiveNumberExpectedTrue() {
		rectangle.moveBy(0, 5);
		assertEquals(10, rectangle.getUpperLeftPoint().getY());
	}
	
	@Test
	public void testMoveByYWithNegativeNumberExpectedTrue() {
		rectangle.moveBy(0, -2);
		assertEquals(3, rectangle.getUpperLeftPoint().getY());
	}
	
	@Test
	public void testCompareToExpectedTrue() {
		Rectangle r = new Rectangle(new Point(2,2), 20, 5);
		assertEquals(100, rectangle.compareTo(r));
	}
	
	@Test
	public void testCompareToWithDifferentTypeExpectedTrue() {
		assertEquals(0, rectangle.compareTo(new Point()));
	}
	
	@Test
	public void testAreaExpectedTrue() {
		//the last parameter is the maximum delta between expected and actual for which both numbers are still considered equal
		assertEquals(200, rectangle.area(), 1e-15);
	}
}
