package com.cb.comictv.adapter.comic;

public class ComicModel {
    String nameComic;
    String chapter;
    String linkImage;
    String linkComicInfo;

    public ComicModel(String nameComic, String chapter, String linkImage, String linkComicInfo) {
        this.nameComic = nameComic;
        this.chapter = chapter;
        this.linkImage = linkImage;
        this.linkComicInfo = linkComicInfo;
    }

    public String getLinkComicInfo() {
        return linkComicInfo;
    }



    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}

