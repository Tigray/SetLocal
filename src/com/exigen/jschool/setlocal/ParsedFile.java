package com.exigen.jschool.setlocal;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * @author Roman Voropaev
 * This class represents parsed properties file.
 * File contains list of translations to different languages.
 */
public class ParsedFile extends AbstractTableModel {
    private final List<Translation> translationList;
    private final Map<String, Translation> translationMap;
    private final String filename;


    private final String encoding;
    private ArrayList<ArrayList> tableData;

    /**
     * Returns name of the file.
     *
     * @return name of the file
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns encoding of parsed file.
     *
     * @return encoding of parsed file
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Constructs empty object
     * It should be filled by <code>addTranslation()</code>
     *
     * @param filename name of the file
     * @param encoding Encoding of the file
     */
    public ParsedFile(String filename, String encoding) {
        translationList = new ArrayList<Translation>();
        translationMap = new HashMap<String, Translation>();
        this.filename = filename;
        this.encoding = encoding;
    }

    /**
     * Returns list of all available translations.
     * List itself is not modifiable! Use addTranslation() instead.
     *
     * @return list of {@link Translation} objects
     */
    public List<Translation> getTranslations() {
        return translationList;
//        return Collections.unmodifiableList(translationList);
    }

    /**
     * Returns translation for given language.
     *
     * @param lang given language
     * @return {@link Translation} object for this language,
     *         {@code null} if it doesn't exist.
     */
    public Translation getTranslation(String lang) {
        return translationMap.get(lang);
    }

    /**
     * Adds translation to available translations.
     * Warning: methods resets translation for translations,
     * that are already in file.
     *
     * @param translation translation to be added to list
     */
    public void addTranslation(Translation translation) {
        translation.setIndexMap();
        String lang = translation.getLang();
        if (translationMap.containsKey(lang)) {
            translationList.remove(translationMap.get(lang));
        }
        translationList.add(translation);
        translationMap.put(lang, translation);
    }

    /**
     * Removes translation from file.
     *
     * @param translation translation to be removed
     */
    public void removeTranslation(Translation translation) {
        translationList.remove(translation);
        translationMap.remove(translation.getLang());
    }

//    public Class<?> getColumnClass(int columnIndex){
//                return getValueAt(0, columnIndex).getClass();
//
//    }

    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }

    public int getRowCount() {
        return UniqueKeys.count();
    }

    public String getColumnName(int col) {
        if (col == 0) {
            return "keys";
        } else {
            return translationList.get(col - 1).getLang();
        }
    }

    public int getColumnCount() {
        return translationList.size() + 1;
    }


    public void setValueAt(Object value, int row, int col) {

        tableData.get(col).set(row, value);

        //
        Translation translation = translationList.get(col - 1);
        List<Line> lines = translation.getLines();
        String key = UniqueKeys.getKey(row);
        LinkedList<Integer> indexKey = translation.getIndexKey(key);
        String valueSt = (String)value;
        if (indexKey != null){
            if (valueSt.length() != 0){
                lines.set(indexKey.get(0), new Line(key, valueSt));
            }else{
                lines.set(indexKey.get(0), null);
            }
        }else{
            if (valueSt.length() != 0){
                lines.add(new Line(key, valueSt));
            }
        }
        //
        fireTableCellUpdated(row, col);

    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData.get(columnIndex).get(rowIndex);
    }

    public String[] getValue(int rowIndex, int columnIndex) {
        String[] result;
        if (columnIndex == 0) {
            result = new String[1];
            result[0] = UniqueKeys.getKey(rowIndex);
            return result;
        } else {
            Translation translation = translationList.get(columnIndex - 1);
            List<Line> lines = translation.getLines();
            String key = UniqueKeys.getKey(rowIndex);
            LinkedList<Integer> indexKey = translation.getIndexKey(key);
            if (indexKey != null) {
                result = new String[indexKey.size()];
                for (int i = 0; i < result.length; i++) {
                    Line line = lines.get(indexKey.get(i));
                    if (line != null){
                        result[i] = lines.get(indexKey.get(i)).getValue();
                    }
                }
                return result;
            } else {
                return null;
            }
        }
    }

    public void reSetTable(int row){
         for(int i = 0; i < translationList.size() + 1; i++){
             ArrayList<String> translation = tableData.get(i);
             translation.remove(row);
         }
    }

    public void initDataTable() {
        tableData = new ArrayList<ArrayList>();
        for (int i = 0; i < translationList.size() + 1; i++) {
            tableData.add(new ArrayList<String>());
        }

        ArrayList list = tableData.get(0);
        for (int i = 0; i < UniqueKeys.count(); i++) {
            list.add(UniqueKeys.getKey(i));
        }

        for (int i = 0; i < translationList.size(); i++) {
            list = tableData.get(i + 1);
            for (int j = 0; j < UniqueKeys.count(); j++) {


                Translation translation = translationList.get(i);
                List<Line> lines = translation.getLines();
                String key = UniqueKeys.getKey(j);
                LinkedList<Integer> indexKey = translation.getIndexKey(key);

                if (indexKey != null) {
                    list.add(lines.get(indexKey.get(0)).getValue());
                } else {
                    list.add(null);
                }

            }
        }
    }
}
