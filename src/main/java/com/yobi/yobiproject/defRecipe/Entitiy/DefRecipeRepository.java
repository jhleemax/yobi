package com.yobi.yobiproject.defRecipe.Entitiy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefRecipeRepository extends JpaRepository<DefRecipe, Long> {

    List<DefRecipe> findAll(); // 모든 api레시피 조회
    List<DefRecipe> findAllByRCPNMContaining(String rcpNM);
}
