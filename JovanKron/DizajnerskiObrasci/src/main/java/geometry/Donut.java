package geometry;

import java.awt.*;
import java.awt.geom.*;

import geometry.Point;

public class Donut extends Circle {
	private static final long serialVersionUID = 1L;
	private int innerRadius;

	public Donut() {}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, Color edgeColor, Color fillColor) {
		this(center, radius, innerRadius);
		setEdgeColor(edgeColor);
		setFillColor(fillColor);
	}
	
	@Override
	public boolean contains(int x, int y) {
		double distanceFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && distanceFromCenter > innerRadius;
	}
	
	@Override
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Ellipse2D outer = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(),
				this.getCenter().getY() - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius(),
				this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius() * 2, this.getInnerRadius() * 2);
		Area area = new Area(outer);
		area.subtract(new Area(inner));
		graphics2D.setColor(getFillColor());
		graphics2D.fill(area);
		graphics2D.setColor(getEdgeColor());
		graphics2D.draw(area);
		if (isSelected()) {
			graphics2D.setColor(Color.BLUE);
			graphics2D.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			graphics2D.drawRect(this.getCenter().getX() + getInnerRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			graphics2D.drawRect(this.getCenter().getX() - getInnerRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			graphics2D.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getInnerRadius() - 3, 6, 6);
			graphics2D.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - getInnerRadius() - 3, 6, 6);
			graphics2D.drawRect(this.getCenter().getX() + getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			graphics2D.drawRect(this.getCenter().getX() - getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			graphics2D.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getRadius() - 3, 6, 6);
			graphics2D.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - getRadius() - 3, 6, 6);
		}
	}
	
	@Override
	public Donut clone(Shape shape) {
		Donut donut = new Donut(new Point(0,0), 0, 0);
		if (shape instanceof Donut)
			donut = (Donut) shape;
		donut.getCenter().setX(this.getCenter().getX());
		donut.getCenter().setY(this.getCenter().getY());
		donut.setRadius(this.getRadius());
		donut.setInnerRadius(this.getInnerRadius());
		donut.setEdgeColor(this.getEdgeColor());
		donut.setFillColor(this.getFillColor());
		return donut;
	}
	
	@Override
	public Donut parse(String str) {
		String [] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("\\(")[1]);
		int y = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
		int innerradius = Integer.parseInt(parts[2].split("=")[1]);
		int radius = Integer.parseInt(parts[3].split("=")[1]);
		Color edgeColor = getColor(parts[4].split("=")[1]);
		Color fillColor = getColor(parts[5].split("=")[1]);
		boolean selectedState = Boolean.parseBoolean(parts[6].split("=")[1]);
		Donut donut = new Donut(new Point(x,y), radius, innerradius, edgeColor, fillColor);
		donut.setSelected(selectedState);
		return donut;
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Donut)
			return (int) (this.area() - ((Donut) obj).area());
		return 0;
	}
	
	@Override
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			return this.getCenter().equals(d.getCenter()) && this.getRadius() == d.getRadius()
					&& innerRadius == d.getInnerRadius();
		} else
			return false;
	}
	
	@Override
	public String toString() {
		return String.format("Donut:Center(%d,%d),innerradius=%d,radius=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b",
				getCenter().getX(),getCenter().getY(),innerRadius,getRadius(),getEdgeColor().getRed(), getEdgeColor().getGreen(), getEdgeColor().getBlue(),
				getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), isSelected());
	}
	 
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
	    this.innerRadius = innerRadius;
	}

}
