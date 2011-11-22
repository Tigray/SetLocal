package com.exigen.jschool.setlocal;

import java.util.*;

/**
 * This class represents set of phrases
 * translated to given language
 */
public class Translation {
    private final List<Line> lines = new LinkedList<Line>();
    private final Map<String,Line> map = new HashMap<String, Line>();
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
        return Collections.unmodifiableList(lines);
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
