package com.exigen.jschool.gui;

import com.exigen.jschool.parser.FormatException;
import com.exigen.jschool.parser.ParsedFile;
import com.exigen.jschool.parser.Parser;

import javax.swing.JFrame;
import java.io.IOException;
import java.util.LinkedList;

public class SetLocal {

	public static void main(String[] args) {
        LinkedList list = new LinkedList();
//		MainFrame  frame = new MainFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
        Parser parser = new Parser();
        try {
            ParsedFile parsedFile = parser.parse("E:/ForBRAIN/IdeaProjects/SetLocal/testFiles/test.properties", "UTF-16");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
	
}
