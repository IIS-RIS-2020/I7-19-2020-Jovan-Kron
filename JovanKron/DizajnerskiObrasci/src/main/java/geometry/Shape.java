package geometry;

import java.awt.*;
import java.io.Serializable;
import java.util.Observable;

public abstract class Shape extends Observable implements Movable, Comparable, Serializable {
	private boolean selected;
	private int positionInArray;
	private Color edgeColor;

	public Shape() { }
	
	public Shape(boolean selected) {
		this.selected = selected;
	}

	//public abstract boolean contains(Point p);
	
	public abstract boolean contains(int x, int y);
	
	public abstract void draw(Graphics g);

	public abstract void moveBy(int byX, int byY);
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		setChanged();
		notifyObservers();
	}

	public int getPositionInArray() {
		return positionInArray;
	}

	public void setPositionInArray(int positionInArray) {
		this.positionInArray = positionInArray;
	}
	
	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}
	
	public abstract Shape clone(Shape old);
	
	public abstract Shape parse(String str);
}
