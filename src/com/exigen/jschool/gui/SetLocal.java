package com.exigen.jschool.gui;

import com.exigen.jschool.setlocal.FormatException;
import com.exigen.jschool.setlocal.ParsedFile;
import com.exigen.jschool.setlocal.Parser;

import javax.swing.JFrame;
import java.io.IOException;

public class SetLocal {

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Parser parser = new Parser();
        ParsedFile parsedFile = null;
        try {
            parsedFile = parser.parse("E:/ForBRAIN/JavaCources/SetLocal/1.txt", "UTF-8");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        if (parsedFile != null) {
            frame.displayTable(parsedFile);
        }


    }
	
}
