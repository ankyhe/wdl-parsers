package org.openwdl.wdl.parser.testutils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.validation.constraints.NotBlank;

public final class FileUtils {

    private FileUtils() {

    }

    public static String readStringFromWdlFile(final String fileName) {
        try {
            return doReadStringFromWdlFile(fileName);
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static String doReadStringFromWdlFile(@NotBlank final String fileName) throws IOException, URISyntaxException {
        final URL url = FileUtils.class.getClassLoader().getResource(fileName);
        if (url == null) {
            throw new FileNotFoundException("%s is not found".formatted(fileName));
        }
        return Files.readString(Path.of(url.toURI()));

    }
}
