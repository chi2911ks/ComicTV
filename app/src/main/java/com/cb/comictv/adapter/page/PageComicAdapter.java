package com.cb.comictv.adapter.page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cb.comictv.R;

import java.util.ArrayList;

public class PageComicAdapter extends RecyclerView.Adapter<PageComicAdapter.ViewHolder>{
    ArrayList<String> listLinks;
    Context context;

    public PageComicAdapter(ArrayList<String> listLinks, Context context) {
        this.listLinks = listLinks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.page_comic_item, parent, false);
        return new PageComicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String linkImage = listLinks.get(position);
        Glide.with(this.context).load(linkImage).into(holder.imagePage);

    }




    @Override
    public int getItemCount() {
        return listLinks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagePage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePage = itemView.findViewById(R.id.image_page);
        }
    }
}
