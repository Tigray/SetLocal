package com.exigen.jschool.setlocal;

import java.io.*;
import java.lang.String;

/**
 * This class parses properties file.
 */
public class Parser {
    private static final long MAX_SIZE = 100 * 1024 * 1024; // 100Mb

    /**
     * Parses given file.
     *
     * @param filename
     *          Name of the file
     * @param encoding
     *          Encoding of file
     * @return ParsedFile
     *          Structure, containing all information
     * @throws IOException     In a case of reading problems
     * @throws FormatException In a case of bad file format
     */
    public ParsedFile parse(String filename, String encoding) throws IOException, FormatException {
        checkFile(filename);
        ParsedFile parsedFile = new ParsedFile(filename, encoding);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), encoding));
        String currentLine;
        Translation currentTranslation = null;
        int lineNum = 0;

        currentLine = reader.readLine().trim();
        currentLine = fixNotepadLine(encoding, currentLine);

        // Reading loop
        while (reader.ready()) {
            lineNum++;
            if (lineNum > 1) {
                currentLine = reader.readLine().trim();
            }
            if (currentLine.length() == 0) {
                continue;
            }
            if (currentLine.charAt(0) == '[' && currentLine.charAt(currentLine.length() - 1) == ']') {
                // Section
                currentTranslation = processSection(parsedFile, currentLine, currentTranslation);
            } else {
                // Line
                processKeyValue(currentLine, currentTranslation, lineNum);
            }
        }
        reader.close();

        if (currentTranslation != null) {
            // Save previous section
            parsedFile.addTranslation(currentTranslation);
        }
        return parsedFile;
    }

    private Translation processSection(ParsedFile parsedFile, String currentLine, Translation currentTranslation) {
        if (currentTranslation != null) {
            // Save previous section
            parsedFile.addTranslation(currentTranslation);
        }
        // New section
        String lang = currentLine.substring(1, currentLine.length() - 1).trim();
        currentTranslation = new Translation(lang);
        return currentTranslation;
    }

    private void processKeyValue(String currentLine, Translation currentTranslation, int lineNum) throws FormatException {
        String[] tokens;
        String key;
        String value;
        tokens = currentLine.split("=");
        if (tokens.length == 2) {   // Only key value in line
            key = tokens[0].trim();
            value = tokens[1].trim();
            if (currentTranslation != null) {
                currentTranslation.addLine(new Line(key, value));
            } else {
                throw new FormatException("Line outside section at " + lineNum);
            }
            if (!UniqueKeys.contains(key)) {
                UniqueKeys.add(key);
            }
        } else {
            throw new FormatException("Wrong line format (or encoding) at " + lineNum + ": " + currentLine);
        }
    }

    // This method removes marker char that Notepad inserts in UTF-8 files
    private String fixNotepadLine(String encoding, String currentLine) {
        if ("UTF-8".equals(encoding) && currentLine.length() > 0) {
            if (currentLine.charAt(0) == 65279) {
                currentLine = currentLine.substring(1);
            }
        }
        return currentLine;
    }

    // This method checks file existence and size
    private void checkFile(String filename) throws FileNotFoundException, FileTooBigException {
        File file = new File(filename);
        if (!file.isFile()) throw new FileNotFoundException("File is not found: " + filename);
        if (file.length() > MAX_SIZE) {
            throw new FileTooBigException(String.format("File %s is too big - %d bytes", filename, file.length()));
        }
    }

    /**
     * Saves modified memory structure to disk.
     *
     * @param parsedFile Object representing parsed file
     * @throws IOException In a case of writing problems
     */
    public void save(ParsedFile parsedFile) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(parsedFile.getFilename()), parsedFile.getEncoding()));
        for (Translation translation : parsedFile.getTranslations()) {
            writer.printf("[%s]%n", translation.getLang());
            for (Line line : translation.getLines()) {
                if (line!=null) writer.println(line);
            }
            writer.println();
        }
        writer.flush();
        writer.close();
    }
}
