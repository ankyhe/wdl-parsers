package org.openwdl.wdl.parser.model;

import javax.validation.constraints.NotBlank;

public class VariableExpression implements Expression {

    @NotBlank
    private String variableName;
}
