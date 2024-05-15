package com.cb.comictv.adapter.chapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.ArrayList;

public class SpinnerChapter extends ArrayAdapter<ChapterModel> {

    private final ArrayList<ChapterModel> chapterModels;
    private final LayoutInflater mInflater;

    public SpinnerChapter(Context context, ArrayList<ChapterModel> chapterModels) {
        super(context, android.R.layout.simple_dropdown_item_1line, chapterModels);

        this.chapterModels = chapterModels;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @SuppressLint("SetTextI18n")
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = mInflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        TextView label = row.findViewById(android.R.id.text1);
        label.setText("Chapter "+chapterModels.get(position).getNameChapter());
        return row;
    }

}
