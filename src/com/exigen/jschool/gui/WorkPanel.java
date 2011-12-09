package com.exigen.jschool.gui;

import com.exigen.jschool.setlocal.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.String;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.swing.*;

/**
 * @author Inga Ovod
 */
public class WorkPanel extends JPanel {

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

    private JComboBox deleteKeysComboBox = new JComboBox();

    private JButton filterEmptyButton = new JButton("Empty");
    private JButton filterCollisionButton = new JButton("Collision");
    private JButton filterLangButton = new JButton("Languages");

    private JTable dataTable;
    private ParsedFile parsFile;

    private String selectDeletedKey;
    private int selectedIndex;

    public WorkPanel(MainFrame frame) {
        initPanel(frame);
    }

    public ParsedFile getParsFile() {
        return parsFile;
    }

    private void initPanel(MainFrame frame) {
        osrPanel = new OpenSaveResetPanel(1, frame);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;

        deleteKeyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectDeletedKey != null) {
                    List<Translation> translations = parsFile.getTranslations();
                    ListIterator litr = translations.listIterator();
                    while (litr.hasNext()) {
                        Translation translation = (Translation) litr.next();
                        Map<String, LinkedList<Integer>> indexMap = translation.getIndexMap();
                        List<Line> lines = translation.getLines();
                        LinkedList<Integer> indexKey = indexMap.remove(selectDeletedKey);
                        for (int i = 0; i < indexKey.size(); i++) {
                            lines.set(indexKey.get(i), null);
                        }

                    }

                    UniqueKeys.remove(selectDeletedKey);
//                    parsFile.initDataTable();
                    parsFile.reSetTable(selectedIndex);
                    parsFile.fireTableDataChanged();
                    selectDeletedKey = null;
                    deleteKeysComboBox.removeItemAt(deleteKeysComboBox.getSelectedIndex());
                    deleteKeysComboBox.setSelectedIndex(-1);
                }


            }
        });

        keyButtonPanel.add(addKeyButton);
        keyButtonPanel.add(deleteKeyButton);
        keyButtonPanel.add(deleteKeysComboBox);

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

            tablePanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.HORIZONTAL;

            dataTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
            JScrollPane scrollPane = new JScrollPane(dataTable);
            tablePanel.add(scrollPane, c);

            for (int i = 0; i < UniqueKeys.count(); i++) {
                deleteKeysComboBox.addItem(UniqueKeys.getKey(i));
            }
            deleteKeysComboBox.setSelectedIndex(-1);
            deleteKeysComboBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (deleteKeysComboBox.getSelectedIndex() != -1){
                            selectDeletedKey = (String) deleteKeysComboBox.getSelectedItem();
                            selectedIndex = deleteKeysComboBox.getSelectedIndex();
                        }

                    }
                }
            });
        }

        validate();
    }

    public void removeTable() {
        tablePanel.removeAll();
        deleteKeysComboBox.removeAllItems();
        validate();
    }

    public JPanel getWorkPanel() {
        return tablePanel;
    }
}
