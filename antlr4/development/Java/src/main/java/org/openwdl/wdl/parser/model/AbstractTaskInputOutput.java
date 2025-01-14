package org.openwdl.wdl.parser.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@NoArgsConstructor
public abstract class AbstractTaskInputOutput {

    private static final String TASK_SUFFIX = "Task";
    
    @NotEmpty
    protected List<@NotNull Declaration> declarations;

    protected String toStringSuffix() {
        return StringUtils.removeStart(this.getClass().getSimpleName(), TASK_SUFFIX);
    }

    @Override
    public String toString() {
        final String s = (this.declarations == null ? List.of() : this.declarations)
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
        return "%s (%s)".formatted(toStringSuffix(), s);
    }

}
