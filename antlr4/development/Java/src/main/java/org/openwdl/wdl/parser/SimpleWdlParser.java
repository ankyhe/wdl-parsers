package org.openwdl.wdl.parser;

import javax.validation.constraints.NotBlank;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.openwdl.wdl.parser.listener.MyWdlParserListener;
import org.openwdl.wdl.parser.model.Document;

public class SimpleWdlParser {

    public Document parse(@NotBlank final String s) {
        final org.openwdl.wdl.parser.WdlLexer lexer = new org.openwdl.wdl.parser.WdlLexer(CharStreams.fromString(s));
        final org.openwdl.wdl.parser.WdlParser parser = new org.openwdl.wdl.parser.WdlParser(new CommonTokenStream(lexer));
        final ParseTree tree = parser.document();
        final MyWdlParserListener listener = new MyWdlParserListener();
        ParseTreeWalker.DEFAULT.walk(listener, tree);

        return Document.builder()
                .version(listener.getVersion())
                .tasks(listener.getTasks())
                .workflows(listener.getWorkflows())
                .build();
    }
}
