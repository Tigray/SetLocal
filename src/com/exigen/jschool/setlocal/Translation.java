package com.exigen.jschool.setlocal;

import java.util.*;

/**
 * @author Roman Voropaev
 * This class represents set of phrases
 * translated to given language
 */
public class Translation {
    private final List<Line> lines = new LinkedList<Line>();
    private final Map<String,Line> map = new HashMap<String, Line>();

    //
    private final Map<String, LinkedList<Integer>> indexMap = new HashMap<String, LinkedList<Integer>>();

    public  Map<String, LinkedList<Integer>> getIndexMap(){
        return indexMap;
    }

    public LinkedList<Integer> getIndexKey(String key){
        return indexMap.get(key);
    }

    public void setIndexMap(){
        Line line;
        String key;
        LinkedList<Integer> indexList;

        for (int i = 0; i < lines.size(); i++){
            line = lines.get(i);
            key = line.getKey();

            if (indexMap.containsKey(key)){
                indexMap.get(key).add(i);
            }else{
                indexList = new LinkedList<Integer>();
                indexList.add(i);
                indexMap.put(key, indexList);
            }
        }
    }
    //
    private final String lang;

    /**
     * Object constructor.
     * @param lang
     *          Language of translation
     */
    public Translation(String lang) {
        this.lang = lang;
    }

    /**
     * Returns language of translation.
     * @return language of translation
     */
    public String getLang() {
        return lang;
    }

    /**
     * Returns all phrases in translation.
     * @return list of phrases
     */
    public List<Line> getLines() {
        return lines;
//        return Collections.unmodifiableList(lines);
    }

    public void addLine(Line line) {
        lines.add(line);
        map.put(line.getKey(), line);
    }

    public void removeLine(int lineNumber) {
        map.remove(lines.remove(lineNumber).getKey());
    }

    public void removeLine(String key) {
        lines.remove(map.remove(key));
    }
}
