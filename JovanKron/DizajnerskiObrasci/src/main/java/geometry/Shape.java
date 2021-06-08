package geometry;

import java.awt.*;
import java.io.Serializable;
import java.util.Observable;

public abstract class Shape extends Observable implements Movable, Comparable<Object>, Serializable {
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color edgeColor;

	public Shape() {}
	
	//[0-0-0]
	public Color getColor(String textOfColor) {
		String [] parts = textOfColor.substring(1, textOfColor.length()-1).split("-");
		int red = Integer.parseInt(parts[0]);
		int green = Integer.parseInt(parts[1]);
		int blue = Integer.parseInt(parts[2]);
		Color color = new Color(red, green, blue);
		return color;
	}
	
	public abstract boolean contains(int x, int y);
	
	public abstract void moveBy(int byX, int byY);

	public abstract void draw(Graphics g);
	
	public abstract Shape clone(Shape old);
	
	public abstract Shape parse(String str);
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		setChanged();
		notifyObservers();
	}
	
	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

}
