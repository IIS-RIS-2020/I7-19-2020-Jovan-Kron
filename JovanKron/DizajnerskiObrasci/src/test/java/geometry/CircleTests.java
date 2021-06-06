package geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics2D;

import org.junit.Before;
import org.junit.Test;

public class CircleTests {

	private Circle circle;
	private Graphics2D graphics2D;
	
	@Before
	public void setUp() {
		circle = new Circle(new Point(5,5), 25, Color.RED, Color.YELLOW);
		graphics2D = mock(Graphics2D.class);
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
	
	@Test
	public void testDrawWhenSelected() {
		circle.setSelected(true);
		circle.draw(graphics2D);
		verify(graphics2D).setColor(Color.YELLOW);
		verify(graphics2D).fillOval(circle.getCenter().getX() - circle.getRadius(), circle.getCenter().getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
		verify(graphics2D).setColor(Color.RED);
		verify(graphics2D).drawOval(circle.getCenter().getX() - circle.getRadius(), circle.getCenter().getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
		verify(graphics2D).setColor(Color.BLUE);
		verify(graphics2D).drawRect(circle.getCenter().getX() - 3, circle.getCenter().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(circle.getCenter().getX() - 3 - circle.getRadius(), circle.getCenter().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(circle.getCenter().getX() - 3 + circle.getRadius(), circle.getCenter().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(circle.getCenter().getX() - 3, circle.getCenter().getY() - 3 + circle.getRadius() , 6, 6);
		verify(graphics2D).drawRect(circle.getCenter().getX() - 3, circle.getCenter().getY() - 3 - circle.getRadius(), 6, 6);
	}
	
	@Test
	public void testDrawWhenNotSelected() {
		circle.setSelected(false);
		circle.draw(graphics2D);
		verify(graphics2D).setColor(Color.YELLOW);
		verify(graphics2D).fillOval(circle.getCenter().getX() - circle.getRadius(), circle.getCenter().getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
		verify(graphics2D).setColor(Color.RED);
		verify(graphics2D).drawOval(circle.getCenter().getX() - circle.getRadius(), circle.getCenter().getY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
	}
	
	@Test
	public void testCloneExpectedEqual() {
		Circle c = new Circle(new Point(0, 0), 0);
		c = circle.clone(c);
		assertEquals(5, c.getCenter().getX());
		assertEquals(5, c.getCenter().getY());
		assertEquals(25, c.getRadius());
		assertEquals(Color.RED, c.getEdgeColor());
		assertEquals(Color.YELLOW, c.getFillColor());
	}
	
	@Test
	public void testCloneWithWrongShapeSubclassExpectedEqual() {
		Point p = new Point();
		Circle c = new Circle(new Point(0, 0), 0);
		c = circle.clone(p);
		assertEquals(5, c.getCenter().getX());
		assertEquals(5, c.getCenter().getY());
		assertEquals(25, c.getRadius());
		assertEquals(Color.RED, c.getEdgeColor());
		assertEquals(Color.YELLOW, c.getFillColor());
	}
	
	@Test
	public void testCloneReferencesExpectedEqual() {
		Circle c1 = new Circle(new Point(0, 0), 0);
		Circle c2 = new Circle(new Point(0, 0), 0);
		assertEquals(c1.hashCode(), c2.clone(c1).hashCode());
	}
	
	@Test
	public void testCloneReferencesWithWrongShapeSubclassExpectedNotEqual() {
		Circle c1 = new Circle();
		c1.setCenter(new Point());
		c1.setRadius(0);
		Circle c2 = new Circle(new Point(), 0);
		Point p = new Point();
		assertNotEquals(c1.hashCode(), c2.clone(p).hashCode());
	}
	
	@Test
	public void testToStringWhenSelected() {
		circle.setSelected(true);
		assertEquals("Circle:Center(5,5),radius=25,Edge-color=[255-0-0],Surface-color=[255-255-0],selected=true", circle.toString());
	}
	
	@Test
	public void testToStringWhenNotSelected() {
		assertEquals("Circle:Center(5,5),radius=25,Edge-color=[255-0-0],Surface-color=[255-255-0],selected=false", circle.toString());
	}
	
	@Test
	public void testParseWhenSelected() {
		String str = "Circle:Center(871,271),radius=50,Edge-color=[0-0-0],Surface-color=[255-255-0],selected=true";
		Circle c = new Circle().parse(str);
		assertEquals(871, c.getCenter().getX());
		assertEquals(271, c.getCenter().getY());
		assertEquals(50, c.getRadius());
		assertEquals(Color.BLACK, c.getEdgeColor());
		assertEquals(Color.YELLOW, c.getFillColor());
		assertTrue(c.isSelected());
	}
	
	@Test
	public void testParseWhenNotSelected() {
		String str = "Circle:Center(871,271),radius=50,Edge-color=[0-0-0],Surface-color=[255-255-0],selected=false";
		Circle c = new Circle().parse(str);
		assertEquals(871, c.getCenter().getX());
		assertEquals(271, c.getCenter().getY());
		assertEquals(50, c.getRadius());
		assertEquals(Color.BLACK, c.getEdgeColor());
		assertEquals(Color.YELLOW, c.getFillColor());
		assertFalse(c.isSelected());
	}
}
