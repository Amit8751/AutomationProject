package com.hm.framework.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JsonFileUtil {

    private static JSONParser jsonParser;
    private static JSONObject jsonObject;
    private static JSONArray jsonArray;
    private static TreeMap jsonData=null;
    private static List<Map<String,String>> testDataAllRows=null;

    public JsonFileUtil(){
        jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:/GithubProject/AutomationProject/src/com/resources/Players_Data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getJsonDataArray(){
        return ( JSONArray ) jsonObject.get("players_data");

    }

    public static List<Map<String,String>> getJsonDataInMap(){
        JsonFileUtil jsonFileUtil=new JsonFileUtil();
        jsonArray = (JSONArray) jsonObject.get("players_data");

        testDataAllRows=new ArrayList<Map<String, String>>();

        for(Object object : jsonArray){
            JSONObject record = (JSONObject) object;

            try {
                jsonData=new ObjectMapper().readValue(String.valueOf(record), TreeMap.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            testDataAllRows.add(jsonData);
        }
        return testDataAllRows;

    }
}
