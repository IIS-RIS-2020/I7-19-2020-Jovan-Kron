package geometry;

import hexagon.Hexagon;
import java.awt.*;

public class HexagonAdapter extends SurfaceShape {
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;

    public HexagonAdapter() {}

    public HexagonAdapter(int x, int y, int r) {
        this.hexagon = new Hexagon(x, y, r);
    }
    
    public HexagonAdapter(Hexagon hexagon,Color edgeColor, Color fillColor) {
    	this.hexagon = hexagon;
		this.setEdgeColor(edgeColor);
		this.setFillColor(fillColor);
	}
    
    public HexagonAdapter(int x, int y, int r, Color edgeColor, Color fillColor) {
        this(x, y, r);
        this.setEdgeColor(edgeColor);
        this.setFillColor(fillColor);
    }
    
    public boolean contains(int x, int y) {
        return this.hexagon.doesContain(x, y);
    }

    public void moveBy(int byX, int byY) {
    	hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);
    }

    public void draw(Graphics graphics) {
        this.hexagon.paint(graphics);
    }

    public Shape clone(Shape shape) {
        HexagonAdapter hexagonAdapter = new HexagonAdapter(0, 0, 0);
        if (shape instanceof HexagonAdapter)
        	hexagonAdapter = (HexagonAdapter) shape;
        hexagonAdapter.setX(this.getX());
        hexagonAdapter.setY(this.getY());
        hexagonAdapter.setR(this.getR());
        hexagonAdapter.setEdgeColor(this.getEdgeColor());
        hexagonAdapter.setFillColor(this.getFillColor());
        return hexagonAdapter;
    }
    
    public HexagonAdapter parse(String str) {
    	String [] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("=")[1]);
		int y = Integer.parseInt(parts[1].split("=")[1].substring(0, parts[1].split("=")[1].length() - 1));
		int r = Integer.parseInt(parts[2].split("=")[1]);
		Color edgeColor = getColor(parts[3].split("=")[1]);
		Color fillColor = getColor(parts[4].split("=")[1]);
		boolean selectedState = Boolean.parseBoolean(parts[5].split("=")[1]);
		HexagonAdapter hexagonAdapter = new HexagonAdapter(x, y, r, edgeColor, fillColor);
		hexagonAdapter.setSelected(selectedState);
		return hexagonAdapter;
    }
    
    public int compareTo(Object obj) {
    	if (obj instanceof HexagonAdapter)
			return hexagon.getR() - ((HexagonAdapter) obj).getR();
		return 0;
    }
    
    public double area() {
        return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  HexagonAdapter) {
        	HexagonAdapter hexagonAdapter = (HexagonAdapter) obj;
        	return hexagonAdapter.getX() == this.getX() && hexagonAdapter.getY() == this.getY()
        			&& hexagonAdapter.getR() == this.getR();
        }
        return false;
    }
    
    @Override
    public String toString() {
    	return String.format("Hexagon:(X=%d,Y=%d),r=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b", hexagon.getX(), hexagon.getY(), hexagon.getR(),
				hexagon.getBorderColor().getRed(),hexagon.getBorderColor().getGreen(),hexagon.getBorderColor().getBlue(),
				hexagon.getAreaColor().getRed(), hexagon.getAreaColor().getGreen(), hexagon.getAreaColor().getBlue(), isSelected());
    }    
    
    @Override
    public boolean isSelected() {
        return hexagon.isSelected();
    }

    @Override
    public void setSelected(boolean selectedState) {
        this.hexagon.setSelected(selectedState);
        super.setSelected(selectedState);
    }
    
    @Override
    public Color getEdgeColor() {
        return hexagon.getBorderColor();
    }
    
    @Override
    public void setEdgeColor(Color color) {
        hexagon.setBorderColor(color);
    }
    
    @Override
    public Color getFillColor() {
        return hexagon.getAreaColor();
    }

    @Override
    public void setFillColor(Color color) {
        hexagon.setAreaColor(color);
    }
    
    public int getX() {
        return hexagon.getX();
    }
    
    public void setX(int x) {
        hexagon.setX(x);
    }

    public int getY() {
        return hexagon.getY();
    }
    
    public void setY(int y) {
        hexagon.setY(y);
    }
    
    public int getR() {
        return  hexagon.getR();
    }

    public void setR(int r) {
        hexagon.setR(r);
    }
    
	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
}
