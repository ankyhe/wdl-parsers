package org.openwdl.wdl.parser.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class Workflow {

    @NotBlank
    private String name;

    @Builder.Default
    private List<WorkflowElement> workflowElements = new ArrayList<>();

    @Override
    public String toString() {
        final String s = (this.workflowElements == null ? List.of() : this.workflowElements).stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
        return "Workflow: %s %s".formatted(this.name, s);
    }
}
