package com.yobi.yobiproject.rcomments.Entity;

import com.yobi.yobiproject.rcomments.dto.RcommentsDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rcomments")
public class Rcomments {
    @Id
    @Column(name="rcomment_num")
    private int rcommentNum;

    @Column(name="recipe_num")
    private int recipeNum;

    @Column(name = "userid")
    private String userId;

    @CreationTimestamp
    @Column(name = "rcomment_date")
    private LocalDateTime rcommentDate;

    @Column(name = "rcomment_nice")
    private int rcommentNice;

    @Column(name = "rcomment_content")
    private String rcommentContent;

    @Column(name = "rcomment_report")
    private int rcommentReport;

    @Builder
    public static Rcomments toRcomments(RcommentsDTO rcommentsDTO) {
        Rcomments rcomments = new Rcomments();
        rcomments.setRcommentContent(rcommentsDTO.getRcommentContent());
        rcomments.setUserId(rcommentsDTO.getUserId());
        rcomments.setRecipeNum(rcommentsDTO.getRecipeNum());
        return rcomments;
    }

}
