package com.exigen.jschool.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class WatchPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel watchPanel = new JPanel();
	private OpenSaveResetPanel osrPanel = new OpenSaveResetPanel(2);
	
	public WatchPanel() {
        initPanel();
	}

    private void initPanel(){
        setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        add(watchPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;
        add(osrPanel, c);
    }
}
