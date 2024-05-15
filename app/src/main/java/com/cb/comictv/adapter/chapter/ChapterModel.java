package com.cb.comictv.adapter.chapter;


import java.io.Serializable;

public class ChapterModel implements Serializable {
    String nameChapter;
    String contentChapter;
    String linkApi;

    public ChapterModel(String nameChapter, String contentChapter, String linkApi) {
        this.nameChapter = nameChapter;
        this.contentChapter = contentChapter;
        this.linkApi = linkApi;
    }

    public void setNameChapter(String nameChapter) {
        this.nameChapter = nameChapter;
    }

    public void setContentChapter(String contentChapter) {
        this.contentChapter = contentChapter;
    }

    public void setLinkApi(String linkApi) {
        this.linkApi = linkApi;
    }

    public String getNameChapter() {
        return nameChapter;
    }

    public String getContentChapter() {
        return contentChapter;
    }

    public String getLinkApi() {
        return linkApi;
    }

}