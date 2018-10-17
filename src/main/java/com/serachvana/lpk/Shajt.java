package com.serachvana.lpk;

import lombok.Data;

public class Shajt {
    private String chapter;
    private String title;
    private Integer number;
    private String text;

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Shajt{" +
                "chapter='" + chapter + '\'' +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", text='" + text + '\'' +
                '}';
    }
}
