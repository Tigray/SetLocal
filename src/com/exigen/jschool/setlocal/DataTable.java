package com.exigen.jschool.setlocal;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

public class DataTable extends JTable {

    private ParsedFile parsedFileModel;

    public DataTable(ParsedFile model) {
        setModel(model);
        parsedFileModel = model;
    }

    public TableCellEditor getCellEditor(int row, int col) {

        String[] items = parsedFileModel.getValue(row, col);
        if (items != null) {
            if (items.length == 1) {
                JTextField textField = new JTextField(items[0]);
                textField.addActionListener(new TextFieldListener(textField, row, col));
                return new DefaultCellEditor(new JTextField(items[0]));
            } else {
                JComboBox comboBox = new JComboBox(items);
                comboBox.addItemListener(new ComboBoxListener(comboBox, row, col));
                return new DefaultCellEditor(comboBox);
            }
        } else {
            JTextField textField = new JTextField();
            textField.addActionListener(new TextFieldListener(textField, row, col));
            return new DefaultCellEditor(new JTextField());

        }

    }

    private class TextFieldListener implements ActionListener {
        private JTextField tf;
        private int row;
        private int col;

        public TextFieldListener(JTextField textField, int rowNum, int colNum) {
            tf = textField;
            row = rowNum;
            col = colNum;
        }

        public void actionPerformed(ActionEvent e) {
            Translation translation = parsedFileModel.getTranslations().get(col - 1);
            List<Line> lines = translation.getLines();
            String key = UniqueKeys.getKey(row);
            LinkedList<Integer> indexKey = translation.getIndexKey(key);
            lines.set(indexKey.get(0), new Line(key, tf.getText()));
        }
    }

    private class ComboBoxListener implements ItemListener {
        private JComboBox cBox;
        private int row;
        private int col;

        public ComboBoxListener(JComboBox comboBox, int rowNum, int colNum) {
            cBox = comboBox;
            row = rowNum;
            col = colNum;
        }

        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                int selectedIndex = cBox.getSelectedIndex();
                Translation translation = parsedFileModel.getTranslations().get(col - 1);
                List<Line> lines = translation.getLines();
                String key = UniqueKeys.getKey(row);
                LinkedList<Integer> indexKey = translation.getIndexKey(key);


                for (int i = 0; i < indexKey.size(); i++) {
                    if (i != selectedIndex) {
                        lines.set(indexKey.remove(i), null);
                    }
                }
            } else {

            }

        }
    }


}
