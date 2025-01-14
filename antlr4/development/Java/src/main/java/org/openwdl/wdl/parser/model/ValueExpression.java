package org.openwdl.wdl.parser.model;

import javax.validation.constraints.NotNull;

public class ValueExpression implements Expression {

    @NotNull
    private Type type;

    private Object value;
}
