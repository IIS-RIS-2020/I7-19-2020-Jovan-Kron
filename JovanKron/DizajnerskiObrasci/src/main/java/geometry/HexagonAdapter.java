package geometry;

import geometry.Shape;
import geometry.SurfaceShape;
import hexagon.Hexagon;

import java.awt.*;

public class HexagonAdapter extends SurfaceShape {

    private Hexagon hexagon;

    public HexagonAdapter() {

    }

    public HexagonAdapter(int x, int y, int r) {
        this.hexagon = new Hexagon(x, y, r);
    }
    
    public HexagonAdapter(Hexagon hexagon,Color edgeColor, Color fillColor) {
    	this.hexagon = hexagon;
		setEdgeColor(edgeColor);
		setFillColor(fillColor);
	}
    
    public HexagonAdapter(int x, int y, int r, Color edgeColor, Color fillColor) {
        this.hexagon = new Hexagon(x, y, r);
        setEdgeColor(edgeColor);
		setFillColor(fillColor);
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
    public int compareTo(Object o) {
    	if (o instanceof HexagonAdapter) {
			return hexagon.getR() - ((HexagonAdapter) o).getR();
		}
		return 0;
    }

    public void setEdgeColor(Color c) {
        hexagon.setBorderColor(c);
    }

    public void setFillColor(Color c) {
        hexagon.setAreaColor(c);
    }

    @Override
    public double area() {
        return 0;
    }

    public void setX(int x) {
        hexagon.setX(x);
    }

    public void setY(int y) {
        hexagon.setY(y);
    }

    public void setR(int r) {
        hexagon.setR(r);
    }

    public Color getEdgeColor() {
        return hexagon.getBorderColor();
    }

    public Color getFillColor() {
        return hexagon.getAreaColor();
    }

    public int getX() {
        return hexagon.getX();
    }

    public int getY() {
        return hexagon.getY();
    }

    public int getR() {
        return  hexagon.getR();
    }
    
    public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
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

    @Override
    public Shape clone(Shape old) {
        HexagonAdapter ha = new HexagonAdapter(0, 0, 0);
        if (old instanceof HexagonAdapter) {
            ha = (HexagonAdapter) old;
        }
        ha.setX(this.getX());
        ha.setY(this.getY());
        ha.setR(this.getR());
        ha.setEdgeColor(this.getEdgeColor());
        ha.setFillColor(this.getFillColor());

        return ha;
    }

    @Override
    public boolean equals(Object obj) {
        HexagonAdapter ha = new HexagonAdapter(0, 0, 0);
        if (obj instanceof  HexagonAdapter) {
            ha = (HexagonAdapter) obj;
        }
        if (ha.getX() == this.getX() && ha.getY() == this.getY() && ha.getR() == this.getR()) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
    	return String.format("Hexagon:(X=%d,Y=%d),r=%d,Edge-color=[%d-%d-%d],Surface-color=[%d-%d-%d],selected=%b", hexagon.getX(), hexagon.getY(), hexagon.getR(),
				hexagon.getBorderColor().getRed(),hexagon.getBorderColor().getGreen(),hexagon.getBorderColor().getBlue(), hexagon.getAreaColor().getRed(), hexagon.getAreaColor().getGreen(),
				hexagon.getAreaColor().getBlue(), isSelected());
    }
    
    @Override
    public HexagonAdapter parse(String str) {
        HexagonAdapter ha = new HexagonAdapter(0, 0, 0);
        str = str.replaceAll("Hexagon ", "");

        ha.setX(Integer.parseInt(str.split(" , ")[0].split(" = ")[1]));
        ha.setY(Integer.parseInt(str.split(" , ")[1].split(" = ")[1]));
        ha.setR(Integer.parseInt(str.split(" , ")[2].split(" = ")[1]));

        return ha;
    }
}
