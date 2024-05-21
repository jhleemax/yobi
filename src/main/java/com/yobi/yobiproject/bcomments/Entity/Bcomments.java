package com.yobi.yobiproject.bcomments.Entity;

import com.yobi.yobiproject.bcomments.dto.BcommentsDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bcomments")
public class Bcomments {
    @Id
    @Column(name = "bcomment_num")
    private int bcommentNum;

    @Column(name = "board_num")
    private int boardNum;

    @Column(name = "userid")
    private String userId;

    @Column(name = "bcomment_content")
    private String bcommentContent;

    @CreationTimestamp
    @Column(name = "bcomment_date")
    private LocalDateTime bcommentDate;

    @Column(name = "bcomment_nice")
    private int bcommentNice;

    @Column(name = "bcomment_report")
    private int bcommentReport;

    public static Bcomments toBcomments(BcommentsDTO bcommentsDTO) {
        Bcomments bcomments = new Bcomments();
        bcomments.setBoardNum(bcommentsDTO.getBoardNum());
        bcomments.setUserId(bcommentsDTO.getUserId());
        bcomments.setBcommentContent(bcommentsDTO.getBcommentContent());
        return bcomments;
    }
}
