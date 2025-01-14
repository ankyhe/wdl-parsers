package org.openwdl.wdl.parser.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class Call implements InnerWorkflowElement {
    
    @NotBlank
    private String name;

    @NotEmpty
    private List<@NotNull Declaration> declarations;
}
