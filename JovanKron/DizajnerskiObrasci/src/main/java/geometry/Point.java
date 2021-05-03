package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private int x;
	private int y;
	private Color color;
	
	public Point() {
		
	}
	
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
		g.setColor(color);
		g.drawLine(this.x-2, y, this.x+2, y);
		g.drawLine(x, this.y-2, x, this.y+2);
		g.setColor(new Color(0, 0, 0));
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
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point start = new Point(0, 0);
			return (int) (this.distance(start.getX(), start.getY()) - ((Point) o).distance(start.getX(), start.getY()));
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			if (this.x == p.getX() &&
					this.y == p.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx*dx + dy*dy);
		return d;
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
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public String toString() {
		return "Point ( " + x + " , " + y + " )";
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public Point clone(Shape s) {
	    Point p = new Point();
	    if (s instanceof Point) {
            p = (Point) s;
        }

        p.setX(this.getX());
        p.setY(this.getY());
        p.setColor(this.getColor());

        return p;
    }
	
	public Point parse(String str) {
		Point p = new Point();
		p.setX(Integer.parseInt(str.split(" ")[2]));
		p.setY(Integer.parseInt(str.split(" ")[4]));

		return p;
	}
}
