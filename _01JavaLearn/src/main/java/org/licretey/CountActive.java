package org.licretey;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;


import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


public class CountActive {
    public static void main(String[] args) {
        try {
            String str = CountActive.readJsonFile("/home/nico/Desktop/dataStr.json");
            ObjectMapper objectMapper = new ObjectMapper();
            Data data = objectMapper.readValue(str, Data.class);
            List<DataRow> rows = data.getRows();
            List<String> collect = rows.stream().map(DataRow::getUsername).collect(Collectors.toList());
            String join = StringUtils.join(collect, ";");
            System.out.println(join);
            System.out.println(join.length());
            // System.out.println("字符串已反序列化为对象.");
            // System.out.println("姓名: " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readJsonFile(String fileName){
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch=reader.read())!=-1){
                sb.append((char) ch);
            }
            fileReader.close();;
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


}