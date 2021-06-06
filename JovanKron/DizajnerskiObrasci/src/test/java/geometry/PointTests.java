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
	
	@Test
	public void testCloneExpectedEqual() {
		Point p = new Point();
		p = point.clone(p);
		assertEquals(3, p.getX());
		assertEquals(7, p.getY());
		assertEquals(Color.RED, p.getEdgeColor());
	}
	
	@Test
	public void testCloneWithWrongShapeSubclassExpectedEqual() {
		Point p = new Point();
		Line l = new Line();
		p = point.clone(l);
		assertEquals(3, p.getX());
		assertEquals(7, p.getY());
		assertEquals(Color.RED, p.getEdgeColor());
	}
	
	@Test
	public void testCloneReferencesExpectedEqual() {
		Point p1 = new Point();
		Point p2 = new Point();
		assertEquals(p1.hashCode(), p2.clone(p1).hashCode());
	}
	
	@Test
	public void testCloneReferencesWithWrongShapeSubclassExpectedNotEqual() {
		Point p1 = new Point();
		Point p2 = new Point();
		Line l = new Line();
		assertNotEquals(p1.hashCode(), p2.clone(l).hashCode());
	}
	
	@Test
	public void testToStringWhenSelected() {
		point.setSelected(true);
		assertEquals("Point:(3,7),Edge-color=[255-0-0],selected=true", point.toString());
	}
	
	@Test
	public void testToStringWhenNotSelected() {
		assertEquals("Point:(3,7),Edge-color=[255-0-0],selected=false", point.toString());
	}
	
	@Test
	public void testParseWhenSelected() {
		String str = "Point:(801,205),Edge-color=[0-0-0],selected=true";
		Point p = new Point().parse(str);
		assertEquals(801, p.getX());
		assertEquals(205, p.getY());
		assertEquals(Color.BLACK, p.getEdgeColor());
		assertTrue(p.isSelected());
	}
	
	@Test
	public void testParseWhenNotSelected() {
		String str = "Point:(801,205),Edge-color=[0-0-0],selected=false";
		Point p = new Point().parse(str);
		assertEquals(801, p.getX());
		assertEquals(205, p.getY());
		assertEquals(Color.BLACK, p.getEdgeColor());
		assertFalse(p.isSelected());
	}
}
