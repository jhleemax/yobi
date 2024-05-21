package com.yobi.yobiproject.bcomments.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BcommentsRepository extends JpaRepository<Bcomments, Long> {
    List<Bcomments> findAll();
    List<Bcomments> findByBoardNum(int boardNum);
    Bcomments findByBcommentNum(int bcommentNum);
}
