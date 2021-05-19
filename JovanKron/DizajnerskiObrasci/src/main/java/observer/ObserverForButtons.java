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
			frame.getTglbtnDelete().setEnabled(false);
			frame.getTglbtnModify().setEnabled(false);
			
			frame.getTglbtnBringToBack().setEnabled(false);
			frame.getTglbtnBringToFront().setEnabled(false);
			frame.getTglbtnToFront().setEnabled(false);
			frame.getTglbtnToBack().setEnabled(false);
			
		}else if(numberOfSelectedShapes == 1) {
			frame.getTglbtnDelete().setEnabled(true);
			frame.getTglbtnModify().setEnabled(true);
			
			frame.getTglbtnBringToBack().setEnabled(true);
			frame.getTglbtnBringToFront().setEnabled(true);
			frame.getTglbtnToFront().setEnabled(true);
			frame.getTglbtnToBack().setEnabled(true);
			
		}else if(numberOfSelectedShapes > 1) {
			frame.getTglbtnDelete().setEnabled(true);
			frame.getTglbtnModify().setEnabled(false);
			
			frame.getTglbtnBringToBack().setEnabled(false);
			frame.getTglbtnBringToFront().setEnabled(false);
			frame.getTglbtnToFront().setEnabled(false);
			frame.getTglbtnToBack().setEnabled(false);
			
		}
		
	}

}
