package strategy;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandsOpen implements Open {
    private String filename;
    @Override
    public Object open() {
        JButton open = new JButton();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(new FileNameExtensionFilter("txt file","txt"));
        if (chooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            filename = chooser.getSelectedFile().getName();
            File file = new File(filename);
            try {
                Scanner myReader = new Scanner(file);
                return myReader;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
