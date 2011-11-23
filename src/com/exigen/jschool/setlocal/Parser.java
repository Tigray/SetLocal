package com.exigen.jschool.setlocal;

import java.io.*;

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
        File file = new File(filename);
        if (!file.isFile()) throw new FileNotFoundException("File is not found: " + filename);
        if (file.length() > MAX_SIZE) {
            throw new FileTooBigException(String.format("File %s is too big - %d bytes", filename, file.length()));
        }
        ParsedFile parsedFile = new ParsedFile(filename, encoding);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), encoding));
        String currentLine;
        Translation currentTranslation = null;
        String[] tokens;
        String key, value;
        int lineNum = 0;

        currentLine = reader.readLine().trim();
        if ("UTF-8".equals(encoding) && currentLine.length() > 0) {
            if (currentLine.charAt(0) == 65279) {
                currentLine = currentLine.substring(1);
            }
        }

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
                if (currentTranslation != null) {
                    // Save previous section
                    parsedFile.addTranslation(currentTranslation);
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
            parsedFile.addTranslation(currentTranslation);
        }
        return parsedFile;
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
                writer.println(line);
            }
            writer.println();
        }
        writer.flush();
        writer.close();
    }

}
