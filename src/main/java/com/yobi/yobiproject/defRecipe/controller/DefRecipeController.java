package com.yobi.yobiproject.defRecipe.controller;

import com.yobi.yobiproject.defRecipe.Entitiy.DefRecipe;
import com.yobi.yobiproject.defRecipe.dto.SearchDefRecipeDTO;
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

    @GetMapping(value = "/api/list")
    public ResponseEntity<?> list() {
        List<DefRecipe> Send_Data = defRecipeService.SendDefrecipe();
        return ResponseEntity.ok().body(Send_Data);
    }
    @GetMapping(value = "/api/search")
    public ResponseEntity<?> search(@RequestBody SearchDefRecipeDTO searchDefRecipeDTO) {
        List<DefRecipe> Search_Data = defRecipeService.SearchDefrecipe(searchDefRecipeDTO);
        return ResponseEntity.ok().body(Search_Data);
    }

    @GetMapping(value = "/api/read/{RCP_SEQ}")
    public ResponseEntity<?> read(@PathVariable("RCP_SEQ") String rcp_SEQ) {
        List<DefRecipe> Read_Data = defRecipeService.ReadDefrecipe(rcp_SEQ);
        return ResponseEntity.ok().body(Read_Data);
    }
}
