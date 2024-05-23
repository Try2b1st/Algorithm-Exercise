import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonArrayDeduplicate {
    public static void main(String[] args) {
        String inputFilePath = "D:\\LeetCode\\LeetCode\\src\\main\\resources\\converted.json";  // 将此处替换为你的输入 JSON 文件路径
        String outputFilePath = "D:\\LeetCode\\LeetCode\\src\\main\\resources\\new.json";  // 将此处替换为你的输出 JSON 文件路径

        try {
            // 读取文件内容
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));

            // 将文件内容解析为 JSON 数组
            JSONArray jsonArray = JSON.parseArray(content);

            // 使用 Set 去重
            Set<JSONObject> jsonSet = new HashSet<>(jsonArray.toJavaList(JSONObject.class));

            // 将 Set 转换回 JSON 数组
            JSONArray deduplicatedJsonArray = new JSONArray();
            deduplicatedJsonArray.addAll(jsonSet);

            // 将去重后的 JSON 数组写入到新的 JSON 文件中
            Files.write(Paths.get(outputFilePath), deduplicatedJsonArray.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
