package com.exigen.jschool.gui;

import com.exigen.jschool.setlocal.FormatException;
import com.exigen.jschool.setlocal.ParsedFile;
import com.exigen.jschool.setlocal.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OpenSaveResetPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    MainFrame frame;
    ParsedFile parsedFile;
    Parser parser;

    JPanel osrButtonPanel = new JPanel();

    JButton openButton = new JButton("Open");
    JButton saveButton = new JButton("Save");
    JButton resetButton = new JButton("Reset");

    JFileChooser jFileChooser = new JFileChooser();

    public OpenSaveResetPanel(int numTub, MainFrame frame_m) {

        frame = frame_m;

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parser = new Parser();
                try {
                    parsedFile = parser.parse("E:/ForBRAIN/JavaCources/SetLocal/1.txt", "UTF-8");
                    System.out.println();
                } catch (IOException exc) {
                    exc.printStackTrace();
                } catch (FormatException exc) {
                    exc.printStackTrace();
                }
                if (parsedFile != null) {
                    frame.displayTable(parsedFile);
                }

            }
        });

        osrButtonPanel.add(openButton);
        osrButtonPanel.add(resetButton);

        if (numTub == 1) {
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(parsedFile != null){
                        try {
                            parser.save(parsedFile);
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    }
                    frame.removeTable();

                }
            });
            osrButtonPanel.add(saveButton);
        }

        add(osrButtonPanel);

    }
}
