package com.example.yobi;
import android.util.Log;

import com.example.yobi.RecipeOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.Pattern;

public class JSONParseManager {
    String[] splitedJSONString;
    String jsonString;
    RecipeOrder[] StringToObject;

    JSONParseManager(String json) { jsonString = json; };
    void splitJSON() {
        splitedJSONString = jsonString.split(Pattern.quote("}"));

        for(int i = 0; i < splitedJSONString.length; i++) {
            splitedJSONString[i] = splitedJSONString[i].trim();
            splitedJSONString[i] += "\n}";
            if(splitedJSONString[i].startsWith(",")) splitedJSONString[i] = splitedJSONString[i].substring(1);;
            splitedJSONString[i] = splitedJSONString[i].trim();
//            System.out.println(s);
        }
        StringToObject = new RecipeOrder[splitedJSONString.length];
    }

    String[] getStrings() {
        return splitedJSONString;
    }

    RecipeOrder[] getObject() {
        ObjectMapper mapper = new ObjectMapper();
        RecipeOrder[] StringToObject = new RecipeOrder[splitedJSONString.length];

        try {
            int i = 0;
            for(String s : splitedJSONString) {
//                System.out.println(s);
                StringToObject[i++] = mapper.readValue(s, RecipeOrder.class);
                //Log.e("my log : ", StringToObject[i++].getRcp_NM());
                //System.out.println(StringToObject[i++].getRcp_NM());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return StringToObject;
    }
}
