package my.assignment.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;

@Log4j2
@UtilityClass
public class TestUtils {
    public static String getResourceFileAsString(String fileName) {
        var is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            var reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            log.error("cant find file: {}", fileName);
            return Strings.EMPTY;
        }
    }

    private static InputStream getResourceFileAsInputStream(String fileName) {
        var classLoader = TestUtils.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
