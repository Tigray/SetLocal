package com.exigen.jschool.setlocal.test;

import com.exigen.jschool.setlocal.ParsedFile;
import com.exigen.jschool.setlocal.Parser;
import com.exigen.jschool.setlocal.Translation;
import org.junit.*;

import java.util.List;


public class ParserTest {

    @Test
    public void testParse() throws Exception {
        Parser parser = new Parser();
        ParsedFile pf = parser.parse("test.txt", "UTF-16");
        List<Translation> translations;
        translations = pf.getTranslations();

        Assert.assertNotNull(translations);
        Assert.assertEquals(translations.size(), 2);

        Translation russian = pf.getTranslation("RU");

        Assert.assertNotNull(russian);
        Assert.assertNotNull(russian.getLines());
        Assert.assertEquals(russian.getLines().size(), 2);
    }
}
