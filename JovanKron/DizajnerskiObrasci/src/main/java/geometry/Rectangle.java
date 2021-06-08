package geometry;

import java.awt.*;

public class Rectangle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int width, height;
	
	public Rectangle() {}

	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}
	
	public Rectangle(Point upperLeft, int width, int height, Color edgeColor, Color fillColor) {
		this(upperLeft, width, height);
		setEdgeColor(edgeColor);
		setFillColor(fillColor);
	}
	
	public boolean contains(int x, int y) {
		if (this.getUpperLeftPoint().getX() <= x && x <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= y && y <= this.getUpperLeftPoint().getY() + height)
			return true;
		else
			return false;
	}
	
	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);
	}

	public void draw(Graphics graphics) {
		graphics.setColor(getFillColor());
		graphics.fillRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.width, this.getHeight());
		graphics.setColor(getEdgeColor());
		graphics.drawRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.width, this.getHeight());
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3, 6, 6);
			graphics.drawRect(this.getUpperLeftPoint().getX() - 3 + getWidth(), this.getUpperLeftPoint().getY() - 3, 6, 6);
			graphics.drawRect(this.getUpperLeftPoint().getX() - 3, this.getUpperLeftPoint().getY() - 3 + getHeight(), 6, 6);
			graphics.drawRect(this.getUpperLeftPoint().getX() + getWidth() - 3, this.getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		}
	}
	
	public Rectangle clone(Shape shape) {
        Rectangle rectangle = new Rectangle(new Point(0,0), 0, 0);
        if(shape instanceof Rectangle)
        	rectangle = (Rectangle) shape;
        rectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
        rectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
        rectangle.setHeight(this.getHeight());
        rectangle.setWidth(this.getWidth());
        rectangle.setFillColor(getFillColor());
        rectangle.setEdgeColor(getEdgeColor());
        return rectangle;
    }
	
	public Rectangle parse(String str) {
		String [] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("\\(")[1]);
		int y = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
		int width = Integer.parseInt(parts[2].split("=")[1]);
		int height = Integer.parseInt(parts[3].split("=")[1]);
		Color edgeColor = getColor(parts[4].split("=")[1]);
		Color fillColor = getColor(parts[5].split("=")[1]);
		boolean selectedState = Boolean.parseBoolean(parts[6].split("=")[1]);
		Rectangle rectangle = new Rectangle(new Point(x,y), width, height, edgeColor, fillColor);
		rectangle.setSelected(selectedState);
		return rectangle;
	}

	public int compareTo(Object obj) {
		if (obj instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) obj).area());
		}
		return 0;
	}
	
	public double area() {
		return width * height;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) obj;
			return this.upperLeftPoint.equals(rectangle.getUpperLeftPoint()) && this.height == rectangle.getHeight()
					&& this.width == rectangle.getWidth();
		} else
			return false;
	}
	
	@Override
	public String toString() {
		return String.format("Rectangle:Upper-left(%d,%d),Width=%d,Height=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b",
				upperLeftPoint.getX(), upperLeftPoint.getY(), width, height, getEdgeColor().getRed(), getEdgeColor().getGreen(), getEdgeColor().getBlue(),
				getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), isSelected());
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
	
}
