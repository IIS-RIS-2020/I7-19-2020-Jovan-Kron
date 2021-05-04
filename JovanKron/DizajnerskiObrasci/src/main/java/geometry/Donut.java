package geometry;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	
	private int innerRadius;

	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, Color edgeColor, Color fillColor) {
		this(center, radius, innerRadius);
		setEdgeColor(edgeColor);
		setFillColor(fillColor);
	}
	
	public void draw(Graphics gr) {
		Graphics2D g = (Graphics2D)gr;

		g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Ellipse2D outer = new Ellipse2D.Double(
				this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(),
				this.getRadius() * 2, this.getRadius() * 2);
		Ellipse2D inner = new Ellipse2D.Double(
				this.getCenter().getX() - this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
				this.getInnerRadius() * 2, this.getInnerRadius() * 2);
		Area area = new Area(outer);
		area.subtract(new Area(inner));

		if (getFillColor() != null) {
			g.setColor(getFillColor());
			g.fill(area);
		}
		g.setColor(Color.BLACK);
		if (getEdgeColor() != null) {
			g.setColor(getEdgeColor());
		}
		g.draw(area);

		g.setColor(new Color(0, 0, 0));
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
			g.setColor(Color.BLACK);
		}
	}

	@Override
	public void setFillColor(Color fillColor) {
		super.setFillColor(fillColor);
	}

	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	/*
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}*/
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getRadius() == d.getRadius() &&
					innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
	    this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return "Donut " + super.toString() + " , inner radius = " + innerRadius;
	}
	
	 @Override
	 public Donut clone(Shape s) {
	        Donut d = new Donut(new Point(0,0), 0, 0);
	        if (s instanceof Donut) {
	        	d = (Donut) s;
			}

	        d.getCenter().setX(this.getCenter().getX());
	        d.getCenter().setY(this.getCenter().getY());
	        d.setRadius(this.getRadius());
	        d.setInnerRadius(this.getInnerRadius());
	        d.setEdgeColor(this.getEdgeColor());
	        d.setFillColor(this.getFillColor());

	        return d;
	 }
	 
	 public Donut parse(String str) {
			Donut d = new Donut(new Point(0,0), 0, 0);
			str = str.replaceAll("Donut Circle Center = ", "");

			d.setCenter(new Point().parse(str.split(" , ")[0] + " , " + str.split(" , ")[1]));
			d.setRadius(Integer.parseInt(str.split(" , ")[2].split(" = ")[1]));
			d.setInnerRadius(Integer.parseInt(str.split(" , ")[3].split(" = ")[1]));

			return d;
		}

}
