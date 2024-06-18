package com.example.yobi;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.Pattern;

public class JSONParseManager {
    String[] splitedJSONString;
    String jsonString;
    RecipeOrder[] StringToObject;

    JSONParseManager(String json) { jsonString = json; };
    void splitJSON() {
        if(jsonString == null) {
            Log.e("jsonparserMessage", "파싱할 json null");
            return;
        }

        if(jsonString.startsWith("[")) jsonString = jsonString.substring(1);
        if(jsonString.endsWith("]")) jsonString = jsonString.substring(0, jsonString.length() - 1);

        splitedJSONString = jsonString.split(Pattern.quote("}"));

        for(int i = 0; i < splitedJSONString.length; i++) {
            splitedJSONString[i] = splitedJSONString[i].trim();
            splitedJSONString[i] += "\n}";
            if(splitedJSONString[i].startsWith(",")) splitedJSONString[i] = splitedJSONString[i].substring(1);;
            splitedJSONString[i] = splitedJSONString[i].trim();
//            System.out.println(s);
            Log.e("JSONParserManager.splitJSON()", splitedJSONString[i]);
        }
        StringToObject = new RecipeOrder[splitedJSONString.length];
    }

    String[] getStrings() {
        return splitedJSONString;
    }

    RecipeOrder[] getObjectbyRecipeOrder() {
        Log.e("JSONParserManager.getObjectbyRecipeOrder()", "start");
        ObjectMapper mapper = new ObjectMapper();
        RecipeOrder[] StringToObject = new RecipeOrder[splitedJSONString.length];

        try {
            Log.e("JSONParserManager.getObjectbyRecipeOrder()", "정상");
            int i = 0;
            for(String s : splitedJSONString) {
//                System.out.println(s);
                StringToObject[i] = mapper.readValue(s, RecipeOrder.class);
                Log.e("my log : ", StringToObject[i].getAtt_FILE_NO_MAIN());
                Log.e("my log : ", StringToObject[i].getRcpnm());
                Log.e("my log : ", StringToObject[i].getRcp_PAT2());
                Log.e("my log : ", StringToObject[i].getInfo_WGT());
                Log.e("my log : ", StringToObject[i].getRcp_PARTS_DTLS());
                i++;
                //System.out.println(StringToObject[i++].getRcp_NM());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Log.e("JSONParserManager.getObjectbyRecipeOrder()", "에러");
        }

        return StringToObject;
    }

    UserRecipe[] getObjectbyUserRecipe() {
        Log.e("JSONParserManager.getObjectbyUserRecipe()", "start");
        ObjectMapper mapper = new ObjectMapper();
        UserRecipe[] StringToObject = new UserRecipe[splitedJSONString.length];

        try {
            Log.e("JSONParserManager.getObjectbyUserRecipe()", "정상");
            int i = 0;
            for(String s : splitedJSONString) {
//                System.out.println(s);
                StringToObject[i] = mapper.readValue(s, UserRecipe.class);
                i++;
                //System.out.println(StringToObject[i++].getRcp_NM());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            Log.e("JSONParserManager.getObjectbyUserRecipe()", "에러");
        }

        return StringToObject;
    }
}
