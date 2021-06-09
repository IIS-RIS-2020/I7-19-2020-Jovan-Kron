package strategy;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import geometry.Shape;
import mvc.*;
import observer.ObserverForButtons;

public class SerializableFile implements AnyFile {
	private DrawingModel model;
	private DrawingFrame frame;
	
	public SerializableFile (DrawingModel model,DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}
	
	public void saveFile(File file) {
		if(Files.exists(Paths.get(file.toString() + ".ser"))) { 
			JOptionPane.showMessageDialog(frame,"File with same name already exists");
			return;
		}
		try {
			 FileOutputStream theFile = new FileOutputStream(file + ".ser"); 
	         ObjectOutputStream outputStream = new ObjectOutputStream(theFile); 
	         outputStream.writeObject(model.getShapes()); 
	         outputStream.close(); 
	         theFile.close();
	         JOptionPane.showMessageDialog(null, "The serializable file was saved successfully");
		} catch(Exception e) {
				 System.out.println("Objects did not serialize");
		}
	}

	public void loadFile(File file) {
		if(!Files.exists(Paths.get(file.toString()))) { 
			JOptionPane.showMessageDialog(frame,"File does not exist");
			return;
		}
		String [] parts = null;
		String ext = "";
		if(file.getName().contains(".")) {
			parts = file.getName().split("\\.");
			ext = parts[parts.length - 1];
		} else {
			JOptionPane.showMessageDialog(frame,"File can't be loaded");
			return;
		}
		if(ext.equals("ser")) {
			try {
				FileInputStream theFile = new FileInputStream(file); 
		        ObjectInputStream inputStream = new ObjectInputStream(theFile);
				@SuppressWarnings("unchecked")
				ArrayList<Shape> shapes = (ArrayList<Shape>) inputStream.readObject();
		        for (Shape shape : shapes) {
		        	if(shape != null) {
		        		shape.addObserver(new ObserverForButtons(model, frame));
		        		shape.setSelected(false);
		        		model.add(shape);
		        	}
				}
		        inputStream.close(); 
		        theFile.close(); 
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Objects did not deserialize");
			}
		} else {
			JOptionPane.showMessageDialog(frame,"File has to be of type ser");
		}		
	}
	
}
