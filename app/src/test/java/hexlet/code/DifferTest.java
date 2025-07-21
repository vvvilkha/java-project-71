package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

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

    private String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path))).trim();
    }

    @Test
    @DisplayName("Throws exception for non-existent file")
    public void fileNotFound() {
        String badPath = "src/test/resources/files/non-existentFile.txt";
        assertThrows(IOException.class, () -> readFile(badPath));
    }

    @Test
    @DisplayName("Default format (stylish) for YAML")
    public void generateDefaultStylishYaml() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultStylish.txt");
        String actual = Differ.generate(firstYaml, secondYaml);
        assertThat(normalize(actual)).isEqualTo(normalize(expected));
    }

    @Test
    @DisplayName("Stylish format for YAML")
    public void generateStylishYaml() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultStylish.txt");
        String actual = Differ.generate(firstYaml, secondYaml, "stylish");
        assertThat(normalize(actual)).isEqualTo(normalize(expected));
    }

    @Test
    @DisplayName("Stylish format for JSON")
    public void generateStylishJson() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultStylish.txt");
        String actual = Differ.generate(firstJson, secondJson, "stylish");
        assertThat(normalize(actual)).isEqualTo(normalize(expected));
    }

    @Test
    @DisplayName("JSON output format (JSON input)")
    public void generateJsonOutputFromJson() throws Exception {
        assertJsonEquals("src/test/resources/expectedResultFiles/resultJson.json",
                Differ.generate(firstJson, secondJson, "json"));
    }

    @Test
    @DisplayName("JSON output format (YAML input)")
    public void generateJsonOutputFromYaml() throws Exception {
        assertJsonEquals("src/test/resources/expectedResultFiles/resultJson.json",
                Differ.generate(firstYaml, secondYaml, "json"));
    }

    @Test
    @DisplayName("Plain format for YAML")
    public void generatePlainYaml() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultPlain.txt");
        String actual = Differ.generate(firstYaml, secondYaml, "plain");
        assertThat(normalize(actual)).isEqualTo(normalize(expected));
    }

    @Test
    @DisplayName("Plain format for JSON")
    public void generatePlainJson() throws Exception {
        String expected = readFile("src/test/resources/expectedResultFiles/resultPlain.txt");
        String actual = Differ.generate(firstJson, secondJson, "plain");
        assertThat(normalize(actual)).isEqualTo(normalize(expected));
    }

    private String normalize(String input) {
        return input.trim().replace("\r\n", "\n").replace("\r", "\n");
    }

    private void assertJsonEquals(String expectedPath, String actualContent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedJson = mapper.readTree(readFile(expectedPath));
        JsonNode actualJson = mapper.readTree(actualContent);
        assertThat(actualJson).isEqualTo(expectedJson);
    }
}


