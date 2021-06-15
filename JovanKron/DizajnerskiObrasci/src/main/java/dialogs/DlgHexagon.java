package dialogs;

import java.awt.*;
import geometry.HexagonAdapter;
import geometry.Point;

public class DlgHexagon extends DlgCircle {
	private static final long serialVersionUID = 1L;

    public DlgHexagon() {
    	setTitle("Hexagon dialog");
    	setBounds(100, 100, 198, 300);
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
	
	public void fillForModify(HexagonAdapter hexagonAdapter) {
		Point hexagonCenter = new Point(hexagonAdapter.getX(), hexagonAdapter.getY());
		fillForModify(hexagonCenter, hexagonAdapter.getEdgeColor(), hexagonAdapter.getFillColor());
		getTxtRadius().setText(Integer.toString(hexagonAdapter.getR()));
	}

	public HexagonAdapter createHexagonAdapterFromInput() {
		Point basePoint = createBasePointFromInput();
		return new HexagonAdapter(basePoint.getX(), basePoint.getY(), getRadius(), getEdgeColor(), getFillColor());
	}
	
}
