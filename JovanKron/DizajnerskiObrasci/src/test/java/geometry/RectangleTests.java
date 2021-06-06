package geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

public class RectangleTests {

	private Rectangle rectangle;
	private Graphics graphics;
	
	@Before
	public void setUp() {
		rectangle = new Rectangle(new Point(5,5), 20, 10, Color.RED, Color.YELLOW);
		graphics = mock(Graphics.class);
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
	public void testMoveByXWithPositiveNumberExpectedEqual() {
		rectangle.moveBy(5, 0);
		assertEquals(10, rectangle.getUpperLeftPoint().getX());
	}
	
	@Test
	public void testMoveByXWithNegativeNumberExpectedEqual() {
		rectangle.moveBy(-1, 0);
		assertEquals(4, rectangle.getUpperLeftPoint().getX());
	}
	
	@Test
	public void testMoveByYWithPositiveNumberExpectedEqual() {
		rectangle.moveBy(0, 5);
		assertEquals(10, rectangle.getUpperLeftPoint().getY());
	}
	
	@Test
	public void testMoveByYWithNegativeNumberExpectedEqual() {
		rectangle.moveBy(0, -2);
		assertEquals(3, rectangle.getUpperLeftPoint().getY());
	}
	
	@Test
	public void testCompareToExpectedEqual() {
		Rectangle r = new Rectangle(new Point(2,2), 20, 5);
		assertEquals(100, rectangle.compareTo(r));
	}
	
	@Test
	public void testCompareToWithDifferentTypeExpectedEqual() {
		assertEquals(0, rectangle.compareTo(new Point()));
	}
	
	@Test
	public void testAreaExpectedEqual() {
		//the last parameter is the maximum delta between expected and actual for which both numbers are still considered equal
		assertEquals(200, rectangle.area(), 1e-15);
	}
	
	@Test
	public void testDrawWhenSelected() {
		rectangle.setSelected(true);
		rectangle.draw(graphics);
		verify(graphics).setColor(Color.YELLOW);
		verify(graphics).fillRect(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY(), rectangle.getWidth(), rectangle.getHeight());
		verify(graphics).setColor(Color.RED);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY(), rectangle.getWidth(), rectangle.getHeight());
		verify(graphics).setColor(Color.BLUE);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() - 3, rectangle.getUpperLeftPoint().getY() - 3, 6, 6);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() - 3 + rectangle.getWidth(), rectangle.getUpperLeftPoint().getY() - 3, 6, 6);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() - 3, rectangle.getUpperLeftPoint().getY() - 3 + rectangle.getHeight(), 6, 6);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX() + rectangle.getWidth() - 3, rectangle.getUpperLeftPoint().getY() + rectangle.getHeight() - 3, 6, 6);
	}
	
	@Test
	public void testDrawWhenNotSelected() {
		rectangle.setSelected(false);
		rectangle.draw(graphics);
		verify(graphics).setColor(Color.YELLOW);
		verify(graphics).fillRect(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY(), rectangle.getWidth(), rectangle.getHeight());
		verify(graphics).setColor(Color.RED);
		verify(graphics).drawRect(rectangle.getUpperLeftPoint().getX(), rectangle.getUpperLeftPoint().getY(), rectangle.getWidth(), rectangle.getHeight());	
	}
	
	@Test
	public void testCloneExpectedEqual() {
		Rectangle r = new Rectangle(new Point(0, 0), 0, 0);
		r = rectangle.clone(r);
		assertEquals(5, r.getUpperLeftPoint().getX());
		assertEquals(5, r.getUpperLeftPoint().getY());
		assertEquals(10, r.getHeight());
		assertEquals(20, r.getWidth());
		assertEquals(Color.RED, r.getEdgeColor());
		assertEquals(Color.YELLOW, r.getFillColor());
	}
	
	@Test
	public void testCloneWithWrongShapeSubclassExpectedEqual() {
		Point p = new Point();
		Rectangle r = new Rectangle(new Point(0, 0), 0, 0);
		r = rectangle.clone(p);
		assertEquals(5, r.getUpperLeftPoint().getX());
		assertEquals(5, r.getUpperLeftPoint().getY());
		assertEquals(10, r.getHeight());
		assertEquals(20, r.getWidth());
		assertEquals(Color.RED, r.getEdgeColor());
		assertEquals(Color.YELLOW, r.getFillColor());
	}
	
	@Test
	public void testCloneReferencesExpectedEqual() {
		Rectangle r1 = new Rectangle(new Point(), 0, 0);
		Rectangle r2 = new Rectangle(new Point(), 0, 0);
		assertEquals(r1.hashCode(), r2.clone(r1).hashCode());
	}
	
	@Test
	public void testCloneReferencesWithWrongShapeSubclassExpectedNotEqual() {
		Rectangle r1 = new Rectangle();
		r1.setUpperLeftPoint(new Point());
		r1.setHeight(0);
		r1.setWidth(0);
		Rectangle r2 = new Rectangle(new Point(), 0, 0);
		Point p = new Point();
		assertNotEquals(r1.hashCode(), r2.clone(p).hashCode());
	}
	
	@Test
	public void testToStringWhenSelected() {
		rectangle.setSelected(true);
		assertEquals("Rectangle:Upper-left(5,5),Width=20,Height=10,Edge-color=[255-0-0],Surface-color=[255-255-0],selected=true", rectangle.toString());
	}
	
	@Test
	public void testToStringWhenNotSelected() {
		assertEquals("Rectangle:Upper-left(5,5),Width=20,Height=10,Edge-color=[255-0-0],Surface-color=[255-255-0],selected=false", rectangle.toString());
	}
	
	@Test
	public void testParseWhenSelected() {
		String str = "Rectangle:Upper-left(681,177),Width=250,Height=50,Edge-color=[0-0-0],Surface-color=[255-255-0],selected=true";
		Rectangle r = new Rectangle().parse(str);
		assertEquals(681, r.getUpperLeftPoint().getX());
		assertEquals(177, r.getUpperLeftPoint().getY());
		assertEquals(250, r.getWidth());
		assertEquals(50, r.getHeight());
		assertEquals(Color.BLACK, r.getEdgeColor());
		assertEquals(Color.YELLOW, r.getFillColor());
		assertTrue(r.isSelected());
	}
	
	@Test
	public void testParseWhenNotSelected() {
		String str = "Rectangle:Upper-left(681,177),Width=250,Height=50,Edge-color=[0-0-0],Surface-color=[255-255-0],selected=false";
		Rectangle r = new Rectangle().parse(str);
		assertEquals(681, r.getUpperLeftPoint().getX());
		assertEquals(177, r.getUpperLeftPoint().getY());
		assertEquals(250, r.getWidth());
		assertEquals(50, r.getHeight());
		assertEquals(Color.BLACK, r.getEdgeColor());
		assertEquals(Color.YELLOW, r.getFillColor());
		assertFalse(r.isSelected());
	}
}
