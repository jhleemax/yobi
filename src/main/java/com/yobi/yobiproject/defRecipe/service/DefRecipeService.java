package com.yobi.yobiproject.defRecipe.service;

import com.yobi.yobiproject.defRecipe.Entitiy.DefRecipe;
import com.yobi.yobiproject.defRecipe.Entitiy.DefRecipeRepository;
import com.yobi.yobiproject.defRecipe.dto.SearchDefRecipeDTO;
import com.yobi.yobiproject.member.Entity.Member;
import com.yobi.yobiproject.member.dto.ResponseMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DefRecipeService {
    String key = "29cf6df4f3f84cbf82be";
    private final DefRecipeRepository defRecipeRepository;

    public List<DefRecipe> SendDefrecipe() {
        List<DefRecipe> defRecipe = defRecipeRepository.findAll();
        return defRecipe;

    }

    public List<DefRecipe> ReadDefrecipe(String rcpSEQ) {
        List<DefRecipe> readRecipe = defRecipeRepository.findByRCPSEQ(rcpSEQ);
        return readRecipe;
    }

    public List<DefRecipe> SearchDefrecipe(SearchDefRecipeDTO searchDefRecipeDTO) {
        System.out.println(searchDefRecipeDTO.getRCPNM());
        return defRecipeRepository.findAllByRCPNMContaining(searchDefRecipeDTO.getRCPNM());
    }
    public void fetchDataAndPrint() {
        try {
            String apiURL = "http://openapi.foodsafetykorea.go.kr/api/" +
                    key + "/COOKRCP01/json/1/99";
            URL url = new URL(apiURL);

            // 한글 인코딩 추가
            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

            // JSON 데이터 구조에 따라 필요한 정보를 추출하여 출력합니다.
            JSONObject cookRcp01 = (JSONObject) jsonObject.get("COOKRCP01");
            JSONArray recipeArray = (JSONArray) cookRcp01.get("row");


            List<DefRecipe> recipeList = new ArrayList<>();
            for (Object obj : recipeArray) {
                JSONObject recipe = (JSONObject) obj;
                DefRecipe defRecipe = new DefRecipe();

                defRecipe.setRCPSEQ(recipe.get("RCP_SEQ").toString());
                defRecipe.setRCPNM(recipe.get("RCP_NM").toString());
                defRecipe.setRCP_WAY2(recipe.get("RCP_WAY2").toString());
                defRecipe.setRCP_PAT2(recipe.get("RCP_PAT2").toString());
                defRecipe.setINFO_WGT(recipe.get("INFO_WGT").toString());
                defRecipe.setINFO_ENG(recipe.get("INFO_ENG").toString());
                defRecipe.setINFO_CAR(recipe.get("INFO_CAR").toString());
                defRecipe.setINFO_PRO(recipe.get("INFO_PRO").toString());
                defRecipe.setINFO_FAT(recipe.get("INFO_FAT").toString());
                defRecipe.setINFO_NA(recipe.get("INFO_NA").toString());
                defRecipe.setHASH_TAG(recipe.get("HASH_TAG").toString());
                defRecipe.setATT_FILE_NO_MAIN(recipe.get("ATT_FILE_NO_MAIN").toString());
                defRecipe.setATT_FILE_NO_MK(recipe.get("ATT_FILE_NO_MK").toString());
                defRecipe.setRCP_PARTS_DTLS(recipe.get("RCP_PARTS_DTLS").toString());

                defRecipe.setMANUAL01(recipe.get("MANUAL01").toString());
                defRecipe.setMANUAL_IMG01(recipe.get("MANUAL_IMG01").toString());

                defRecipe.setMANUAL02(recipe.get("MANUAL02").toString());
                defRecipe.setMANUAL_IMG02(recipe.get("MANUAL_IMG02").toString());

                defRecipe.setMANUAL03(recipe.get("MANUAL03").toString());
                defRecipe.setMANUAL_IMG03(recipe.get("MANUAL_IMG03").toString());

                defRecipe.setMANUAL04(recipe.get("MANUAL04").toString());
                defRecipe.setMANUAL_IMG04(recipe.get("MANUAL_IMG04").toString());

                defRecipe.setMANUAL05(recipe.get("MANUAL05").toString());
                defRecipe.setMANUAL_IMG05(recipe.get("MANUAL_IMG05").toString());

                defRecipe.setMANUAL06(recipe.get("MANUAL06").toString());
                defRecipe.setMANUAL_IMG06(recipe.get("MANUAL_IMG06").toString());

                defRecipe.setMANUAL07(recipe.get("MANUAL07").toString());
                defRecipe.setMANUAL_IMG07(recipe.get("MANUAL_IMG07").toString());

                defRecipe.setMANUAL08(recipe.get("MANUAL08").toString());
                defRecipe.setMANUAL_IMG08(recipe.get("MANUAL_IMG08").toString());

                defRecipe.setMANUAL09(recipe.get("MANUAL09").toString());
                defRecipe.setMANUAL_IMG09(recipe.get("MANUAL_IMG09").toString());

                defRecipe.setMANUAL10(recipe.get("MANUAL10").toString());
                defRecipe.setMANUAL_IMG10(recipe.get("MANUAL_IMG10").toString());

                defRecipe.setMANUAL11(recipe.get("MANUAL11").toString());
                defRecipe.setMANUAL_IMG11(recipe.get("MANUAL_IMG11").toString());

                defRecipe.setMANUAL12(recipe.get("MANUAL12").toString());
                defRecipe.setMANUAL_IMG12(recipe.get("MANUAL_IMG12").toString());

                defRecipe.setMANUAL13(recipe.get("MANUAL13").toString());
                defRecipe.setMANUAL_IMG13(recipe.get("MANUAL_IMG13").toString());

                defRecipe.setMANUAL14(recipe.get("MANUAL14").toString());
                defRecipe.setMANUAL_IMG14(recipe.get("MANUAL_IMG14").toString());

                defRecipe.setMANUAL15(recipe.get("MANUAL15").toString());
                defRecipe.setMANUAL_IMG15(recipe.get("MANUAL_IMG15").toString());

                defRecipe.setMANUAL16(recipe.get("MANUAL16").toString());
                defRecipe.setMANUAL_IMG16(recipe.get("MANUAL_IMG16").toString());

                defRecipe.setMANUAL17(recipe.get("MANUAL17").toString());
                defRecipe.setMANUAL_IMG17(recipe.get("MANUAL_IMG17").toString());

                defRecipe.setMANUAL18(recipe.get("MANUAL18").toString());
                defRecipe.setMANUAL_IMG18(recipe.get("MANUAL_IMG18").toString());

                defRecipe.setMANUAL19(recipe.get("MANUAL19").toString());
                defRecipe.setMANUAL_IMG19(recipe.get("MANUAL_IMG19").toString());

                defRecipe.setMANUAL20(recipe.get("MANUAL20").toString());
                defRecipe.setMANUAL_IMG20(recipe.get("MANUAL_IMG20").toString());
                defRecipe.setRCP_NA_TIP(recipe.get("RCP_NA_TIP").toString());

                recipeList.add(defRecipe);
            }
            defRecipeRepository.saveAll(recipeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}