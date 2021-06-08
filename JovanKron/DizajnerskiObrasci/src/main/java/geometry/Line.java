package geometry;

import java.awt.*;

public class Line extends Shape {
	private static final long serialVersionUID = 1L;
	private Point startPoint, endPoint;
	
	public Line() {}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, Color edgeColor) {
		this(startPoint, endPoint);
		setEdgeColor(edgeColor);
	}
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getX() + this.getEndPoint().getX()) / 2;
		int middleByY = (this.getStartPoint().getY() + this.getEndPoint().getY()) / 2;
		return new Point(middleByX, middleByY);
	}
	
	public boolean contains(int x, int y) {
		return startPoint.distance(x, y) + endPoint.distance(x, y) - this.length() <= 0.05;
	}
	
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}
	
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setColor(getEdgeColor());
		graphics2D.drawLine(this.getStartPoint().getX(), this.getStartPoint().getY(), this.getEndPoint().getX(), this.getEndPoint().getY());
		if (isSelected()) {
			graphics2D.setColor(Color.BLUE);
			graphics2D.drawRect(this.getStartPoint().getX() - 3, this.getStartPoint().getY() - 3, 6, 6);
			graphics2D.drawRect(this.getEndPoint().getX() - 3, this.getEndPoint().getY() - 3, 6, 6);
			graphics2D.drawRect(this.middleOfLine().getX() - 3, this.middleOfLine().getY() - 3, 6, 6);
		}
	}
	
	public Line clone(Shape shape) {
        Line line = new Line(new Point(0, 0), new Point(0, 0));
        if (shape instanceof Line)
            line = (Line) shape;
        line.getStartPoint().setX(this.getStartPoint().getX());
        line.getStartPoint().setY(this.getStartPoint().getY());
        line.getEndPoint().setX(this.getEndPoint().getX());
        line.getEndPoint().setY(this.getEndPoint().getY());
        line.setEdgeColor(getEdgeColor());
        return line;
    }
	
	public Line parse(String str) {
		String [] parts = str.split(",");
		int startX = Integer.parseInt(parts[0].split("\\(")[1]);
		int startY = Integer.parseInt(parts[1].split("\\)")[0]);
		int endX = Integer.parseInt(parts[1].split("\\(")[1]);
		int endY = Integer.parseInt(parts[2].substring(0, parts[2].length() - 1));
		Color edgeColor = getColor(parts[3].split("=")[1]);
		boolean selectedState = Boolean.parseBoolean(parts[4].split("=")[1]);
		Line line = new Line(new Point(startX, startY), new Point(endX, endY), edgeColor);
		line.setSelected(selectedState);
		return line;
	}

	public int compareTo(Object obj) {
		if (obj instanceof Line)
			return (int) (this.length() - ((Line) obj).length());
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line) obj;
			return this.startPoint.equals(line.getStartPoint()) && this.endPoint.equals(line.getEndPoint());
		} else
			return false;
	}
	
	@Override
	public String toString() {
		return String.format("Line:(%d,%d)-->(%d,%d),Edge-color=[%d-%d-%d],selected=%b", startPoint.getX(), startPoint.getY(),
				endPoint.getX(), endPoint.getY(), getEdgeColor().getRed(), getEdgeColor().getGreen(), getEdgeColor().getBlue(), isSelected());
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
