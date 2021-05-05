package geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class DonutTests {
	
	private Donut donut;

	@Before
	public void setUp() {
		donut = new Donut(new Point(5, 5), 20, 10, Color.RED, Color.YELLOW);
	}
	
	@Test
	public void testContainsExpectedTrue() {
		assertTrue(donut.contains(17,17));
	}

	@Test
	public void testContainsExpectedFalse() {
		assertFalse(donut.contains(0, 100));
	}
	
	@Test
	public void testContainsWithWrongXCoordinateExpectedFalse() {
		assertFalse(donut.contains(100,17));
	}
	
	@Test
	public void testContainsWithWrongYCoordinateExpectedFalse() {
		assertFalse(donut.contains(17,100));
	}
	
	@Test
	public void testContainsWithCoordinatesInInnerCircleExpectedFalse() {
		assertFalse(donut.contains(6, 6));
	}
	
	@Test
	public void testEqualsExpectedTrue() {
		assertTrue(donut.equals(new Donut(new Point(5, 5), 20, 10)));
	}
	
	@Test
	public void testEqualsWithWrongCenterExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(1, 1), 20, 10)));
	}
	
	@Test
	public void testEqualsWithWrongRadiusExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(5, 5), 99, 10)));
	}
	
	@Test
	public void testEqualsWithWrongInnerRadiusExpectedFalse() {
		assertFalse(donut.equals(new Donut(new Point(5, 5), 20, 99)));
	}
	
	@SuppressWarnings("unlikely-arg-type") 
	@Test
	public void testEqualsWithDifferentTypeExpectedFalse() {
		assertFalse(donut.equals(new Point(3, 3)));
	}

	@Test
	public void testCompareToExpectedEqual() {
		Donut d = new Donut(new Point(5, 5), 20, 15);
		assertEquals(392, donut.compareTo(d));
	}
	
	@Test
	public void testCompareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, donut.compareTo(new Point()));
	}
	
	@Test
	public void testAreaExpectedEqual() {
		//the last parameter is the maximum delta between expected and actual for which both numbers are still considered equal
		assertEquals(942.4777960769379, donut.area(), 1e-15);
	}
}
