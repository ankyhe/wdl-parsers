package org.openwdl.wdl.parser.model.expression;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class GetNameExpression implements ExpressionCore {

    @NotNull
    private ExpressionCore scope;

    @NotNull
    private IdentifierExpression identifier;

    @AssertTrue
    public boolean isValid() {
        return (this.scope instanceof IdentifierExpression) || (this.scope instanceof GetNameExpression);
    }
}
