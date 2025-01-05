import com.alibaba.fastjson.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonArrayDeduplicate {

//    public static void main(String[] args) {
//        try {
//            // 读取源数据的JSON文件
//            JSONReader reader = new JSONReader(new FileReader("src/main/resources/i_my_format_data.json"));
//            JSONArray data = reader.readObject(JSONArray.class);
//
//            // 确定80%和20%的数据量
//            int totalData = data.size();
//            long splitIndex = (long) (0.8 * totalData);
//
//            // 分割数据
//            JSONArray data80 = new JSONArray();
//            JSONArray data20 = new JSONArray();
//
//            for (long i = 0; i < totalData; i++) {
//                if (i < splitIndex) {
//                    data80.add(data.getJSONObject(Math.toIntExact(i)));
//                } else {
//                    data20.add(data.getJSONObject(Math.toIntExact(i)));
//                }
//            }
//
//            // 将分割后的数据写入两个新的JSON文件
//            Files.write(Paths.get("src/main/resources/data_80.json"), data80.toJSONString().getBytes());
//
//            Files.write(Paths.get("src/main/resources/data_20.json"), data20.toJSONString().getBytes());
//
//
//            System.out.println("数据已成功分割成80%和20%并保存到两个新的JSON文件中。");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        String s = "1.7163091968e+10";

        Double parseDouble = Double.parseDouble(s);
        Long l = parseDouble.longValue();
        System.out.println(l);
    }
}
