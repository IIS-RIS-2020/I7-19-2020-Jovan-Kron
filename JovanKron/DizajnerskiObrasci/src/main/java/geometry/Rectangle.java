package geometry;

import java.awt.*;

public class Rectangle extends SurfaceShape {
	private Point upperLeftPoint;
	private int width;
	private int height;
	private Color edgeColor;
	private Color fillColor;
	
	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		setHeight(height);
		setWidth(width);
	}

	/*public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this(upperLeftPoint, height, width);
		setSelected(selected);
	}*/
	
	public Rectangle(Point upperLeft, int width, int height, Color edgeColor, Color fillColor) {
		this(upperLeft, width, height);
		setEdgeColor(edgeColor);
		setFillColor(fillColor);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(fillColor);
		g.fillRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.width, this.getHeight());
		g.setColor(edgeColor);
		g.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.width, this.getHeight());
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3 + getWidth(), this.getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3 + getHeight(), 6, 6);
			g.drawRect(this.getUpperLeftPoint().getX() + getWidth() - 3, this.getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);

	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		if (this.getUpperLeftPoint().getX() <= x 
				&& x <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= y
				&& y <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	/*
	public boolean contains(Point p) {
		if (this.getUpperLeftPoint().getX() <= p.getX() 
				&& p.getX() <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= p.getY()
				&& p.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}*/

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			if (this.upperLeftPoint.equals(r.getUpperLeftPoint()) && this.height == r.getHeight()
					&& this.width == r.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double area() {
		return width * height;
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public String toString() {
		return String.format("Rectangle:Upper-left(%d,%d),Width=%d,Height=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b",upperLeftPoint.getX(),upperLeftPoint.getY(),width,height,
				getEdgeColor().getRed(), getEdgeColor().getGreen(), getEdgeColor().getBlue(), getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), isSelected());
	}
	
	@Override
    public Rectangle clone(Shape s) {

        Rectangle r = new Rectangle(new Point(0,0), 0, 0);

        if(s instanceof Rectangle) {
        	r = (Rectangle) s;
		}

        r.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
        r.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
        r.setHeight(this.getHeight());
        r.setWidth(this.getWidth());
        r.setFillColor(this.getFillColor());
        r.setEdgeColor(this.getEdgeColor());

        return r;
    }
	
	@Override
	public Rectangle parse(String str) {
		Rectangle r = new Rectangle(new Point(0,0), 0, 0);
		str = str.replaceAll("Rectangle Upper Left ", "");

		r.setUpperLeftPoint(new Point().parse(str.split(" , ")[0] + " , " + str.split(" , ")[1]));
		r.setHeight(Integer.parseInt(str.split(" , ")[2].split(" = ")[1]));
		r.setWidth(Integer.parseInt(str.split(" , ")[3].split(" = ")[1]));

		return r;
	}
}
