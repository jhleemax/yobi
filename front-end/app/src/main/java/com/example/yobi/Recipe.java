package com.example.yobi;

public class Recipe {
    String thumbnail;
    String
        title;
    String genre;
    String amount;
    String time;
    String difficulty;
    String ingredient;

    public Recipe() {};
    public Recipe(String thumbnail, String title, String genre, String ingredient) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.genre = genre;
//        this.amount = amouunt;
//        this.time = time;
//        this.difficulty = difficulty;
        this.ingredient = ingredient;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
