package org.openwdl.wdl.parser.listener;

import java.util.ArrayList;
import java.util.List;

import org.openwdl.wdl.parser.WdlParser;
import org.openwdl.wdl.parser.WdlParserBaseListener;
import org.openwdl.wdl.parser.model.AnyType;
import org.openwdl.wdl.parser.model.ArrayType;
import org.openwdl.wdl.parser.model.Declaration;
import org.openwdl.wdl.parser.model.IntType;
import org.openwdl.wdl.parser.model.StringType;
import org.openwdl.wdl.parser.model.Task;
import org.openwdl.wdl.parser.model.TaskInput;
import org.openwdl.wdl.parser.model.TaskOutput;
import org.openwdl.wdl.parser.model.Type;
import org.openwdl.wdl.parser.model.UnboundDeclaration;

import lombok.Getter;

public class MyWdlParserListener extends WdlParserBaseListener {

    @Getter
    private String version;

    @Getter
    private final List<Task> tasks = new ArrayList<>();

    private Type type;

    private Task task;

    private final List<Declaration> declarations = new ArrayList<>();

    @Override
    public void enterVersion(WdlParser.VersionContext ctx) {
        this.version = ctx.RELEASE_VERSION() == null ? null : ctx.RELEASE_VERSION().getText();

        super.enterVersion(ctx);
    }

    @Override
    public void enterType_base(final WdlParser.Type_baseContext ctx) {
        if (ctx.STRING() != null) {
            this.type = new StringType();
        } else if (ctx.INT() != null) {
            this.type = new IntType();
        } else {
            this.type = new AnyType();
        }
        super.enterType_base(ctx);
    }

    @Override
    public void exitArray_type(final WdlParser.Array_typeContext ctx) {
        this.type = new ArrayType(this.type);
        super.exitArray_type(ctx);
    }

    @Override
    public void exitUnbound_decls(final WdlParser.Unbound_declsContext ctx) {
        final String identifier = ctx.Identifier().getText();
        final UnboundDeclaration unboundDeclaration = UnboundDeclaration.builder()
                .type(this.type)
                .argumentName(identifier)
                .build();
        this.declarations.add(unboundDeclaration);

        super.exitUnbound_decls(ctx);
    }

    @Override
    public void enterTask(final WdlParser.TaskContext ctx) {
        final String taskName = ctx.Identifier().getText();
        this.task = Task.builder()
                .name(taskName)
                .build();

        super.enterTask(ctx);
    }

    @Override
    public void exitTask_input(final WdlParser.Task_inputContext ctx) {
        final TaskInput taskInput = TaskInput.builder()
                .declarations(List.copyOf(this.declarations))
                .build();
        this.task.setInput(taskInput);
        this.declarations.clear();

        super.exitTask_input(ctx);
    }

    @Override
    public void exitTask_output(final WdlParser.Task_outputContext ctx) {
        final TaskOutput taskOutput = TaskOutput.builder()
                .declarations(List.copyOf(this.declarations))
                .build();
        this.task.setOutput(taskOutput);
        this.declarations.clear();

        super.exitTask_output(ctx);
    }

    @Override
    public void exitTask(final WdlParser.TaskContext ctx) {
        this.tasks.add(this.task);

        super.exitTask(ctx);
    }
}
