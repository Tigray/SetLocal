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

    public void displayTable(ParsedFile parsedFile) {
        if (parsedFile != null) {

            JTable dataTable = new DataTable(parsedFile);
            parsedFile.initDataTable2();

            JPanel tablePanel = workPanel.getTablePanel();
            tablePanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.HORIZONTAL;

            dataTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
            JScrollPane scrollPane = new JScrollPane(dataTable);
            tablePanel.add(scrollPane, c);
        }

        validate();
    }

}
