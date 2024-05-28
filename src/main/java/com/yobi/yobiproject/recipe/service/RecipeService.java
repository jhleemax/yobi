package com.yobi.yobiproject.recipe.service;
import com.yobi.yobiproject.bookmark.Entity.Bookmark;
import com.yobi.yobiproject.bookmark.Entity.BookmarkRepository;
import com.yobi.yobiproject.defRecipe.Entitiy.DefRecipe;
import com.yobi.yobiproject.defRecipe.Entitiy.DefRecipeRepository;
import com.yobi.yobiproject.defRecipe.dto.SearchDefRecipeDTO;
import com.yobi.yobiproject.defRecipe.service.DefRecipeService;
import com.yobi.yobiproject.exception.CustomErrorCode;
import com.yobi.yobiproject.exception.CustomException;
import com.yobi.yobiproject.member.Entity.MemberRepository;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import com.yobi.yobiproject.recipe.Entity.RecipeRepository;
import com.yobi.yobiproject.recipe.dto.DeleteRecipeDTO;
import com.yobi.yobiproject.recipe.dto.SearchRecipeDTO;
import com.yobi.yobiproject.recipe.dto.UpdateRecipeDTO;
import com.yobi.yobiproject.recipe.dto.WriteRecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final DefRecipeService defRecipeService;
    private final MemberRepository memberRepository;
    private final DefRecipeRepository defRecipeRepository;
    private final BookmarkRepository bookmarkRepository;
    public HttpStatus save(WriteRecipeDTO writeRecipeDTO) {
        if(memberRepository.findByUserId(writeRecipeDTO.getUserId()) == null) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }
        else {
            Recipe recipe = Recipe.toRecipe(writeRecipeDTO);
            recipeRepository.save(recipe);
            return HttpStatus.OK;
        }
    }

    public HttpStatus Update(UpdateRecipeDTO updateRecipeDTO, int rcpNum) {
        Recipe recipe = recipeRepository.findByRecipeNum(rcpNum);
        if(updateRecipeDTO.getUserId().equals(recipe.getUserId())) {
            recipe.setRecipeCategory(updateRecipeDTO.getRecipeCategory());
            recipe.setMaterial(updateRecipeDTO.getMaterial());
            recipe.setIngredient(updateRecipeDTO.getIngredient());
            recipe.setFoodName(updateRecipeDTO.getFoodName());
            recipe.setUserId(updateRecipeDTO.getUserId());

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
            return HttpStatus.OK;
        }
        else {
            throw new CustomException(CustomErrorCode.USER_NOT_IDEQUAL);
        }
    }

    public List<Recipe> SendRecipe() { // 모든 사용자 레시피
        List<Recipe> recipe = recipeRepository.findAll();
        return recipe;
    }

    public Recipe ReadRecipe(int rcpnum) { // 사용자 레시피 상세보기
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        return recipe;
    }

    public HttpStatus LikeRecipe(int rcpnum) { // 사용자 레시피 좋아요 추가, 수정해야해서 사용 비추천
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        recipe.setRecipeNice(recipe.getRecipeNice() + 1);
        recipeRepository.save(recipe);
        return HttpStatus.OK;
    }

    public void UnLikeRecipe(int rcpnum) { // 사용자 레시피 좋아요 취소, 수정해야해서 사용 비추천
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        recipe.setRecipeNice(recipe.getRecipeNice() - 1);
        recipeRepository.save(recipe);
    }

    public HttpStatus Delete(DeleteRecipeDTO deleteRecipeDTO) {
        Recipe recipe = recipeRepository.findByRecipeNum(deleteRecipeDTO.getRecipeNum());
        if(recipe != null) {
            if(recipe.getUserId().equals(deleteRecipeDTO.getUserId())) {
                recipeRepository.delete(recipe);
                return HttpStatus.OK;
            }
            else {
                throw new CustomException(CustomErrorCode.USER_NOT_IDEQUAL);
            }
        }
        else {
            throw new CustomException(CustomErrorCode.RECIPE_NOT_FOUND);
        }
    }

    public List<Recipe> UserRecipe(String userid) { // 사용자 레시피 조회
        List<Recipe> userRecipe = recipeRepository.findByUserId(userid);
        if (userRecipe.isEmpty()) {
            throw new CustomException(CustomErrorCode.RECIPE_NOT_FOUND);
        }
        else {
            return userRecipe;
        }
    }

    public List<Object> SearchRecipe(SearchRecipeDTO searchRecipeDTO) { // 레시피 통합 조회
        List<Object> searchData = new ArrayList<>();
        List<DefRecipe> ApiData = defRecipeRepository.findAllByRCPNMContaining(searchRecipeDTO.getRCPNM());
        List<Recipe> RecipeData = recipeRepository.findAllByFoodNameContaining(searchRecipeDTO.getFoodName());
        searchData.addAll(ApiData);
        searchData.addAll(RecipeData);
        if(searchData.isEmpty()) {
            throw new CustomException(CustomErrorCode.RECIPE_NOT_FOUND);
        }
        else {
            return searchData;
        }
    }



}
