package com.example.yobi;

public class UserRecipeOrder {
    String contents;

    String num;

    public UserRecipeOrder(String contents, String num) {
        this.contents = contents;
        this.num = num;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
