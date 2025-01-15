package org.openwdl.wdl.parser.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class Document {

    private static final String DEFAULT_VERSION = "1.0";

    private String version;

    @Builder.Default
    private List<@NotNull Task> tasks = new ArrayList<>();

    @Builder.Default
    @Size(max = 1) /* support more in the future */
    private List<@NotNull Workflow> workflows = new ArrayList<>();

    public String getVersion() {
        if (StringUtils.isNotBlank(this.version)) {
            return this.version;
        }

        return DEFAULT_VERSION;
    }
}
