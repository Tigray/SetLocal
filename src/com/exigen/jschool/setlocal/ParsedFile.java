package com.exigen.jschool.setlocal;

import java.util.*;

/**
 * This class represents parsed properties file.
 * File contains list of translations to different languages.
 */
public class ParsedFile {
    private final List<Translation> translationList;
    private final Map<String,Translation> translationMap;
    private final String filename;

    private final String encoding;

    /**
     * Returns name of the file.
     * @return name of the file
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns encoding of parsed file.
     * @return encoding of parsed file
     */
    public String getEncoding() {
        return encoding;
    }
    /**
     * Constructs empty object
     * It should be filled by <code>addTranslation()</code>
     * @param filename
     *          name of the file
     * @param encoding
     *          Encoding of the file
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
     * @return list of {@link Translation} objects
     */
    public List<Translation> getTranslations() {
        return Collections.unmodifiableList(translationList);
    }

    /**
     * Returns translation for given language.
     * @param lang
     *          given language
     * @return {@link Translation} object for this language,
     *          {@code null} if it doesn't exist.
     */
    public Translation getTranslation(String lang) {
        return translationMap.get(lang);
    }

    /**
     * Adds translation to available translations.
     * Warning: methods resets translation for translations,
     * that are already in file.
     * @param translation
     *          translation to be added to list
     */
    public void addTranslation(Translation translation) {
        String lang = translation.getLang();
        if (translationMap.containsKey(lang)) {
            translationList.remove(translationMap.get(lang));
        }
        translationList.add(translation);
        translationMap.put(lang, translation);
    }

    /**
     * Removes translation from file.
     * @param translation
     *          translation to be removed
     */
    public void removeTranslation(Translation translation) {
        translationList.remove(translation);
        translationMap.remove(translation.getLang());
    }
}
