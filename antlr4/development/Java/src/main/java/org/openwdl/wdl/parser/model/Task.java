package org.openwdl.wdl.parser.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class Task {

    @NotBlank
    private String name;

    private TaskInput input;

    private TaskOutput output;

    @Override
    public String toString() {
        return "Task: %s %s %s".formatted(this.name, this.input, this.output);
    }
}
