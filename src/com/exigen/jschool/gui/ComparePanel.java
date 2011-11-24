package com.exigen.jschool.gui;

import javax.swing.JPanel;
import java.awt.*;

public class ComparePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private JPanel comparePanel = new JPanel();
	private OpenSaveResetPanel osrPanel = new OpenSaveResetPanel(2);

	public ComparePanel() {
		initPanel();
	}

    private void initPanel(){
        setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        add(comparePanel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTH;
        add(osrPanel, c);
    }
}
