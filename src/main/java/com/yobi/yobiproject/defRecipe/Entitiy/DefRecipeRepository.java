package com.yobi.yobiproject.defRecipe.Entitiy;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Nonnull
public interface DefRecipeRepository extends JpaRepository<DefRecipe, Long> {

    List<DefRecipe> findAll(); // 모든 api레시피 조회
}
