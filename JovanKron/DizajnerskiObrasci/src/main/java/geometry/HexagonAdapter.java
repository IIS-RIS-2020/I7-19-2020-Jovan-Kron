package geometry;

import geometry.Shape;
import geometry.SurfaceShape;
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
        this.hexagon = new Hexagon(x, y, r);
        this.setEdgeColor(edgeColor);
        this.setFillColor(fillColor);
    }

    @Override
    public boolean contains(int x, int y) {
        return this.hexagon.doesContain(x, y);
    }

    @Override
    public void draw(Graphics g) {
        this.hexagon.paint(g);
    }

    @Override
    public void moveBy(int byX, int byY) {
    	hexagon.setX(hexagon.getX() + byX);
		hexagon.setY(hexagon.getY() + byY);
    }

    @Override
    public int compareTo(Object obj) {
    	if (obj instanceof HexagonAdapter)
			return hexagon.getR() - ((HexagonAdapter) obj).getR();
		return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  HexagonAdapter) {
        	HexagonAdapter ha = (HexagonAdapter) obj;
        	return ha.getX() == this.getX() && ha.getY() == this.getY() && ha.getR() == this.getR();
        }
        return false;
    }

    @Override
    public double area() {
        return 0;
    }
    
    @Override
    public String toString() {
    	return String.format("Hexagon:(X=%d,Y=%d),r=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b", hexagon.getX(), hexagon.getY(), hexagon.getR(),
				hexagon.getBorderColor().getRed(),hexagon.getBorderColor().getGreen(),hexagon.getBorderColor().getBlue(),
				hexagon.getAreaColor().getRed(), hexagon.getAreaColor().getGreen(), hexagon.getAreaColor().getBlue(), isSelected());
    }
    

    @Override
    public Shape clone(Shape old) {
        HexagonAdapter ha = new HexagonAdapter(0, 0, 0);
        if (old instanceof HexagonAdapter)
            ha = (HexagonAdapter) old;
        ha.setX(this.getX());
        ha.setY(this.getY());
        ha.setR(this.getR());
        ha.setEdgeColor(this.getEdgeColor());
        ha.setFillColor(this.getFillColor());
        return ha;
    }
    
    @Override
    public HexagonAdapter parse(String str) {
    	String [] parts = str.split(",");
		int x = Integer.parseInt(parts[0].split("=")[1]);
		int y = Integer.parseInt(parts[1].split("=")[1].substring(0, parts[1].split("=")[1].length() - 1));
		int r = Integer.parseInt(parts[2].split("=")[1]);
		Color edgeColor = getColor(parts[3].split("=")[1]);
		Color surfaceColor = getColor(parts[4].split("=")[1]);
		boolean selected = Boolean.parseBoolean(parts[5].split("=")[1]);
		HexagonAdapter hexagonAdapter = new HexagonAdapter(new Hexagon(x, y, r), edgeColor, surfaceColor);
		hexagonAdapter.setSelected(selected);
		return hexagonAdapter;
    }
    
    @Override
    public boolean isSelected() {
        return hexagon.isSelected();
    }

    @Override
    public void setSelected(boolean selected) {
        this.hexagon.setSelected(selected);
        super.setSelected(selected);
    }
    
    public Color getEdgeColor() {
        return hexagon.getBorderColor();
    }
    
    public void setEdgeColor(Color c) {
        hexagon.setBorderColor(c);
    }
    
    public Color getFillColor() {
        return hexagon.getAreaColor();
    }

    public void setFillColor(Color c) {
        hexagon.setAreaColor(c);
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
    
    public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
}
