package geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import hexagon.Hexagon;

public class HexagonAdapterTests {
	
	private HexagonAdapter hexagonAdapter;
	private Graphics graphics;
	private Hexagon hexagon;

	@Before
	public void setUp() {
		hexagon = mock(Hexagon.class);
		hexagonAdapter = new HexagonAdapter(new Hexagon(5, 5, 25), Color.RED, Color.YELLOW);
		graphics = mock(Graphics.class);
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
	public void testAreaExpectedEqual() {
		assertEquals(0, hexagonAdapter.area(), 0.00000000001d);
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
	
	@Test
	public void testDrawWhenSelected() {
		HexagonAdapter h = new HexagonAdapter(hexagon, Color.RED, Color.YELLOW);
		h.setSelected(true);
		h.draw(graphics);
		verify(hexagon).paint(graphics);
	}
	
	@Test
	public void testDrawWhenNotSelected() {
		HexagonAdapter h = new HexagonAdapter(hexagon, Color.RED, Color.YELLOW);
		h.setSelected(false);
		h.draw(graphics);
		verify(hexagon).paint(graphics);
	}
	
	@Test
	public void testCloneExpectedEqual() {
		HexagonAdapter h = new HexagonAdapter(new Hexagon(0, 0, 0), Color.BLACK, Color.BLACK);
		h = (HexagonAdapter) hexagonAdapter.clone(h);
		assertEquals(5, h.getX());
		assertEquals(5, h.getY());
		assertEquals(25, h.getR());
		assertEquals(Color.RED, h.getEdgeColor());
		assertEquals(Color.YELLOW, h.getFillColor());
	}
	
	@Test
	public void testCloneWithWrongShapeSubclassExpectedEqual() {
		Point p = new Point();
		HexagonAdapter h = new HexagonAdapter(new Hexagon(0, 0, 0), Color.BLACK, Color.BLACK);
		h = (HexagonAdapter) hexagonAdapter.clone(p);
		assertEquals(5, h.getX());
		assertEquals(5, h.getY());
		assertEquals(25, h.getR());
		assertEquals(Color.RED, h.getEdgeColor());
		assertEquals(Color.YELLOW, h.getFillColor());
	}
	
	@Test
	public void testCloneReferencesExpectedEqual() {
		HexagonAdapter h1 = new HexagonAdapter(new Hexagon(0, 0, 0), Color.BLACK, Color.BLACK);
		HexagonAdapter h2 = new HexagonAdapter(new Hexagon(0, 0, 0), Color.BLACK, Color.BLACK);
		assertEquals(h1.hashCode(), h2.clone(h1).hashCode());
	}
	
	@Test
	public void testCloneReferencesWithWrongShapeSubclassExpectedNotEqual() {
		HexagonAdapter h1 = new HexagonAdapter();
		h1.setHexagon(new Hexagon(0, 0, 0));
		HexagonAdapter h2 = new HexagonAdapter(new Hexagon(0, 0, 0), Color.BLACK, Color.BLACK);
		Point p = new Point();
		assertNotEquals(h1.hashCode(), h2.clone(p).hashCode());
	}
	
	@Test
	public void testToStringWhenSelected() {
		hexagonAdapter.setSelected(true);
		assertEquals("Hexagon:(X=5,Y=5),r=25,Edge-color=[255-0-0],Surface-color=[255-255-0],selected=true", hexagonAdapter.toString());
	}
	
	@Test
	public void testToStringWhenNotSelected() {
		assertEquals("Hexagon:(X=5,Y=5),r=25,Edge-color=[255-0-0],Surface-color=[255-255-0],selected=false", hexagonAdapter.toString());
	}
	
	@Test
	public void testParseWhenSelected() {
		String str = "Hexagon:(X=800,Y=339),r=55,Edge-color=[0-0-0],Surface-color=[255-255-0],selected=true";
		HexagonAdapter h = new HexagonAdapter().parse(str);
		assertEquals(800, h.getX());
		assertEquals(339, h.getY());
		assertEquals(55, h.getR());
		assertEquals(Color.BLACK, h.getEdgeColor());
		assertEquals(Color.YELLOW, h.getFillColor());
		assertTrue(h.isSelected());
	}
	
	@Test
	public void testParseWhenNotSelected() {
		String str = "Hexagon:(X=800,Y=339),r=55,Edge-color=[0-0-0],Surface-color=[255-255-0],selected=false";
		HexagonAdapter h = new HexagonAdapter().parse(str);
		assertEquals(800, h.getX());
		assertEquals(339, h.getY());
		assertEquals(55, h.getR());
		assertEquals(Color.BLACK, h.getEdgeColor());
		assertEquals(Color.YELLOW, h.getFillColor());
		assertFalse(h.isSelected());
	}
	
}
