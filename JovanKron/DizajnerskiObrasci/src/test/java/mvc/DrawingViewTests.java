package mvc;

import java.awt.Graphics;
import org.junit.*;
import geometry.*;
import static org.mockito.Mockito.*;

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
		Point point = mock(Point.class);
		view.getModel().add(point);
		view.paint(graphics);
		verify(point).draw(graphics);
	}
	
	@Test
	public void testPaintWithMoreThanOneShapeExpectedTrue() {
		Point point = mock(Point.class);
		Circle circle = mock(Circle.class);
		view.getModel().add(point);
		view.getModel().add(circle);
		view.paint(graphics);
		verify(point).draw(graphics);
		verify(circle).draw(graphics);
	}
	
}
