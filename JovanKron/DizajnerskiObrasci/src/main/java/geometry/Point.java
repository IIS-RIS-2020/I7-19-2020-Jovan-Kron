package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	
	public Point() {}
	
	public Point(int x, int y) {
		this.x = x;
		setY(y);
	}
	
	public Point(int x, int y, Color edgeColor) {
		this(x, y);
		setEdgeColor(edgeColor);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getEdgeColor());
		g.drawLine(this.x-2, y, this.x+2, y);
		g.drawLine(x, this.y-2, x, this.y+2);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x-3, this.y-3, 6, 6);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y += byY;
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Point) {
			Point start = new Point(0, 0);
			return (int) (this.distance(start.getX(), start.getY()) - ((Point) obj).distance(start.getX(), start.getY()));
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			return this.x == p.getX() && this.y == p.getY();
		} else
			return false;
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public String toString() {
		return String.format("Point:("+x+","+y+")" + ",Edge-color=[%d-%d-%d],selected=%b", getEdgeColor().getRed(),
				getEdgeColor().getGreen(), getEdgeColor().getBlue(), isSelected());
	}
	
	@Override
    public Point clone(Shape s) {
	    Point p = new Point();
	    if (s instanceof Point)
            p = (Point) s;
        p.setX(this.getX());
        p.setY(this.getY());
        p.setEdgeColor(getEdgeColor());
        return p;
    }

	public Point parse(String str) {
		String [] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("\\(")[1]);
		int y = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
		Color edgeColor = getColor(parts[2].split("=")[1]);
		boolean selected = Boolean.parseBoolean(parts[3].split("=")[1]);
		Point point = new Point(x, y, edgeColor);
		point.setSelected(selected);
		return point;
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
