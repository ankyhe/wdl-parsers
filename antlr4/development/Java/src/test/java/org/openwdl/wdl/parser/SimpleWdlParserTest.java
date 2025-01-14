package org.openwdl.wdl.parser;

import org.junit.jupiter.api.Test;
import org.openwdl.wdl.parser.model.Document;
import org.openwdl.wdl.parser.testutils.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleWdlParserTest {

    @Test
    public void testParse() {
        final String s = FileUtils.readStringFromWdlFile("test1.wdl");
        final SimpleWdlParser simpleWdlParser = new SimpleWdlParser();
        final Document document = simpleWdlParser.parse(s);
        log.info("document is {}", document);
    }
}
