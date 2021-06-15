package geometry;

import java.awt.*;

public class Circle extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Point center;
	private int radius;
	
	public Circle() {}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, Color edgeColor, Color fillColor) {
		this(center, radius);
		setEdgeColor(edgeColor);
		setFillColor(fillColor);
	}

	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius;
	}
	
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);	
	}
	
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setColor(getFillColor());
		graphics2D.fillOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(),
					this.getRadius() * 2, this.getRadius() * 2);
		graphics2D.setColor(getEdgeColor());
		graphics2D.drawOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(),
					this.getRadius() * 2, this.getRadius() * 2);
		if (isSelected()) {
			graphics2D.setColor(Color.BLUE);
			graphics2D.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			graphics2D.drawRect(getCenter().getX() - 3 - getRadius(), getCenter().getY() - 3, 6, 6);
			graphics2D.drawRect(getCenter().getX() - 3 + getRadius(), getCenter().getY() - 3, 6, 6);
			graphics2D.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 + getRadius() , 6, 6);
			graphics2D.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 - getRadius(), 6, 6);
		}
	}
	
	public Circle clone(Shape shape) {
        Circle circle = new Circle(new Point(0,0), 0);
        if (shape instanceof Circle)
        	circle = (Circle) shape;
        circle.getCenter().setX(this.getCenter().getX());
        circle.getCenter().setY(this.getCenter().getY());
        circle.setRadius(this.getRadius());
        circle.setFillColor(getFillColor());
        circle.setEdgeColor(getEdgeColor());
        return circle;
    }
	
	public Circle parse(String str) {
		String [] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("\\(")[1]);
		int y = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
		int radius = Integer.parseInt(parts[2].split("=")[1]);
		Color edgeColor = getColor(parts[3].split("=")[1]);
		Color fillColor = getColor(parts[4].split("=")[1]);
		boolean selectedState = Boolean.parseBoolean(parts[5].split("=")[1]);
		Circle circle = new Circle(new Point(x,y), radius, edgeColor, fillColor);
		circle.setSelected(selectedState);
		return circle;
	}
	
	public int compareTo(Object obj) {
		if (obj instanceof Circle)
			return (this.radius - ((Circle) obj).radius);
		return 0;
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle circle = (Circle) obj;
			return this.center.equals(circle.getCenter()) && this.radius == circle.getRadius();
		} else
			return false;
	}
	
	@Override
	public String toString() {
		return String.format("Circle:Center(%d,%d),radius=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b",
				center.getX(), center.getY(), radius, getEdgeColor().getRed(), getEdgeColor().getGreen(),getEdgeColor().getBlue(),
				getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), isSelected());
	}    
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point center) {
		this.center = center;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
        this.radius = radius;
    }
	
}
