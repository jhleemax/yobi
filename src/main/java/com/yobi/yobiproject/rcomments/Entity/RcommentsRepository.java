package com.yobi.yobiproject.rcomments.Entity;

import com.yobi.yobiproject.recipe.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RcommentsRepository extends JpaRepository<Rcomments, Long> {
    List<Rcomments> findByRecipeNum(int rcpNum);
    Rcomments findByRcommentNum(int rComNum);
}
