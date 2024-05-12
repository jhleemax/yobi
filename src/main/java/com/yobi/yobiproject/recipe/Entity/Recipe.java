package com.yobi.yobiproject.recipe.Entity;

import com.yobi.yobiproject.defRecipe.Entitiy.DefRecipe;
import com.yobi.yobiproject.recipe.dto.WriteRecipeDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe {
    @Id
    @Column(name = "recipe_num")
    private int recipeNum;

    @Column(name = "userid")
    private String userId;

    @Column(name = "recipe_category")
    private String recipeCategory;

    @Column(name = "recipe_nice")
    private int recipeNice;

    @Column(name = "foodname")
    private String foodName;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "material")
    private String material;

    @Column(name = "recipe_report")
    private int  recipeReport;

    @Column(name = "recipe_manual01")
    private String recipeManual01;

    @Column(name = "recipe_image01")
    private String recipeImage01;

    @Column(name = "recipe_manual02")
    private String recipeManual02;

    @Column(name = "recipe_image02")
    private String recipeImage02;

    @Column(name = "recipe_manual03")
    private String recipeManual03;

    @Column(name = "recipe_image03")
    private String recipeImage03;

    @Column(name = "recipe_manual04")
    private String recipeManual04;

    @Column(name = "recipe_image04")
    private String recipeImage04;

    @Column(name = "recipe_manual05")
    private String recipeManual05;

    @Column(name = "recipe_image05")
    private String recipeImage05;

    @Column(name = "recipe_manual06")
    private String recipeManual06;

    @Column(name = "recipe_image06")
    private String recipeImage06;

    @Column(name = "recipe_manual07")
    private String recipeManual07;

    @Column(name = "recipe_image07")
    private String recipeImage07;

    @Column(name = "recipe_manual08")
    private String recipeManual08;

    @Column(name = "recipe_image08")
    private String recipeImage08;

    @Column(name = "recipe_manual09")
    private String recipeManual09;

    @Column(name = "recipe_image09")
    private String recipeImage09;

    @Column(name = "recipe_manual10")
    private String recipeManual10;

    @Column(name = "recipe_image10")
    private String recipeImage10;

    @Column(name = "recipe_manual11")
    private String recipeManual11;

    @Column(name = "recipe_image11")
    private String recipeImage11;

    @Column(name = "recipe_manual12")
    private String recipeManual12;

    @Column(name = "recipe_image12")
    private String recipeImage12;

    @Column(name = "recipe_manual13")
    private String recipeManual13;

    @Column(name = "recipe_image13")
    private String recipeImage13;

    @Column(name = "recipe_manual14")
    private String recipeManual14;

    @Column(name = "recipe_image14")
    private String recipeImage14;

    @Column(name = "recipe_manual15")
    private String recipeManual15;

    @Column(name = "recipe_image15")
    private String recipeImage15;

    @Column(name = "recipe_manual16")
    private String recipeManual16;

    @Column(name = "recipe_image16")
    private String recipeImage16;

    @Column(name = "recipe_manual17")
    private String recipeManual17;

    @Column(name = "recipe_image17")
    private String recipeImage17;

    @Column(name = "recipe_manual18")
    private String recipeManual18;

    @Column(name = "recipe_image18")
    private String recipeImage18;

    @Column(name = "recipe_manual19")
    private String recipeManual19;

    @Column(name = "recipe_image19")
    private String recipeImage19;

    @Column(name = "recipe_manual20")
    private String recipeManual20;

    @Column(name = "recipe_image20")
    private String recipeImage20;

    @Builder
    public static Recipe toRecipe(WriteRecipeDTO writeRecipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setUserId(writeRecipeDTO.getUserId());
        recipe.setRecipeCategory(writeRecipeDTO.getRecipeCategory());
        recipe.setFoodName(writeRecipeDTO.getFoodName());
        recipe.setIngredient(writeRecipeDTO.getIngredient());
        recipe.setMaterial(writeRecipeDTO.getMaterial());

        recipe.setRecipeManual01(writeRecipeDTO.getRecipeManual01());
        recipe.setRecipeImage01(writeRecipeDTO.getRecipeImage01());

        recipe.setRecipeManual02(writeRecipeDTO.getRecipeManual02());
        recipe.setRecipeImage02(writeRecipeDTO.getRecipeImage02());

        recipe.setRecipeManual03(writeRecipeDTO.getRecipeManual03());
        recipe.setRecipeImage03(writeRecipeDTO.getRecipeImage03());

        recipe.setRecipeManual04(writeRecipeDTO.getRecipeManual04());
        recipe.setRecipeImage04(writeRecipeDTO.getRecipeImage04());

        recipe.setRecipeManual05(writeRecipeDTO.getRecipeManual05());
        recipe.setRecipeImage05(writeRecipeDTO.getRecipeImage05());

        recipe.setRecipeManual06(writeRecipeDTO.getRecipeManual06());
        recipe.setRecipeImage06(writeRecipeDTO.getRecipeImage06());

        recipe.setRecipeManual07(writeRecipeDTO.getRecipeManual07());
        recipe.setRecipeImage07(writeRecipeDTO.getRecipeImage07());

        recipe.setRecipeManual08(writeRecipeDTO.getRecipeManual08());
        recipe.setRecipeImage08(writeRecipeDTO.getRecipeImage08());

        recipe.setRecipeManual09(writeRecipeDTO.getRecipeManual09());
        recipe.setRecipeImage09(writeRecipeDTO.getRecipeImage09());

        recipe.setRecipeManual10(writeRecipeDTO.getRecipeManual10());
        recipe.setRecipeImage10(writeRecipeDTO.getRecipeImage10());

        recipe.setRecipeManual11(writeRecipeDTO.getRecipeManual11());
        recipe.setRecipeImage11(writeRecipeDTO.getRecipeImage11());

        recipe.setRecipeManual12(writeRecipeDTO.getRecipeManual12());
        recipe.setRecipeImage12(writeRecipeDTO.getRecipeImage12());

        recipe.setRecipeManual13(writeRecipeDTO.getRecipeManual13());
        recipe.setRecipeImage13(writeRecipeDTO.getRecipeImage13());

        recipe.setRecipeManual14(writeRecipeDTO.getRecipeManual14());
        recipe.setRecipeImage14(writeRecipeDTO.getRecipeImage14());

        recipe.setRecipeManual15(writeRecipeDTO.getRecipeManual15());
        recipe.setRecipeImage15(writeRecipeDTO.getRecipeImage15());

        recipe.setRecipeManual16(writeRecipeDTO.getRecipeManual16());
        recipe.setRecipeImage16(writeRecipeDTO.getRecipeImage16());

        recipe.setRecipeManual17(writeRecipeDTO.getRecipeManual17());
        recipe.setRecipeImage17(writeRecipeDTO.getRecipeImage17());

        recipe.setRecipeManual18(writeRecipeDTO.getRecipeManual18());
        recipe.setRecipeImage18(writeRecipeDTO.getRecipeImage18());

        recipe.setRecipeManual19(writeRecipeDTO.getRecipeManual19());
        recipe.setRecipeImage19(writeRecipeDTO.getRecipeImage19());

        recipe.setRecipeManual20(writeRecipeDTO.getRecipeManual20());
        recipe.setRecipeImage20(writeRecipeDTO.getRecipeImage20());
        return recipe;
    }

}
