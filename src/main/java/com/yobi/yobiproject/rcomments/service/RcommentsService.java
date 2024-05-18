package com.yobi.yobiproject.rcomments.service;

import com.yobi.yobiproject.exception.CustomErrorCode;
import com.yobi.yobiproject.exception.CustomException;
import com.yobi.yobiproject.member.Entity.MemberRepository;
import com.yobi.yobiproject.rcomments.Entity.Rcomments;
import com.yobi.yobiproject.rcomments.Entity.RcommentsRepository;
import com.yobi.yobiproject.rcomments.dto.RcommentsDTO;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import com.yobi.yobiproject.recipe.Entity.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RcommentsService {
    private final RcommentsRepository rcommentsRepository;
    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;

    public HttpStatus save(RcommentsDTO rcommentsDTO) {
        if(recipeRepository.findByRecipeNum(rcommentsDTO.getRecipeNum()) == null) { // 레시피가 존재하지 않을 때
            throw new CustomException(CustomErrorCode.RECIPE_NOT_FOUND);
        }
        else { // 레시피가 존재할 때
            if(memberRepository.findByUserId(rcommentsDTO.getUserId()) == null) { // 작성자가 회원이 아닐 때
                throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
            }
            else { // 작성자가 회원일 때
                if(rcommentsDTO.getRcommentContent().length() > 100) {
                    throw new CustomException(CustomErrorCode.COMMENT_LONG_REQUEST);
                }
                else {
                    Rcomments rcomments = Rcomments.toRcomments(rcommentsDTO);
                    rcommentsRepository.save(rcomments);
                    return HttpStatus.OK;
                }
            }
        }
    }
}

