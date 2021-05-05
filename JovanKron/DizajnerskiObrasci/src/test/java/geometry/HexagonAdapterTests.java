package geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import hexagon.Hexagon;

public class HexagonAdapterTests {
	
	private HexagonAdapter hexagonAdapter;

	@Before
	public void setUp() {
		hexagonAdapter = new HexagonAdapter(new Hexagon(5, 5, 25), Color.RED, Color.YELLOW);
	}
	
	@Test
	public void testContainsExpectedTrue() {
		assertTrue(hexagonAdapter.contains(6,6));
	}

	@Test
	public void testContainsExpectedFalse() {
		assertFalse(hexagonAdapter.contains(0, 100));
	}
	
	@Test
	public void testEqualsExpectedTrue() {
		assertTrue(hexagonAdapter.equals(new HexagonAdapter(5, 5, 25)));
	}
	
	@Test
	public void testEqualsWithWrongCenterExpectedFalse() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(2, 3, 25)));
	}
	
	@Test
	public void testEqualsWithWrongEdgeExpectedFalse() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(5, 5, 99)));
	}
	
	@Test
	public void testEqualsWithWrongXCoordinateExpectedFalse() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(2, 5, 25)));
	}
	
	@Test
	public void testEqualsWithWrongYCoordinateExpectedFalse() {
		assertFalse(hexagonAdapter.equals(new HexagonAdapter(5, 2, 25)));
	}
	
	@SuppressWarnings("unlikely-arg-type") 
	@Test
	public void testEqualsWithDifferentTypeExpectedFalse() {
		assertFalse(hexagonAdapter.equals(new Point(3, 3)));
	}
	
	@Test
	public void testMoveByXWithPositiveNumberExpectedEqual() {
		hexagonAdapter.moveBy(5, 0);
		assertEquals(10, hexagonAdapter.getX());
	}
	
	@Test
	public void testMoveByXWithNegativeNumberExpectedEqual() {
		hexagonAdapter.moveBy(-1, 0);
		assertEquals(4, hexagonAdapter.getX());
	}
	
	@Test
	public void testMoveByYWithPositiveNumberExpectedEqual() {
		hexagonAdapter.moveBy(0, 5);
		assertEquals(10, hexagonAdapter.getY());
	}
	
	@Test
	public void testMoveByYWithNegativeNumberExpectedEqual() {
		hexagonAdapter.moveBy(0, -2);
		assertEquals(3, hexagonAdapter.getY());
	}
	
	@Test
	public void testCompareToExpectedEqual() {
		HexagonAdapter h = new HexagonAdapter(5, 5, 15);
		assertEquals(10, hexagonAdapter.compareTo(h));
	}
	
	@Test
	public void testCompareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, hexagonAdapter.compareTo(new Point()));
	}
	
}
