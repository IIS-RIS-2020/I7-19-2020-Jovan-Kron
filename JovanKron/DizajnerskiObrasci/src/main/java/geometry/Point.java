package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private static final long serialVersionUID = 1L;
	private int x, y;
	
	public Point() {}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, Color edgeColor) {
		this(x, y);
		setEdgeColor(edgeColor);
	}
	
	public double distance(int x, int y) {
		double differenceInX = this.x - x;
		double differenceInY = this.y - y;
		return Math.sqrt(differenceInX * differenceInX + differenceInY * differenceInY);
	}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}
	
	public void moveBy(int byX, int byY) {
		this.x += byX;
		this.y += byY;
	}

	public void draw(Graphics graphics) {
		graphics.setColor(getEdgeColor());
		graphics.drawLine(this.x - 2, y, this.x + 2, y);
		graphics.drawLine(x, this.y - 2, x, this.y + 2);
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(this.x - 3, this.y - 3, 6, 6);
		}
	}

    public Point clone(Shape shape) {
	    Point point = new Point();
	    if (shape instanceof Point)
	    	point = (Point) shape;
	    point.setX(this.getX());
	    point.setY(this.getY());
	    point.setEdgeColor(getEdgeColor());
        return point;
    }
	
	public Point parse(String str) {
		String[] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("\\(")[1]);
		int y = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
		Color edgeColor = getColor(parts[2].split("=")[1]);
		boolean selectedState = Boolean.parseBoolean(parts[3].split("=")[1]);
		Point point = new Point(x, y, edgeColor);
		point.setSelected(selectedState);
		return point;
	}

	public int compareTo(Object obj) {
		if (obj instanceof Point)
			return (int) (this.distance(0, 0) - ((Point) obj).distance(0, 0));
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point point = (Point) obj;
			return this.x == point.getX() && this.y == point.getY();
		} else
			return false;
	}
	
	@Override
	public String toString() {
		return String.format("Point:("+x+","+y+")" + ",Edge-color=[%d-%d-%d],selected=%b", getEdgeColor().getRed(),
				getEdgeColor().getGreen(), getEdgeColor().getBlue(), isSelected());
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
