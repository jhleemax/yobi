package com.yobi.yobiproject.recipe.service;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import com.yobi.yobiproject.recipe.Entity.RecipeRepository;
import com.yobi.yobiproject.recipe.dto.UpdateRecipeDTO;
import com.yobi.yobiproject.recipe.dto.WriteRecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public void save(WriteRecipeDTO writeRecipeDTO) {
        Recipe recipe = Recipe.toRecipe(writeRecipeDTO);
        recipeRepository.save(recipe);
    }

    public void Update(UpdateRecipeDTO updateRecipeDTO, int rcpNum) {
        Recipe recipe = recipeRepository.findByRecipeNum(rcpNum);
        recipe.setRecipeCategory(updateRecipeDTO.getRecipeCategory());
        recipe.setMaterial(updateRecipeDTO.getMaterial());
        recipe.setIngredient(updateRecipeDTO.getIngredient());
        recipe.setFoodName(updateRecipeDTO.getFoodName());

        recipe.setRecipeManual01(updateRecipeDTO.getRecipeManual01());
        recipe.setRecipeImage01(updateRecipeDTO.getRecipeImage01());

        recipe.setRecipeManual02(updateRecipeDTO.getRecipeManual02());
        recipe.setRecipeImage02(updateRecipeDTO.getRecipeImage02());

        recipe.setRecipeManual03(updateRecipeDTO.getRecipeManual03());
        recipe.setRecipeImage03(updateRecipeDTO.getRecipeImage03());

        recipe.setRecipeManual04(updateRecipeDTO.getRecipeManual04());
        recipe.setRecipeImage04(updateRecipeDTO.getRecipeImage04());

        recipe.setRecipeManual05(updateRecipeDTO.getRecipeManual05());
        recipe.setRecipeImage05(updateRecipeDTO.getRecipeImage05());

        recipe.setRecipeManual06(updateRecipeDTO.getRecipeManual06());
        recipe.setRecipeImage06(updateRecipeDTO.getRecipeImage06());

        recipe.setRecipeManual07(updateRecipeDTO.getRecipeManual07());
        recipe.setRecipeImage07(updateRecipeDTO.getRecipeImage07());

        recipe.setRecipeManual08(updateRecipeDTO.getRecipeManual08());
        recipe.setRecipeImage08(updateRecipeDTO.getRecipeImage08());

        recipe.setRecipeManual09(updateRecipeDTO.getRecipeManual09());
        recipe.setRecipeImage09(updateRecipeDTO.getRecipeImage09());

        recipe.setRecipeManual10(updateRecipeDTO.getRecipeManual10());
        recipe.setRecipeImage10(updateRecipeDTO.getRecipeImage10());

        recipe.setRecipeManual11(updateRecipeDTO.getRecipeManual11());
        recipe.setRecipeImage11(updateRecipeDTO.getRecipeImage11());

        recipe.setRecipeManual12(updateRecipeDTO.getRecipeManual12());
        recipe.setRecipeImage12(updateRecipeDTO.getRecipeImage12());

        recipe.setRecipeManual13(updateRecipeDTO.getRecipeManual13());
        recipe.setRecipeImage13(updateRecipeDTO.getRecipeImage13());

        recipe.setRecipeManual14(updateRecipeDTO.getRecipeManual14());
        recipe.setRecipeImage14(updateRecipeDTO.getRecipeImage14());

        recipe.setRecipeManual15(updateRecipeDTO.getRecipeManual15());
        recipe.setRecipeImage15(updateRecipeDTO.getRecipeImage15());

        recipe.setRecipeManual16(updateRecipeDTO.getRecipeManual16());
        recipe.setRecipeImage16(updateRecipeDTO.getRecipeImage16());

        recipe.setRecipeManual17(updateRecipeDTO.getRecipeManual17());
        recipe.setRecipeImage17(updateRecipeDTO.getRecipeImage17());

        recipe.setRecipeManual18(updateRecipeDTO.getRecipeManual18());
        recipe.setRecipeImage18(updateRecipeDTO.getRecipeImage18());

        recipe.setRecipeManual19(updateRecipeDTO.getRecipeManual19());
        recipe.setRecipeImage19(updateRecipeDTO.getRecipeImage19());

        recipe.setRecipeManual20(updateRecipeDTO.getRecipeManual20());
        recipe.setRecipeImage20(updateRecipeDTO.getRecipeImage20());
        recipeRepository.save(recipe);
    }

    public List<Recipe> SendRecipe() { // 모든 사용자 레시피
        List<Recipe> recipe = recipeRepository.findAll();
        return recipe;
    }

    public Recipe ReadRecipe(int rcpnum) { // 사용자 레시피 상세보기
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        return recipe;
    }

    public HttpStatus LikeRecipe(int rcpnum) { // 사용자 레시피 좋아요 추가
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        recipe.setRecipeNice(recipe.getRecipeNice() + 1);
        recipeRepository.save(recipe);
        return HttpStatus.OK;
    }

    public void UnLikeRecipe(int rcpnum) { // 사용자 레시피 좋아요 취소
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        recipe.setRecipeNice(recipe.getRecipeNice() - 1);
        recipeRepository.save(recipe);
    }

    public void DeleteRecipe(int rcpnum) { // 사용자 레시피 삭제
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        recipeRepository.delete(recipe);
    }

}
