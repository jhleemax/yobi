package com.yobi.yobiproject.bookmark.service;

import com.yobi.yobiproject.bookmark.Entity.Bookmark;
import com.yobi.yobiproject.bookmark.Entity.BookmarkRepository;
import com.yobi.yobiproject.bookmark.dto.BookmarkDTO;
import com.yobi.yobiproject.member.Entity.MemberRepository;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import com.yobi.yobiproject.recipe.Entity.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;

    public HttpStatus save(BookmarkDTO bookmarkDTO) {
        Bookmark bookmark = Bookmark.toBookmark(bookmarkDTO);
        bookmarkRepository.save(bookmark);
        return HttpStatus.OK;
    }
    @Transactional(readOnly = true)
    public List<Recipe> list(String userId) { // 유저의 북마크 목록 가져오기
        List<Bookmark> bookmarks = bookmarkRepository.findByMemberUserId(userId);
                List<Integer> recipes = bookmarks.stream()
                .map(bookmark -> bookmark.getRecipe().getRecipeNum())
                .collect(Collectors.toList());
        List<Recipe> recipeList = recipeRepository.findAllByRecipeNumIn(recipes);
        return recipeList;
    }


}
