package com.yobi.yobiproject.rcomments.service;

import com.yobi.yobiproject.exception.CustomErrorCode;
import com.yobi.yobiproject.exception.CustomException;
import com.yobi.yobiproject.member.Entity.MemberRepository;
import com.yobi.yobiproject.rcomments.Entity.Rcomments;
import com.yobi.yobiproject.rcomments.Entity.RcommentsRepository;
import com.yobi.yobiproject.rcomments.dto.DeleteRcommentsDTO;
import com.yobi.yobiproject.rcomments.dto.RcommentsDTO;
import com.yobi.yobiproject.rcomments.dto.UpdateRcommentsDTO;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import com.yobi.yobiproject.recipe.Entity.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
                else if(rcommentsDTO.getRcommentContent().isEmpty()) {
                    throw new CustomException(CustomErrorCode.COMMENT_NOT_CONTENT);
                }
                else {
                    Rcomments rcomments = Rcomments.toRcomments(rcommentsDTO);
                    rcommentsRepository.save(rcomments);
                    return HttpStatus.OK;
                }
            }
        }
    }

    public List<Rcomments> read(int rcpNum) {
        if(recipeRepository.findByRecipeNum(rcpNum) == null) {
            throw new CustomException(CustomErrorCode.RECIPE_NOT_FOUND);
        }
        else {
            return rcommentsRepository.findByRecipeNum(rcpNum);
        }
    }

    public HttpStatus update(int rComNum, UpdateRcommentsDTO updateRcommentsDTO) {
        if(memberRepository.findByUserId(updateRcommentsDTO.getUserId()) == null) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }
        else {
            if(updateRcommentsDTO.getRcommentContent().length() > 100) {
                throw new CustomException(CustomErrorCode.COMMENT_LONG_REQUEST);
            }
            else if(updateRcommentsDTO.getRcommentContent().isEmpty()) {
                throw new CustomException(CustomErrorCode.COMMENT_NOT_CONTENT);
            }
            else {
                Rcomments rcomments = rcommentsRepository.findByRcommentNum(rComNum);
                rcomments.setRcommentContent(updateRcommentsDTO.getRcommentContent());
                rcommentsRepository.save(rcomments);
                return HttpStatus.OK;
            }
        }
    }

    public HttpStatus delete(int rComNum, DeleteRcommentsDTO deleteRcommentsDTO) {
        Rcomments rcomments = rcommentsRepository.findByRcommentNum(rComNum);
        if(rcomments.getUserId().equals(deleteRcommentsDTO.getUserId())) {
            rcommentsRepository.delete(rcomments);
            return HttpStatus.OK;
        }
        else {
            throw new CustomException(CustomErrorCode.USER_NOT_IDEQUAL);
        }
    }
}

