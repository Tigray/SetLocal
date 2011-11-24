package com.exigen.jschool.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OpenSaveResetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel osrButtonPanel = new JPanel();

	JButton openButton = new JButton("Open");
	JButton saveButton = new JButton("Save");
	JButton resetButton = new JButton("Reset");

	public OpenSaveResetPanel(int numTub) {

		osrButtonPanel.add(openButton);
		osrButtonPanel.add(resetButton);
		if (numTub == 1) {
			osrButtonPanel.add(saveButton);
		}
		add(osrButtonPanel);

	}
}
