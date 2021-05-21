package mvc;

import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel {

	private static final long serialVersionUID = 1L;
	private DrawingModel model = new DrawingModel();

	public void setModel(DrawingModel model) {
		this.model = model;
		
	}
	
	public DrawingModel getModel() {
		return model;
	}
	
	public void paint(Graphics g) {
		ListIterator<Shape> it = model.getShapes().listIterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}
}
