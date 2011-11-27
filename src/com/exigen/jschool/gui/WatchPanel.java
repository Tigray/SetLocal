package com.exigen.jschool.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class WatchPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel watchPanel = new JPanel();
	private OpenSaveResetPanel osrPanel;
	
	public WatchPanel(MainFrame frame) {
        initPanel(frame);
	}

    public JPanel getWatchPanel(){
        return watchPanel;
    }

    private void initPanel(MainFrame frame){
        osrPanel = new OpenSaveResetPanel(2, frame);

        watchPanel.setLayout(new BoxLayout(watchPanel, BoxLayout.PAGE_AXIS));

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

    public void removeWatch(){
        watchPanel.removeAll();
        validate();
    }
}
