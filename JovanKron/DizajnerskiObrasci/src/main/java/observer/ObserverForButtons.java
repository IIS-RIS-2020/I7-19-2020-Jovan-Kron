package observer;

import java.util.Observable;
import java.util.Observer;

import geometry.Shape;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class ObserverForButtons implements Observer  {

	private DrawingModel model;
	private DrawingFrame frame;
	
	public ObserverForButtons(DrawingModel model,DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		int numberOfSelectedShapes=0;
		
		for(Shape shape : model.getShapes()) {
			if(shape.isSelected())
				numberOfSelectedShapes++;
		}
		
		if(numberOfSelectedShapes == 0) {
			frame.getTglBtnDelete().setEnabled(false);
			frame.getTglBtnModify().setEnabled(false);
			
			frame.getTglBtnBringToBack().setEnabled(false);
			frame.getTglBtnBringToFront().setEnabled(false);
			frame.getTglBtnToFront().setEnabled(false);
			frame.getTglBtnToBack().setEnabled(false);
			
		}else if(numberOfSelectedShapes == 1) {
			frame.getTglBtnDelete().setEnabled(true);
			frame.getTglBtnModify().setEnabled(true);
			
			frame.getTglBtnBringToBack().setEnabled(true);
			frame.getTglBtnBringToFront().setEnabled(true);
			frame.getTglBtnToFront().setEnabled(true);
			frame.getTglBtnToBack().setEnabled(true);
			
		}else if(numberOfSelectedShapes > 1) {
			frame.getTglBtnDelete().setEnabled(true);
			frame.getTglBtnModify().setEnabled(false);
			
			frame.getTglBtnBringToBack().setEnabled(false);
			frame.getTglBtnBringToFront().setEnabled(false);
			frame.getTglBtnToFront().setEnabled(false);
			frame.getTglBtnToBack().setEnabled(false);
			
		}
		
	}

}
