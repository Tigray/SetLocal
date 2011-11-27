package com.exigen.jschool.gui;

import com.exigen.jschool.setlocal.DataTable;
import com.exigen.jschool.setlocal.ParsedFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WorkPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel tablePanel = new JPanel();
	private JPanel keyButtonPanel = new JPanel();
	private OpenSaveResetPanel osrPanel;
	private JPanel filterPanel = new JPanel();
	
	private JButton addKeyButton = new JButton("Add Key");
	private JButton deleteKeyButton = new JButton("Delete Key");

	private JButton filterEmptyButton = new JButton("Empty");
	private JButton filterCollisionButton = new JButton("Collision");
	private JButton filterLangButton = new JButton("Languages");

    private JTable dataTable;
    private ParsedFile parsFile;

	public WorkPanel(MainFrame frame) {
		 initPanel(frame);
	}

    public  ParsedFile getParsFile(){
        return parsFile;
    }

    private void initPanel(MainFrame frame){
        osrPanel = new OpenSaveResetPanel(1, frame);
        setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;

//        deleteKeyButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });

		keyButtonPanel.add(addKeyButton);
		keyButtonPanel.add(deleteKeyButton);

		add(keyButtonPanel, c);

		c.gridx = 0;
		c.gridy = 1;

		add(tablePanel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 2;

		filterPanel.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.anchor = GridBagConstraints.NORTH;
		c2.gridx = 0;
		c2.gridy = 0;
		filterPanel.add(filterEmptyButton, c2);
		c2.gridx = 0;
		c2.gridy = 1;
		filterPanel.add(filterCollisionButton, c2);
		c2.gridx = 0;
		c2.gridy = 2;
		filterPanel.add(filterLangButton, c2);

		add(filterPanel, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 2;
		c.weighty = 1;
		c.anchor = GridBagConstraints.SOUTH;



		add(osrPanel, c);
    }

    public void displayTable(ParsedFile parsedFile) {
        if (parsedFile != null) {
            parsFile = parsedFile;

            dataTable = new DataTable(parsedFile);
            parsedFile.initDataTable();

//            JPanel tablePanel = workPanel.getTablePanel();
            tablePanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.HORIZONTAL;

            dataTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
            JScrollPane scrollPane = new JScrollPane(dataTable);
            tablePanel.add(scrollPane, c);
        }

        validate();
    }

    public void removeTable(){
        tablePanel.removeAll();
        validate();
    }

    public JPanel getWorkPanel() {
        return tablePanel;
    }
}
