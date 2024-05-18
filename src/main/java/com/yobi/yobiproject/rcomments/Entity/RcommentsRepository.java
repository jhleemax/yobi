package com.yobi.yobiproject.rcomments.Entity;

import com.yobi.yobiproject.recipe.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RcommentsRepository extends JpaRepository<Rcomments, Long> {
}
