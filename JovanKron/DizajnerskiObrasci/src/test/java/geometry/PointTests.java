package geometry;

import static org.junit.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;

public class PointTests {
	private Point point;
	private Graphics graphics;

	@Before
	public void setUp() {
		point = new Point(3, 7, Color.RED);
		graphics = mock(Graphics.class);
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
	
	@Test
	public void testEqualsWithWrongCoordinateForXExpectedFalse() {
		assertFalse(point.equals(new Point(1, 7)));
	}
	
	@Test
	public void testEqualsWithWrongCoordinateForYExpectedFalse() {
		assertFalse(point.equals(new Point(3, 1)));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsWithDifferentTypeExpectedFalse() {
		assertFalse(point.equals(new Line(new Point(1, 1), new Point(3, 3))));
	}

	@Test
	public void testMoveByXWithPositiveNumberExpectedEqual() {
		point.moveBy(5, 0);
		assertEquals(8, point.getX());
	}
	
	@Test
	public void testMoveByXWithNegativeNumberExpectedEqual() {
		point.moveBy(-2, 0);
		assertEquals(1, point.getX());
	}
	
	@Test
	public void testMoveByYWithPositiveNumberExpectedEqual() {
		point.moveBy(0, 5);
		assertEquals(12, point.getY());
	}
	
	@Test
	public void testMoveByYWithNegativeNumberExpectedEqual() {
		point.moveBy(0, -2);
		assertEquals(5, point.getY());
	}
	
	@Test
	public void testCompareToExpectedEqual() {
		Point p = new Point();
		p.setX(5);
		p.setY(9);
		assertEquals(-2, point.compareTo(p));
	}
	
	@Test
	public void testCompareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, point.compareTo(new Line()));
	}
	
	@Test
	public void testDrawWhenSelected() {
		point.setSelected(true);
		point.draw(graphics);
		verify(graphics).setColor(point.getEdgeColor());
		verify(graphics).drawLine(point.getX() - 2, point.getY(), point.getX() + 2, point.getY());
		verify(graphics).drawLine(point.getX(), point.getY() - 2, point.getX(), point.getY() + 2);
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(point.getX() - 3, point.getY() - 3, 6, 6);
	}
	
	@Test
	public void testDrawWhenNotSelected() {
		point.setSelected(false);
		point.draw(graphics);
		verify(graphics).setColor(point.getEdgeColor());
		verify(graphics).drawLine(point.getX() - 2, point.getY(), point.getX() + 2, point.getY());
		verify(graphics).drawLine(point.getX(), point.getY() - 2, point.getX(), point.getY() + 2);
	}
}
