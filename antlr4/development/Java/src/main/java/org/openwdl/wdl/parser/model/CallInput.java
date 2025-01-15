package org.openwdl.wdl.parser.model;

import org.openwdl.wdl.parser.model.expression.Expression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class CallInput {

    private String name;

    private Expression expression;

    @Override
    public String toString() {
        return "%s = %s".formatted(this.name, this.expression);
    }
}
