package geometry;

import java.awt.*;


public class Circle extends SurfaceShape {
	private Point center;
	private int radius;
	private Color edgeColor;
	private Color fillColor;
	
	public Circle() {
 
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	/*
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}*/
	
	public Circle(Point center, int radius, Color edgeColor, Color fillColor) {
		this(center, radius);
		setEdgeColor(edgeColor);
		setFillColor(fillColor);
	}

	@Override
	public void draw(Graphics gr) {
		Graphics2D g = (Graphics2D)gr;

		g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(fillColor);
		g.fillOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(),
					this.getRadius() * 2, this.getRadius() * 2);
		g.setColor(edgeColor);
		g.drawOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(),
				this.getRadius() * 2, this.getRadius() * 2);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3 - getRadius(), getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3 + getRadius(), getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 + getRadius() , 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 - getRadius(), 6, 6);
		}
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);	
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (this.radius - ((Circle) o).radius);
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius;
	}
	/*
	public boolean contains(Point p) {
		return p.distance(getCenter().getX(), getCenter().getY()) <= radius;
	}*/
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			if (this.center.equals(c.getCenter()) && this.radius == c.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double area() {
		return radius * radius * Math.PI;
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
		return String.format("Circle:Center(%d,%d),radius=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b",
				center.getX(),center.getY(),radius,getEdgeColor().getRed(), getEdgeColor().getGreen(), getEdgeColor().getBlue(), getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), isSelected());
	}
	
	@Override
    public Circle clone(Shape s) {
        Circle c = new Circle(new Point(0,0), 0);
        if (s instanceof Circle) {
        	c = (Circle) s;
		}

        c.getCenter().setX(this.getCenter().getX());
        c.getCenter().setY(this.getCenter().getY());
        c.setRadius(this.getRadius());
        c.setFillColor(this.getFillColor());
        c.setEdgeColor(this.getEdgeColor());

        return c;
    }
	
	@Override
	public Circle parse(String str) {
		Circle c = new Circle(new Point(0,0), 0);
		str = str.replaceAll("Circle Center = ", "");

		c.setCenter(new Point().parse(str.split(" , ")[0] + " , " + str.split(" , ")[1]));
		c.setRadius(Integer.parseInt(str.split(" , ")[2].split(" = ")[1]));

		return c;
	}
}
