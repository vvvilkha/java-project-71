package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {


    @Test
    void testFlatDiff() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";

        String content1 = Files.readString(Paths.get(file1).toAbsolutePath());
        String content2 = Files.readString(Paths.get(file2).toAbsolutePath());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data1 = objectMapper.readValue(content1, Map.class);
        Map<String, Object> data2 = objectMapper.readValue(content2, Map.class);

        String diff = Differ.generate(data1, data2);

        assertTrue(diff.contains("- follow: false"));
        assertTrue(diff.contains("+ verbose: true"));
    }
}
