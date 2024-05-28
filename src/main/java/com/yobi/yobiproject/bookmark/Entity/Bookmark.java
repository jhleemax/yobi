package com.yobi.yobiproject.bookmark.Entity;

import com.yobi.yobiproject.bookmark.dto.BookmarkDTO;
import com.yobi.yobiproject.member.Entity.Member;
import com.yobi.yobiproject.recipe.Entity.Recipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//ORM
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookmark")
public class Bookmark {
    @Id // unique + notnull , primarykey는 unsinged(int인경우)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 오토인크리먼트(MYSQL) -> DB마다 다름
    @Column(name = "BOOKMARK_NUM")
    private int bookmarkNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_num", referencedColumnName = "recipe_num")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private Member member;

    public static Bookmark toBookmark(BookmarkDTO bookmarkDTO) {
        Bookmark bookmark = new Bookmark();
        bookmark.recipe.setRecipeNum(bookmarkDTO.getRecipeNum());
        bookmark.member.setUserId(bookmarkDTO.getUserId());
        return bookmark;
    }
}
