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

	@Override
	public void draw(Graphics gr) {
		Graphics2D g = (Graphics2D)gr;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getFillColor());
		g.fillOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(),
					this.getRadius() * 2, this.getRadius() * 2);
		g.setColor(getEdgeColor());
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
	public int compareTo(Object obj) {
		if (obj instanceof Circle)
			return (this.radius - ((Circle) obj).radius);
		return 0;
	}
	
	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			return this.center.equals(c.getCenter()) && this.radius == c.getRadius();
		} else
			return false;
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	public String toString() {
		return String.format("Circle:Center(%d,%d),radius=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b",
				center.getX(), center.getY(), radius, getEdgeColor().getRed(), getEdgeColor().getGreen(),getEdgeColor().getBlue(),
				getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), isSelected());
	}
	
	@Override
    public Circle clone(Shape s) {
        Circle c = new Circle(new Point(0,0), 0);
        if (s instanceof Circle)
        	c = (Circle) s;

        c.getCenter().setX(this.getCenter().getX());
        c.getCenter().setY(this.getCenter().getY());
        c.setRadius(this.getRadius());
        c.setFillColor(getFillColor());
        c.setEdgeColor(getEdgeColor());
        return c;
    }
	
	@Override
	public Circle parse(String str) {
		String [] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("\\(")[1]);
		int y = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
		int radius = Integer.parseInt(parts[2].split("=")[1]);
		Color edgeColor = getColor(parts[3].split("=")[1]);
		Color surfaceColor = getColor(parts[4].split("=")[1]);
		boolean selected = Boolean.parseBoolean(parts[5].split("=")[1]);
		Circle circle = new Circle(new Point(x,y), radius, edgeColor, surfaceColor);
		circle.setSelected(selected);
		return circle;
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
