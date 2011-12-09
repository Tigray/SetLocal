package com.exigen.jschool.gui;

import javax.swing.JPanel;
import java.awt.*;

/**
 * @author Inga Ovod
 */
public class ComparePanel extends JPanel{

	/**
     * Soryy ))
	 *  At this moment compare panel doesn't work.
	 */
	private static final long serialVersionUID = 1L;

    private JPanel comparePanel = new JPanel();
	private OpenSaveResetPanel osrPanel ;

	public ComparePanel(MainFrame frame) {
		initPanel(frame);
	}

    private void initPanel(MainFrame frame){
        osrPanel = new OpenSaveResetPanel(3, frame);
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
