package com.exigen.jschool.setlocal;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
public class DataTable extends JTable {

    ParsedFile parsedFileModel;

    public DataTable(ParsedFile model) {
        setModel(model);
        parsedFileModel = model;
    }

    public TableCellEditor getCellEditor(final int row, final int col) {

        String[] items = parsedFileModel.getValue(row, col);
        if (items != null) {
            return new JCBEditor(new JComboBox(items));
        } else {
            return new JCBEditor(new JComboBox());

        }

    }

    class  JCBEditor extends  DefaultCellEditor {

        public JCBEditor(JComboBox comboBox) {
            super(comboBox);
        }
    }

}
