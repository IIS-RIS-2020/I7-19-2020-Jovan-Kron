package strategy;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import dialogs.OptionPane;
import dialogs.RealOptionPane;
import geometry.Shape;
import mvc.*;
import observer.ObserverForButtons;

public class SerializableFile implements AnyFile {
	private DrawingModel model;
	private DrawingFrame frame;
	private OptionPane optionPane = new RealOptionPane();
	
	public SerializableFile (DrawingModel model, DrawingFrame frame) {
		this.model=model;
		this.frame=frame;
	}
	
	public void saveFile(File file) {
		if(Files.exists(Paths.get(file.toString() + ".ser"))) { 
			optionPane.showMessageDialog(frame,"File with same name already exists");
			return;
		}
		try {
			 FileOutputStream theFile = new FileOutputStream(file + ".ser"); 
	         ObjectOutputStream outputStream = new ObjectOutputStream(theFile); 
	         outputStream.writeObject(model.getShapes()); 
	         outputStream.close(); 
	         theFile.close();
	         optionPane.showMessageDialog(null, "The serializable file was saved successfully");
		} catch(Exception e) {
				 System.out.println("Objects did not serialize");
		}
	}

	public void loadFile(File file) {
		if(!Files.exists(Paths.get(file.toString()))) { 
			optionPane.showMessageDialog(frame, "File does not exist");
			return;
		}
		String [] parts = null;
		String ext = "";
		if(file.getName().contains(".")) {
			parts = file.getName().split("\\.");
			ext = parts[parts.length - 1];
		} else {
			optionPane.showMessageDialog(frame, "File can't be loaded");
			return;
		}
		if(ext.equals("ser"))
			readSerializableFile(file);
		else
			optionPane.showMessageDialog(frame, "File has to be of type ser");
	}
	
	private void readSerializableFile(File file) {
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
	}
	
	public void setOptionPane(OptionPane optionPane) {
	    this.optionPane = optionPane;
	}
	
}
