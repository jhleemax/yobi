package com.yobi.yobiproject.bookmark.Entity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark,Long>{
    @EntityGraph(attributePaths = {"recipe"},type = EntityGraph.EntityGraphType.LOAD)
    List<Bookmark> findByMemberUserId(String userId);

}
