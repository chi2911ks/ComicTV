package com.cb.comictv.adapter.comic;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cb.comictv.R;
import com.cb.comictv.comicinfo.ComicInfo;

import java.util.ArrayList;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {

    Context context;
    ArrayList<ComicModel> comicData;

    public ComicAdapter(Context context, ArrayList<ComicModel> comicData) {
        this.context = context;
        this.comicData = comicData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comic_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ComicModel comicModel = comicData.get(position);
        holder.nameComic.setText(comicModel.getNameComic());
        holder.chapter.setText("Chapter "+comicModel.getChapter());
        Glide.with(this.context).load(comicModel.getLinkImage()).into(holder.imageComic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ComicInfo.class);
                intent.putExtra("linkComic", comicModel.getLinkComicInfo());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageComic;
        TextView chapter, nameComic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageComic = itemView.findViewById(R.id.image_truyen);
            chapter = itemView.findViewById(R.id.chapter_truyen);
            nameComic = itemView.findViewById(R.id.title_truyen);
        }

    }
}
