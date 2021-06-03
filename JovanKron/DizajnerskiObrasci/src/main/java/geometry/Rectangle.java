package geometry;

import java.awt.*;

public class Rectangle extends SurfaceShape {

	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int width;
	private int height;
	
	public Rectangle() {}

	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		setHeight(height);
		setWidth(width);
	}
	
	public Rectangle(Point upperLeft, int width, int height, Color edgeColor, Color fillColor) {
		this(upperLeft, width, height);
		setEdgeColor(edgeColor);
		setFillColor(fillColor);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(this.getUpperLeftPoint().getX(), this.getUpperLeftPoint().getY(), this.width, this.getHeight());
		g.setColor(getEdgeColor());
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
	public int compareTo(Object obj) {
		if (obj instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) obj).area());
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		if (this.getUpperLeftPoint().getX() <= x && x <= this.getUpperLeftPoint().getX() + width
				&& this.getUpperLeftPoint().getY() <= y && y <= this.getUpperLeftPoint().getY() + height)
			return true;
		else
			return false;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			return this.upperLeftPoint.equals(r.getUpperLeftPoint()) && this.height == r.getHeight() && this.width == r.getWidth();
		} else
			return false;
	}
	
	public double area() {
		return width * height;
	}
	
	public String toString() {
		return String.format("Rectangle:Upper-left(%d,%d),Width=%d,Height=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b",
				upperLeftPoint.getX(), upperLeftPoint.getY(), width, height, getEdgeColor().getRed(), getEdgeColor().getGreen(), getEdgeColor().getBlue(),
				getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), isSelected());
	}
	
	@Override
    public Rectangle clone(Shape s) {
        Rectangle r = new Rectangle(new Point(0,0), 0, 0);
        if(s instanceof Rectangle)
        	r = (Rectangle) s;
        r.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
        r.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
        r.setHeight(this.getHeight());
        r.setWidth(this.getWidth());
        r.setFillColor(getFillColor());
        r.setEdgeColor(getEdgeColor());
        return r;
    }
	
	@Override
	public Rectangle parse(String str) {
		String [] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("\\(")[1]);
		int y = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
		int width = Integer.parseInt(parts[2].split("=")[1]);
		int height = Integer.parseInt(parts[3].split("=")[1]);
		Color edgeColor = getColor(parts[4].split("=")[1]);
		Color surfaceColor = getColor(parts[5].split("=")[1]);
		boolean selected = Boolean.parseBoolean(parts[6].split("=")[1]);
		Rectangle rectangle = new Rectangle(new Point(x,y), width, height, edgeColor, surfaceColor);
		rectangle.setSelected(selected);
		return rectangle;
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
