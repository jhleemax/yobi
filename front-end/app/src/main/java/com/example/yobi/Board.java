package com.example.yobi;

public class Board {
    String
            thumbnail,
            title,
            profileImg,
            userid,
            likeCount,
            recipeNum;

    public Board(String thumbnail, String title, String profileImg, String userid, String likeCount, String recipeNum) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.profileImg = profileImg;
        this.userid = userid;
        this.likeCount = likeCount;
        this.recipeNum = recipeNum;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getRecipeNum() {
        return recipeNum;
    }

    public void setRecipeNum(String recipeNum) {
        this.recipeNum = recipeNum;
    }
}
