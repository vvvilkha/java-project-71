package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    private static final String FIXTURES_PATH = "src/test/resources/";

    @Test
    void testJsonDiff() throws Exception {
        String file1 = FIXTURES_PATH + "file1.json";
        String file2 = FIXTURES_PATH + "file2.json";

        Map<String, Object> data1 = Parser.parse(file1);
        Map<String, Object> data2 = Parser.parse(file2);

        String expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

        String actual = Differ.generate(data1, data2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testYamlDiff() throws Exception {
        String file1 = FIXTURES_PATH + "file1.yml";
        String file2 = FIXTURES_PATH + "file2.yml";

        Map<String, Object> data1 = Parser.parse(file1);
        Map<String, Object> data2 = Parser.parse(file2);

        String expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

        String actual = Differ.generate(data1, data2);

        assertThat(actual).isEqualTo(expected);
    }
}
