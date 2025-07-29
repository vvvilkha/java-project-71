package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    @DisplayName("Diff generation for various formats and inputs")
    void testDiffGeneration(String file1, String file2, String format, String expectedPath) throws Exception {
        String actual = Differ.generate(file1, file2, format);

        if (format.equals("json")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode expected = mapper.readTree(Files.readString(Path.of(expectedPath)));
            JsonNode actualJson = mapper.readTree(actual);
            assertThat(actualJson).isEqualTo(expected);
        } else {
            String expected = Files.readString(Path.of(expectedPath));
            assertThat(normalize(actual)).isEqualTo(normalize(expected));
        }
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("src/test/resources/files/firstJson.json", "src/test/resources/files/secondJson.json", "stylish", "src/test/resources/expectedResultFiles/resultStylish.txt"),
                Arguments.of("src/test/resources/files/firstYaml.yml", "src/test/resources/files/secondYaml.yml", "stylish", "src/test/resources/expectedResultFiles/resultStylish.txt"),
                Arguments.of("src/test/resources/files/firstJson.json", "src/test/resources/files/secondJson.json", "plain", "src/test/resources/expectedResultFiles/resultPlain.txt"),
                Arguments.of("src/test/resources/files/firstYaml.yml", "src/test/resources/files/secondYaml.yml", "plain", "src/test/resources/expectedResultFiles/resultPlain.txt"),
                Arguments.of("src/test/resources/files/firstJson.json", "src/test/resources/files/secondJson.json", "json", "src/test/resources/expectedResultFiles/resultJson.json"),
                Arguments.of("src/test/resources/files/firstYaml.yml", "src/test/resources/files/secondYaml.yml", "json", "src/test/resources/expectedResultFiles/resultJson.json")
        );
    }

    private String normalize(String input) {
        return input.trim().replace("\r\n", "\n").replace("\r", "\n");
    }
}




