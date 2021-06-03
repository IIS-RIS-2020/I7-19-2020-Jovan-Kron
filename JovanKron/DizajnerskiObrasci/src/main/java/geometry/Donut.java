package geometry;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

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
	
	public void draw(Graphics gr) {
		Graphics2D g = (Graphics2D)gr;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Ellipse2D outer = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(),
				this.getCenter().getY() - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius(),
				this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius() * 2, this.getInnerRadius() * 2);
		Area area = new Area(outer);
		area.subtract(new Area(inner));
		g.setColor(getFillColor());
		g.fill(area);
		g.setColor(getEdgeColor());
		g.draw(area);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() + getInnerRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - getInnerRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getInnerRadius() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - getInnerRadius() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() + getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getRadius() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - getRadius() - 3, 6, 6);
		}
	}

	public int compareTo(Object obj) {
		if (obj instanceof Donut)
			return (int) (this.area() - ((Donut) obj).area());
		return 0;
	}
	
	public boolean contains(int x, int y) {
		double distanceFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && distanceFromCenter > innerRadius;
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			return this.getCenter().equals(d.getCenter()) && this.getRadius() == d.getRadius()
					&& innerRadius == d.getInnerRadius();
		} else
			return false;
	}
	
	public String toString() {
		return String.format("Donut:Center(%d,%d),innerradius=%d,radius=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b",
				getCenter().getX(),getCenter().getY(),innerRadius,getRadius(),getEdgeColor().getRed(), getEdgeColor().getGreen(), getEdgeColor().getBlue(),
				getFillColor().getRed(), getFillColor().getGreen(), getFillColor().getBlue(), isSelected());
	}
	
	@Override
	public Donut clone(Shape s) {
		Donut d = new Donut(new Point(0,0), 0, 0);
		if (s instanceof Donut)
			d = (Donut) s;
		
		d.getCenter().setX(this.getCenter().getX());
		d.getCenter().setY(this.getCenter().getY());
		d.setRadius(this.getRadius());
		d.setInnerRadius(this.getInnerRadius());
		d.setEdgeColor(this.getEdgeColor());
		d.setFillColor(this.getFillColor());
		return d;
	}
	
	public Donut parse(String str) {
		String [] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("\\(")[1]);
		int y = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
		int innerradius = Integer.parseInt(parts[2].split("=")[1]);
		int radius = Integer.parseInt(parts[3].split("=")[1]);
		Color edgeColor = getColor(parts[4].split("=")[1]);
		Color surfaceColor = getColor(parts[5].split("=")[1]);
		boolean selected = Boolean.parseBoolean(parts[6].split("=")[1]);
		Donut donut = new Donut(new Point(x,y), radius, innerradius, edgeColor, surfaceColor);
		donut.setSelected(selected);
		return donut;
	}
	 
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
	    this.innerRadius = innerRadius;
	}

}
