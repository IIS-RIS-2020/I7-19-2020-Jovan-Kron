package geometry;

import java.awt.*;

public abstract class SurfaceShape extends Shape{

	private static final long serialVersionUID = 1L;
	private Color fillColor;

    public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public abstract double area();
}
