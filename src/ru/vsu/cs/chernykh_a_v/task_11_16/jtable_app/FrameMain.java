package ru.vsu.cs.chernykh_a_v.task_11_16.jtable_app;

import ru.vsu.cs.chernykh_a_v.task_11_16.main_logic.MainLogicClass;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FrameMain extends JFrame{
    private JPanel panel;
    private JButton buttonUpdate;
    private JButton buttonWriteFile;
    private JTextArea textAreaInput;
    private JTextArea textAreaOutput;
    private JButton buttonReadFile;

    public FrameMain() throws IOException {

        this.setTitle("Main program");
        this.setPreferredSize(new Dimension(500,500));
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        JFileChooser fileChooserOpen = new JFileChooser();
        JFileChooser fileChooserSave = new JFileChooser();


        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        JFileChooser finalFileChooserOpen = fileChooserOpen;
        buttonReadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (finalFileChooserOpen.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                        String data = MainLogicClass.readAllLinesFromFile(finalFileChooserOpen.getSelectedFile().getPath());
                        textAreaInput.append(data);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String data = textAreaInput.getText();
                    String answer = MainLogicClass.deleteCommasFromFileText(data);
                    textAreaOutput.setText(answer);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        JFileChooser finalFileChooserSave = fileChooserSave;
        buttonWriteFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (finalFileChooserSave.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
                        String path = finalFileChooserSave.getSelectedFile().getPath();
                        String answer = textAreaOutput.getText();
                        MainLogicClass.writeUpdateDataInFile(answer,path);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
