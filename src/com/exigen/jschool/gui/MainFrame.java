package com.exigen.jschool.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final String WORK_TAB = "Work";
	private static final String WATCH_TAB = "Watch";
	private static final String COMPARE_TAB = "Compare";
	private static final String TITLE = "SetLocal";
	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 500;
	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	WorkPanel workPanel = new WorkPanel();
	WatchPanel watchPanel = new WatchPanel();
	ComparePanel comparePanel = new ComparePanel();
	
	public MainFrame() {
		super();
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle(TITLE);
		setResizable(false);
		
		tabbedPane.addTab(WORK_TAB, workPanel);
		tabbedPane.addTab(WATCH_TAB, watchPanel);
		tabbedPane.addTab(COMPARE_TAB, comparePanel);
		
		add(tabbedPane);
	}

    public void displayData(){
        validate();
    }
	
}
