package com.exigen.jschool.gui;

import com.exigen.jschool.setlocal.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OpenSaveResetPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    MainFrame frame;
//    ParsedFile parsedFile;
    Parser parser;

    JPanel osrButtonPanel = new JPanel();

    JButton openButton = new JButton("Open");
    JButton saveButton = new JButton("Save");
    JButton resetButton = new JButton("Reset");

    private int tubNum;

    JFileChooser jFileChooser = new JFileChooser();

    public OpenSaveResetPanel(int numTub, MainFrame frame_m) {

        frame = frame_m;
        tubNum = numTub;

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                switch (tubNum) {
                    case 1: {
                        if (fc.showOpenDialog(frame.getWorkPanel().getWorkPanel()) == JFileChooser.APPROVE_OPTION) {
                            parser = new Parser();
                            ParsedFile parsedFile = null;
                            try {
                                parsedFile = parser.parse(fc.getSelectedFile().getPath(), "UTF-8");
                                System.out.println();
                            } catch (IOException exc) {
                                exc.printStackTrace();
                            } catch (FormatException exc) {
                                exc.printStackTrace();
                            }
                            if (parsedFile != null) {
                                frame.getWorkPanel().displayTable(parsedFile);
                            }
                        }
                        break;
                    }
                    case 2: {
                        if (fc.showOpenDialog(frame.getWatchPanel().getWatchPanel()) == JFileChooser.APPROVE_OPTION) {
                            loadFile(fc.getSelectedFile().getPath());
                        }
                        break;
                    }
                    default:
                        break;
                }
            }

        });

        osrButtonPanel.add(openButton);
        osrButtonPanel.add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch(tubNum){
                    case 1:{
                        frame.getWorkPanel().removeTable();
                        break;
                    }
                    case 2:{
                        frame.getWatchPanel().removeWatch();
                        break;
                    }
                    case 3:{
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });

        if (numTub == 1) {
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ParsedFile parsedFile =  frame.getWorkPanel().getParsFile();
                    if (parsedFile != null) {
                        try {
                            parser.save(parsedFile);
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                    }
                    frame.getWorkPanel().removeTable();

                }
            });
            osrButtonPanel.add(saveButton);
        }

        add(osrButtonPanel);

    }


    private void loadFile(String filename) {
        ParsedFile parsedFile = null;
        Parser parser = new Parser();
        try {
            parsedFile = parser.parse(filename, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        if (parsedFile != null) {
            JPanel watchPanel = frame.getWatchPanel().getWatchPanel();
            for (Translation translation : parsedFile.getTranslations()) {
                JLabel section = new JLabel(String.format("[%s]%n", translation.getLang()));
                section.setForeground(Color.BLUE);
                section.setAlignmentX(Component.LEFT_ALIGNMENT);
                watchPanel.add(section);
                for (Line line : translation.getLines()) {
                    JPanel linePanel = new JPanel();
                    linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.LINE_AXIS));
                    JLabel key = new JLabel(line.getKey());
                    key.setForeground(Color.RED);
                    linePanel.add(key);
                    JLabel equal = new JLabel(" = ");
                    equal.setForeground(Color.BLACK);
                    linePanel.add(equal);
                    JLabel value = new JLabel(line.getValue());
                    value.setForeground(Color.ORANGE);
                    linePanel.add(value);
                    linePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    watchPanel.add(linePanel);
                }
            }
            frame.getWatchPanel().validate();
        }

//        getParent().repaint();
    }
}
