package com.upgrade.helpers;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileHelper {
    public static File getFileFromChooser(File lastFile, String fileExtension) {
        JFileChooser chooser = new JFileChooser();
        File file = null;



        chooser.setMultiSelectionEnabled(false);
        chooser.setSize(400,600);

        if(fileExtension != null) {
            chooser.setDialogTitle("Choose " + fileExtension.toUpperCase() + " File Location:");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    fileExtension.toUpperCase() + " Files", fileExtension.toUpperCase(),
                    fileExtension.toLowerCase());
            chooser.setFileFilter(filter);
        }
        else {
            chooser.setDialogTitle("Choose Folder Location:");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }

        if (lastFile != null && lastFile.exists()) {
            if(fileExtension != null && !lastFile.equals("")) chooser.setSelectedFile(lastFile);
            else chooser.setCurrentDirectory(lastFile);
        }

        int choice = chooser.showOpenDialog(null);

        if(choice == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }

        if(file == null) {
            System.out.println("No file selected! Aborting battle program.");
            System.exit(0);
        }

        return file;
    }
}
