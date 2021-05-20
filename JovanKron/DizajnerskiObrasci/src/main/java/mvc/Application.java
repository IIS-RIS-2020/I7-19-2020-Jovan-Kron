package mvc;

import java.awt.Color;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
        DrawingFrame frame = new DrawingFrame();
        frame.getView().setModel(model);
        DrawingController controller = new DrawingController(model, frame);
        frame.setController(controller);
        frame.setTitle("Dejan Tosenberger IT21/2017");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //removes toolbar, but sizes correctly and is fixed
        //frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);
        
	}
}
