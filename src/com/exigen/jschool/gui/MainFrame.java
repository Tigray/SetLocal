package com.exigen.jschool.gui;

import com.exigen.jschool.setlocal.DataTable;
import com.exigen.jschool.setlocal.ParsedFile;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

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

    private JTabbedPane tabbedPane = new JTabbedPane();

    private WorkPanel workPanel = new WorkPanel(this);
    private WatchPanel watchPanel = new WatchPanel(this);
    private ComparePanel comparePanel = new ComparePanel(this);

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

    public WorkPanel getWorkPanel(){
        return workPanel;
    }

    public WatchPanel getWatchPanel(){
        return watchPanel;
    }

    public ComparePanel getComparePanel(){
        return comparePanel;
    }
}
