package com.yobi.yobiproject.recipe.Entity;

import com.yobi.yobiproject.member.Entity.Member;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // 생성자 추가
    public Recipe(WriteRecipeDTO writeRecipeDTO) {
        this.userId = writeRecipeDTO.getUserId();
        this.recipeCategory = writeRecipeDTO.getRecipeCategory();
        this.foodName = writeRecipeDTO.getFoodName();
        this.ingredient = writeRecipeDTO.getIngredient();
        this.material = writeRecipeDTO.getMaterial();
        this.recipeManual01 = writeRecipeDTO.getRecipeManual01();
        this.recipeManual02 = writeRecipeDTO.getRecipeManual02();
        this.recipeManual03 = writeRecipeDTO.getRecipeManual03();
        this.recipeManual04 = writeRecipeDTO.getRecipeManual04();
        this.recipeManual05 = writeRecipeDTO.getRecipeManual05();
        this.recipeManual06 = writeRecipeDTO.getRecipeManual06();
        this.recipeManual07 = writeRecipeDTO.getRecipeManual07();
        this.recipeManual08 = writeRecipeDTO.getRecipeManual08();
        this.recipeManual09 = writeRecipeDTO.getRecipeManual09();
        this.recipeManual10 = writeRecipeDTO.getRecipeManual10();
        this.recipeManual11 = writeRecipeDTO.getRecipeManual11();
        this.recipeManual12 = writeRecipeDTO.getRecipeManual12();
        this.recipeManual13 = writeRecipeDTO.getRecipeManual13();
        this.recipeManual14 = writeRecipeDTO.getRecipeManual14();
        this.recipeManual15 = writeRecipeDTO.getRecipeManual15();
        this.recipeManual16 = writeRecipeDTO.getRecipeManual16();
        this.recipeManual17 = writeRecipeDTO.getRecipeManual17();
        this.recipeManual18 = writeRecipeDTO.getRecipeManual18();
        this.recipeManual19 = writeRecipeDTO.getRecipeManual19();
        this.recipeManual20 = writeRecipeDTO.getRecipeManual20();
    }

}
