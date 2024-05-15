package com.cb.comictv.adapter.chapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cb.comictv.R;
import com.cb.comictv.pagecomic.MyComic;
import com.cb.comictv.pagecomic.PageComic;

import java.util.ArrayList;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder>{
    Context context;
    ArrayList<ChapterModel> chapterModels;

    public ChapterAdapter(Context context, ArrayList<ChapterModel> chapterModels) {
        this.context = context;
        this.chapterModels = chapterModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chapter_item, parent, false);
        return new ChapterAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ChapterModel chapterModel = chapterModels.get(position);

        holder.nameChapter.setText("Chapter "+chapterModel.getNameChapter());
        holder.contentChapter.setText(chapterModel.getContentChapter());
        holder.itemView.setOnClickListener(v -> {
            MyComic.currentIndex = position;
            MyComic.chapterModels = chapterModels;
            Intent intent = new Intent(context, PageComic.class);
            intent.putExtra("link_chapter_comic", chapterModel.getLinkApi());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chapterModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameChapter;
        TextView contentChapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameChapter = itemView.findViewById(R.id.name_chapter);
            contentChapter = itemView.findViewById(R.id.content_chapter);
        }
    }
}
