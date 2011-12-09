package com.exigen.jschool.setlocal;

/**
 * @author Roman Voropaev
 * This class represents translation of given phrase,
 * key represents english phrase,
 * value represents translation to given language
 */
public class Line {
    private final String key;
    private final String value;

    /**
     * Line constructor
     * @param value Phrase translation
     * @param key Phrase in English
     */
    public Line(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }

    /**
     * Key getter
     * @return English variant of phrase
     */
    public String getKey() {
        return key;
    }

    /**
     * Value getter
     * @return Phrase translation
     */
    public String getValue() {
        return value;
    }
}
