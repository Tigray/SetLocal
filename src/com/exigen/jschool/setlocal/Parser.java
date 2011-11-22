package com.exigen.jschool.setlocal;

import java.io.*;

/**
 * This class parses properties file.
 */
public class Parser {
    /**
     * Parses given file.
     * @param filename
     *          Name of the file.
     * @return ParsedFile structure, containing all information
     * @throws IOException In a case of reading problems
     * @throws FormatException In a case of bad file format
     */
    public ParsedFile parse(String filename, String encoding) throws IOException, FormatException {
        ParsedFile file = new ParsedFile(filename, encoding);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), encoding));
        String currentLine;
        Translation currentTranslation = null;
        String[] tokens;
        String key, value;
        int lineNum = 0;

        // Reading loop
        while (reader.ready()) {
            currentLine = reader.readLine().trim();
            lineNum++;
            if (currentLine.length() == 0) continue;
            if (currentLine.charAt(0) == '[' && currentLine.charAt(currentLine.length() - 1) == ']') {
                // Section
                if (currentTranslation != null) {
                    // Save previous section
                    file.addTranslation(currentTranslation);
                }
                // New section
                String lang = currentLine.substring(1, currentLine.length() - 1).trim();
                currentTranslation = new Translation(lang);
            } else {
                // Line
                tokens = currentLine.split("=");
                if (tokens.length == 2) {   // Only key value in line
                    key = tokens[0].trim();
                    value = tokens[1].trim();
                    if (currentTranslation != null) {
                        currentTranslation.addLine(new Line(key, value));
                    } else {
                        throw new FormatException("Line outside section at " + lineNum);
                    }
                } else {
                    throw new FormatException("Wrong line format (or encoding) at " + lineNum + ": " + currentLine);
                }
            }
        }
        reader.close();

        if (currentTranslation != null) {
            // Save previous section
            file.addTranslation(currentTranslation);
        }
        return file;
    }

    /**
     * Saves modified memory structure to disk.
     * @param parsedFile Object representing parsed file
     * @throws IOException In a case of writing problems
     */
    public void save(ParsedFile parsedFile) throws IOException{
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(parsedFile.getFilename()), parsedFile.getEncoding()));
        for (Translation translation : parsedFile.getTranslations()) {
            writer.printf("[%s]%n", translation.getLang());
            for (Line line : translation.getLines()) {
                writer.println(line);
            }
            writer.println();
        }
        writer.flush();
        writer.close();
    }

}
