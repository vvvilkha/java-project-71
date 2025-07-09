package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

    private String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))).trim();
    }

    private static String firstYaml;
    private static String secondYaml;
    private static String firstJson;
    private static String secondJson;

    @BeforeAll
    public static void beforeAll() {
        firstYaml = "src/test/resources/files/firstYaml.yml";
        secondYaml = "src/test/resources/files/secondYaml.yml";
        firstJson = "src/test/resources/files/firstJson.json";
        secondJson = "src/test/resources/files/secondJson.json";
    }

    @Test
    public void fileNotFound() {
        String notTheRightPath = "src/test/resources/files/non-existentFile.txt";
        assertThrows(IOException.class, () -> {
            readFile(notTheRightPath);
        });
    }

    @Test
    public void testGenerateDefaultFormat() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultStylish.txt");
        String result = Differ.generate(firstYaml, secondYaml);
        assertThat(result.trim().replace("\r\n", "\n"))
                .isEqualTo(expected.trim().replace("\r\n", "\n"));
    }

    @Test
    public void forYamlGenerateStylishFormat() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultStylish.txt");
        String result = Differ.generate(firstYaml, secondYaml, "stylish");
        assertThat(result.trim().replace("\r\n", "\n"))
                .isEqualTo(expected.trim().replace("\r\n", "\n"));
    }

    @Test
    public void forJsonGenerateStylish() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultStylish.txt");
        String result = Differ.generate(firstJson, secondJson, "stylish");
        assertThat(result.trim().replace("\r\n", "\n"))
                .isEqualTo(expected.trim().replace("\r\n", "\n"));
    }

    @Test
    public void forJsonGenerateJsonFormat() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultJson.json");
        String result = Differ.generate(firstJson, secondJson, "json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedJson = mapper.readTree(expected);
        JsonNode actualJson = mapper.readTree(result);

        assertThat(actualJson).isEqualTo(expectedJson);
    }

    @Test
    public void forYamlGenerateJsonFormat() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultJson.json");
        String result = Differ.generate(firstYaml, secondYaml, "json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedJson = mapper.readTree(expected);
        JsonNode actualJson = mapper.readTree(result);

        assertThat(actualJson).isEqualTo(expectedJson);
    }

    @Test
    public void forYamlGeneratePlainFormat() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultPlain.txt");
        String result = Differ.generate(firstYaml, secondYaml, "plain");
        assertThat(result.trim().replace("\r\n", "\n"))
                .isEqualTo(expected.trim().replace("\r\n", "\n"));
    }

    @Test
    public void forJsonGeneratePlainFormat() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultPlain.txt");
        String result = Differ.generate(firstJson, secondJson, "plain");
        assertThat(result.trim().replace("\r\n", "\n"))
                .isEqualTo(expected.trim().replace("\r\n", "\n"));

    }
}

