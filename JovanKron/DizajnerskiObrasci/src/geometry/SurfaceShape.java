package geometry;

import java.awt.*;

public abstract class SurfaceShape extends Shape{
    private Color fillColor;

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public abstract double area();
}
