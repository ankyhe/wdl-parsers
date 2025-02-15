package org.openwdl.wdl.parser.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.openwdl.wdl.parser.model.expression.Expression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class BoundDeclaration implements Declaration, InnerWorkflowElement {

    @NotBlank
    private Type type;

    @NotBlank
    private String argumentName;

    @NotNull
    private Expression expression;

    @Override
    public String toString() {
        return "%s %s=%s".formatted(this.type, this.argumentName, this.expression);
    }
}
