package mvc;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
        DrawingFrame frame = new DrawingFrame();
        frame.getView().setModel(model);
        frame.getList().setModel(model.getListModel());
        DrawingController controller = new DrawingController(model, frame);
        frame.setController(controller);
        model.addPropertyChangeListener(frame);
        frame.setTitle("Dejan Tosenberger IT21/2017");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
