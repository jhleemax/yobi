package com.yobi.yobiproject.defRecipe.controller;

import com.yobi.yobiproject.defRecipe.Entitiy.DefRecipe;
import com.yobi.yobiproject.defRecipe.Entitiy.DefRecipeRepository;
import com.yobi.yobiproject.defRecipe.service.DefRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class DefRecipeController {
    private final DefRecipeService defRecipeService;

    @PostMapping(value = "/api/insert")
    public ResponseEntity<?> insert(){
        defRecipeService.fetchDataAndPrint();
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/api/defrecipe")
    public ResponseEntity<?> defrecipe(){
        List<DefRecipe> Send_Data = defRecipeService.SendDefRecipe();
        return ResponseEntity.ok().body(Send_Data);
    }


//    @GetMapping(value = "/api/{RCP_SEQ}")
//    public ResponseEntity<?> getRecipe(@PathVariable("RCP_SEQ") String userId){
//        ResponseMemberDTO member = memberService.getMemberById(userId);
//        return ResponseEntity.ok().body(member);
//    }
}
