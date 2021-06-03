package geometry;

import java.awt.*;

public class Line extends Shape {

	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	
	public Line() {}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		setEndPoint(endPoint);
	}
	
	public Line(Point startPoint, Point endPoint, Color edgeColor) {
		this.startPoint = startPoint;
		setEndPoint(endPoint);
		setEdgeColor(edgeColor);
	}

	@Override
	public void draw(Graphics gr) {
		Graphics2D g = (Graphics2D)gr;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setColor(getEdgeColor());
		g.drawLine(this.getStartPoint().getX(), this.getStartPoint().getY(), this.getEndPoint().getX(),
					this.getEndPoint().getY());
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getStartPoint().getX() - 3, this.getStartPoint().getY() - 3, 6, 6);
			g.drawRect(this.getEndPoint().getX() - 3, this.getEndPoint().getY() - 3, 6, 6);
			g.drawRect(this.middleOfLine().getX() - 3, this.middleOfLine().getY() - 3, 6, 6);
		}
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Line)
			return (int) (this.length() - ((Line) obj).length());
		return 0;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}
	
	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getX() + this.getEndPoint().getX()) / 2;
		int middleByY = (this.getStartPoint().getY() + this.getEndPoint().getY()) / 2;
		return new Point(middleByX, middleByY);
	}
	
	public boolean contains(int x, int y) {
		return startPoint.distance(x, y) + endPoint.distance(x, y) - this.length() <= 0.05;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line l = (Line) obj;
			return this.startPoint.equals(l.getStartPoint()) && this.endPoint.equals(l.getEndPoint());
		} else
			return false;
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public String toString() {
		return String.format("Line:(%d,%d)-->(%d,%d),Edge-color=[%d-%d-%d],selected=%b", startPoint.getX(), startPoint.getY(),
				endPoint.getX(), endPoint.getY(), getEdgeColor().getRed(), getEdgeColor().getGreen(), getEdgeColor().getBlue(), isSelected());
	}
	
	@Override
    public Line clone(Shape s) {
        Line l = new Line(new Point(0, 0), new Point(0, 0));
        if (s instanceof Line)
            l = (Line) s;

        l.getStartPoint().setX(this.getStartPoint().getX());
        l.getStartPoint().setY(this.getStartPoint().getY());
        l.getEndPoint().setX(this.getEndPoint().getX());
        l.getEndPoint().setY(this.getEndPoint().getY());
        l.setEdgeColor(getEdgeColor());
        return l;
    }
	
	public Line parse(String str) {
		String [] parts = str.split(",");
		int startX = Integer.parseInt(parts[0].split("\\(")[1]);
		int startY = Integer.parseInt(parts[1].split("\\)")[0]);
		int endX = Integer.parseInt(parts[1].split("\\(")[1]);
		int endY = Integer.parseInt(parts[2].substring(0, parts[2].length() - 1));
		Color edgeColor = getColor(parts[3].split("=")[1]);
		boolean selected = Boolean.parseBoolean(parts[4].split("=")[1]);
		Line line = new Line(new Point(startX, startY), new Point(endX, endY), edgeColor);
		line.setSelected(selected);
		return line;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
}
