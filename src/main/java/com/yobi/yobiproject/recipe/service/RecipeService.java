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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final DefRecipeService defRecipeService;
    private final MemberRepository memberRepository;
    private final DefRecipeRepository defRecipeRepository;
    private final BookmarkRepository bookmarkRepository;
    private final String uploadBaseDir = "C:\\Yobi"; // 기본 업로드 디렉토리

    public void save(WriteRecipeDTO writeRecipeDTO, List<MultipartFile> recipeImages) throws IOException {
        // 새로운 Recipe 엔티티 생성 및 저장
        Recipe recipe = new Recipe(writeRecipeDTO);
        Recipe savedRecipe = recipeRepository.save(recipe);

        // 사용자 폴더 경로 생성
        Path userDir = Paths.get(uploadBaseDir, writeRecipeDTO.getUserId());

        // 사용자가 작성한 레시피의 개수를 가져옴
        long recipeCount = recipeRepository.countByUserId(writeRecipeDTO.getUserId());

        // 새로운 레시피 폴더 경로 생성
        Path recipeDir = userDir.resolve(String.valueOf(recipeCount + 1));

        // 폴더가 존재하지 않으면 생성
        if (!Files.exists(recipeDir)) {
            Files.createDirectories(recipeDir);
        }

        // 이미지 저장 및 경로 설정
        for (int i = 0; i < recipeImages.size(); i++) { // 이미지 개수
            MultipartFile file = recipeImages.get(i); // 객체 반환
            if (!file.isEmpty()) {
                // 고유한 파일 이름 생성
                String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename()); // 파일 확장자 추출
                String uniqueFileName = UUID.randomUUID().toString() + (fileExtension != null ? "." + fileExtension : ""); // 고유 파일 이름 생성
                Path filePath = recipeDir.resolve((i + 1) + "_" + uniqueFileName); // recipeDir경로로 저장
                Files.createDirectories(filePath.getParent()); // 파일 경로의 부모 디렉토리가 존재하지 않는다면 디렉토리 생성
                file.transferTo(filePath.toFile()); // 이미지를 지정된 경로 "filePath"로 저장

                // 레시피 엔티티에 이미지 경로 설정
                switch (i) {
                    case 0:
                        savedRecipe.setRecipeImage01(filePath.toString());
                        break;
                    case 1:
                        savedRecipe.setRecipeImage02(filePath.toString());
                        break;
                    case 2:
                        savedRecipe.setRecipeImage03(filePath.toString());
                        break;
                    case 3:
                        savedRecipe.setRecipeImage04(filePath.toString());
                        break;
                    case 4:
                        savedRecipe.setRecipeImage05(filePath.toString());
                        break;
                    case 5:
                        savedRecipe.setRecipeImage06(filePath.toString());
                        break;
                    case 6:
                        savedRecipe.setRecipeImage07(filePath.toString());
                        break;
                    case 7:
                        savedRecipe.setRecipeImage08(filePath.toString());
                        break;
                    case 8:
                        savedRecipe.setRecipeImage09(filePath.toString());
                        break;
                    case 9:
                        savedRecipe.setRecipeImage10(filePath.toString());
                        break;
                    case 10:
                        savedRecipe.setRecipeImage11(filePath.toString());
                        break;
                    case 11:
                        savedRecipe.setRecipeImage12(filePath.toString());
                        break;
                    case 12:
                        savedRecipe.setRecipeImage13(filePath.toString());
                        break;
                    case 13:
                        savedRecipe.setRecipeImage14(filePath.toString());
                        break;
                    case 14:
                        savedRecipe.setRecipeImage15(filePath.toString());
                        break;
                    case 15:
                        savedRecipe.setRecipeImage16(filePath.toString());
                        break;
                    case 16:
                        savedRecipe.setRecipeImage17(filePath.toString());
                        break;
                    case 17:
                        savedRecipe.setRecipeImage18(filePath.toString());
                        break;
                    case 18:
                        savedRecipe.setRecipeImage19(filePath.toString());
                        break;
                    case 19:
                        savedRecipe.setRecipeImage20(filePath.toString());
                        break;
                    default:
                        break;
                }
            }
        }

        // 레시피 엔티티 업데이트
        recipeRepository.save(savedRecipe);
    }

    public Recipe ReadRecipe(int rcpnum) {
        Recipe recipe = recipeRepository.findByRecipeNum(rcpnum);
        if (recipe == null) {
            throw new CustomException(CustomErrorCode.RECIPE_NOT_FOUND);
        }

        // 파일 경로를 URL로 변환
        recipe.setRecipeImage01(convertPathToUrl(recipe.getRecipeImage01()));
        recipe.setRecipeImage02(convertPathToUrl(recipe.getRecipeImage02()));
        recipe.setRecipeImage03(convertPathToUrl(recipe.getRecipeImage03()));
        recipe.setRecipeImage04(convertPathToUrl(recipe.getRecipeImage04()));
        recipe.setRecipeImage05(convertPathToUrl(recipe.getRecipeImage05()));
        recipe.setRecipeImage06(convertPathToUrl(recipe.getRecipeImage06()));
        recipe.setRecipeImage07(convertPathToUrl(recipe.getRecipeImage07()));
        recipe.setRecipeImage08(convertPathToUrl(recipe.getRecipeImage08()));
        recipe.setRecipeImage09(convertPathToUrl(recipe.getRecipeImage09()));
        recipe.setRecipeImage10(convertPathToUrl(recipe.getRecipeImage10()));
        recipe.setRecipeImage11(convertPathToUrl(recipe.getRecipeImage11()));
        recipe.setRecipeImage12(convertPathToUrl(recipe.getRecipeImage12()));
        recipe.setRecipeImage13(convertPathToUrl(recipe.getRecipeImage13()));
        recipe.setRecipeImage14(convertPathToUrl(recipe.getRecipeImage14()));
        recipe.setRecipeImage15(convertPathToUrl(recipe.getRecipeImage15()));
        recipe.setRecipeImage16(convertPathToUrl(recipe.getRecipeImage16()));
        recipe.setRecipeImage17(convertPathToUrl(recipe.getRecipeImage17()));
        recipe.setRecipeImage18(convertPathToUrl(recipe.getRecipeImage18()));
        recipe.setRecipeImage19(convertPathToUrl(recipe.getRecipeImage19()));
        recipe.setRecipeImage20(convertPathToUrl(recipe.getRecipeImage20()));

        return recipe;
    }

    private String convertPathToUrl(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return null;
        }
        String baseUrl = "http://localhost:8080/images/";
        String relativePath = filePath.replace("C:\\Yobi\\", "").replace("\\", "/");
        return baseUrl + relativePath;
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
