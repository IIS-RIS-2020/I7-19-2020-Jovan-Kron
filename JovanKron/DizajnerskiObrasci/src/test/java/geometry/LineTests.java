package geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.junit.Before;
import org.junit.Test;

public class LineTests {

	private Line line;
	private Graphics graphics;
	private Graphics2D graphics2D;
	
	@Before
	public void setUp() {
		Point stratPoint = new Point(1,1);
		Point endPoint = new Point(5,5);
		Color edgeColor = Color.RED;
		line = new Line(stratPoint, endPoint, edgeColor);
		graphics = mock(Graphics.class);
		graphics2D = mock(Graphics2D.class);
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
	
	@Test
	public void testDrawWhenColorChosenAndLineSelected() {
		line.setSelected(true);
		line.setColor(Color.RED);
		//Mock Graphics class cannot be cast to Graphics2D class within draw method, using Graphics2D object directly for testing purposes
		line.draw(graphics2D);
		verify(graphics2D).setColor(Color.RED);
		verify(graphics2D).drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(),line.getEndPoint().getY());
		verify(graphics2D).setColor(Color.BLUE);
		verify(graphics2D).drawRect(line.getStartPoint().getX() - 3, line.getStartPoint().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(line.getEndPoint().getX() - 3, line.getEndPoint().getY() - 3, 6, 6);
		verify(graphics2D).drawRect(line.middleOfLine().getX() - 3, line.middleOfLine().getY() - 3, 6, 6);
	}
	
	@Test
	public void testDrawWhenNoColorChosenAndLineNotSelected() {
		line.setSelected(false);
		line.draw(graphics2D);
		verify(graphics2D, times(1)).setColor(Color.BLACK);
		verify(graphics2D).drawLine(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(),line.getEndPoint().getY());
	}
}
