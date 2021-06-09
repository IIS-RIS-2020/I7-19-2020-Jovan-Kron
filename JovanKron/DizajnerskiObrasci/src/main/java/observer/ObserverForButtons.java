package observer;

import java.util.*;
import geometry.Shape;
import mvc.*;

public class ObserverForButtons implements Observer  {
	private DrawingModel model;
	private DrawingFrame frame;
	
	public ObserverForButtons(DrawingModel model,DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}
	
	public void update(Observable observable, Object arg) {
		int numberOfSelectedShapes = 0;
		for(Shape shape : model.getShapes()) {
			if(shape.isSelected())
				numberOfSelectedShapes++;
		}
		if (numberOfSelectedShapes == 0)
			frame.setAllShapeManipultationButtonsState(false);			
		else if (numberOfSelectedShapes == 1)
			frame.setAllShapeManipultationButtonsState(true);
		else if (numberOfSelectedShapes > 1) {
			frame.setAllShapeManipultationButtonsState(false);
			frame.getTglBtnDelete().setEnabled(true);
		}
	}

}
