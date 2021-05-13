package mvc;

import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import geometry.Circle;
import geometry.Point;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DrawingViewTests {
	
	DrawingView view;
	Graphics graphics;

	@Before
	public void setUp() {
		DrawingModel model = new DrawingModel();
		view = new DrawingView();
		view.setModel(model);
		graphics = mock(Graphics.class);
	}
	
	@Test
	public void testPaintWithoytAnyShapesExpectedTrue() {
		view.paint(graphics);
	}
	
	@Test
	public void testPaintWithOneShapeExpectedTrue() {
		Point p = mock(Point.class);
		view.getModel().add(p);
		view.paint(graphics);
		verify(p).draw(graphics);
	}
	
	@Test
	public void testPaintWithMoreThanOneShapeExpectedTrue() {
		Point p = mock(Point.class);
		Circle c = mock(Circle.class);
		view.getModel().add(p);
		view.getModel().add(c);
		view.paint(graphics);
		verify(p).draw(graphics);
		verify(c).draw(graphics);
	}
}
