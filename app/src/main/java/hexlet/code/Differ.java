package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.utils.FileUtil;

import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        String content1 = FileUtil.readFile(filePath1);
        String content2 = FileUtil.readFile(filePath2);

        String format1 = FileUtil.getFormat(filePath1);
        String format2 = FileUtil.getFormat(filePath2);

        Map<String, Object> data1 = Parser.parse(content1, format1);
        Map<String, Object> data2 = Parser.parse(content2, format2);

        List<Map<String, Object>> diff = DiffBuilder.build(data1, data2);
        return Formatter.format(Constants.FORMAT_STYLISH, diff);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String content1 = FileUtil.readFile(filePath1);
        String content2 = FileUtil.readFile(filePath2);

        String format1 = FileUtil.getFormat(filePath1);
        String format2 = FileUtil.getFormat(filePath2);

        Map<String, Object> data1 = Parser.parse(content1, format1);
        Map<String, Object> data2 = Parser.parse(content2, format2);

        List<Map<String, Object>> diff = DiffBuilder.build(data1, data2);
        return Formatter.format(format, diff);
    }
}
