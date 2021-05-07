package geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class DonutTests {
	
	private Donut donut;
	private Graphics2D graphics2D;
	ArgumentCaptor<Shape> argument;
	Area area;

	@Before
	public void setUp() {
		donut = new Donut(new Point(5, 5), 20, 10, Color.RED, Color.YELLOW);
		graphics2D = mock(Graphics2D.class);
		argument = ArgumentCaptor.forClass(Shape.class);
		Ellipse2D outer = new Ellipse2D.Double(
				donut.getCenter().getX() - donut.getRadius(), donut.getCenter().getY() - donut.getRadius(),
				donut.getRadius() * 2, donut.getRadius() * 2);
		Ellipse2D inner = new Ellipse2D.Double(
				donut.getCenter().getX() - donut.getInnerRadius(), donut.getCenter().getY() - donut.getInnerRadius(),
				donut.getInnerRadius() * 2, donut.getInnerRadius() * 2);
		area = new Area(outer);
		area.subtract(new Area(inner));
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
	
	@Test
	public void testDrawWhenSelected() {
		donut.setSelected(true);
		donut.draw(graphics2D);
		verify(graphics2D).setColor(Color.YELLOW);
		verify(graphics2D).fill(argument.capture());
		assertEquals(area.getBounds(), (argument.getValue().getBounds()));
		verify(graphics2D).setColor(Color.RED);
		verify(graphics2D).draw(argument.capture());
		assertEquals(area.getBounds(), (argument.getValue().getBounds()));
		verify(graphics2D).setColor(Color.BLUE);
		verify(graphics2D).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(donut.getCenter().getX() + donut.getInnerRadius() - 3, donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(donut.getCenter().getX() - donut.getInnerRadius() - 3, donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() + donut.getInnerRadius() - 3, 6, 6);
		verify(graphics2D).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() - donut.getInnerRadius() - 3, 6, 6);
		verify(graphics2D).drawRect(donut.getCenter().getX() + donut.getRadius() - 3, donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(donut.getCenter().getX() - donut.getRadius() - 3, donut.getCenter().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() + donut.getRadius() - 3, 6, 6);
		verify(graphics2D).drawRect(donut.getCenter().getX() - 3, donut.getCenter().getY() - donut.getRadius() - 3, 6, 6);
	}
	
	@Test
	public void testDrawWhenNotSelected() {
		donut.setSelected(false);
		donut.draw(graphics2D);
		verify(graphics2D).setColor(Color.YELLOW);
		verify(graphics2D).fill(argument.capture());
		assertEquals(area.getBounds(), (argument.getValue().getBounds()));
		verify(graphics2D).setColor(Color.RED);
		verify(graphics2D).draw(argument.capture());
		assertEquals(area.getBounds(), (argument.getValue().getBounds()));
	}
	
	@Test
	public void testCloneExpectedEqual() {
		Donut d = new Donut(new Point(0, 0), 0 , 0);
		d = donut.clone(d);
		assertEquals(5, d.getCenter().getX());
		assertEquals(5, d.getCenter().getY());
		assertEquals(20, d.getRadius());
		assertEquals(10, d.getInnerRadius());
		assertEquals(Color.RED, d.getEdgeColor());
		assertEquals(Color.YELLOW, d.getFillColor());
	}
	
	@Test
	public void testCloneWithWrongShapeSubclassExpectedEqual() {
		Point p = new Point();
		Donut d = new Donut(new Point(0, 0), 0 , 0);
		d = donut.clone(p);
		assertEquals(5, d.getCenter().getX());
		assertEquals(5, d.getCenter().getY());
		assertEquals(20, d.getRadius());
		assertEquals(10, d.getInnerRadius());
		assertEquals(Color.RED, d.getEdgeColor());
		assertEquals(Color.YELLOW, d.getFillColor());
	}
	
	@Test
	public void testCloneReferencesExpectedEqual() {
		Donut d1 = new Donut(new Point(0, 0), 0 , 0);
		Donut d2 = new Donut(new Point(0, 0), 0 , 0);
		assertEquals(d1.hashCode(), d2.clone(d1).hashCode());
	}
	
	@Test
	public void testCloneReferencesWithWrongShapeSubclassExpectedNotEqual() {
		Donut d1 = new Donut();
		d1.setCenter(new Point());
		d1.setRadius(0);
		d1.setInnerRadius(0);
		Donut d2 = new Donut(new Point(0, 0), 0 , 0);
		Point p = new Point();
		assertNotEquals(d1.hashCode(), d2.clone(p).hashCode());
	}
}
