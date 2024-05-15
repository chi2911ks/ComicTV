package com.cb.comictv.comicinfo;

import com.cb.comictv.adapter.chapter.ChapterModel;

import java.util.ArrayList;

public class InfoComicModel {
    String nameComic;
    String author;
    String descriptionHead;
    String og_image;
    String category;
    ArrayList<ChapterModel> chapterModels;

    public InfoComicModel(String nameComic, String author, String descriptionHead, String og_image, String category, ArrayList<ChapterModel> chapterModels) {
        this.nameComic = nameComic;
        this.author = author;
        this.descriptionHead = descriptionHead;
        this.og_image = og_image;
        this.category = category;
        this.chapterModels = chapterModels;
    }

    public String getNameComic() {
        return nameComic;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescriptionHead() {
        return descriptionHead;
    }

    public String getOg_image() {
        return og_image;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<ChapterModel> getChapterModels() {
        return chapterModels;
    }
}
