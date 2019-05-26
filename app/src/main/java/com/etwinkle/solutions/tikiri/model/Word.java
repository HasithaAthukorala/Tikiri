package com.etwinkle.solutions.tikiri.model;

public class Word {
    private int id;
    private int level;
    private String word;
    private String list;
    private String image;

    public Word(int id, int level, String word, String list, String image) {
        this.id = id;
        this.level = level;
        this.word = word;
        this.list = list;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
