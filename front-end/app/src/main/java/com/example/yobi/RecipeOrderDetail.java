package com.example.yobi;

import java.io.Serializable;

public class RecipeOrderDetail implements Serializable {
    String
        img,
        mainDescription;

    public RecipeOrderDetail(String img, String mainDescription) {
        this.img = img;
        this.mainDescription = mainDescription;
    }

    public String getImg() {
        return img;
    }

    public String getMainDescription() {
        return mainDescription;
    }
}
