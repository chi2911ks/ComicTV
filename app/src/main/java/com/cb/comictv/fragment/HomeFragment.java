package com.cb.comictv.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cb.comictv.R;
import com.cb.comictv.adapter.comic.ComicAdapter;
import com.cb.comictv.adapter.comic.ComicModel;
import com.cb.comictv.api.GetDataComic;
import com.cb.comictv.interfaces.GetComic;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import io.supercharge.shimmerlayout.ShimmerLayout;


public class HomeFragment extends Fragment implements GetComic {

    RecyclerView recyclerView;
    ProgressBar progressComic;
    ArrayList<ComicModel> comicModels = new ArrayList<>();
    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();
        progressComic = view.findViewById(R.id.progress_comic);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        new GetDataComic(this).execute();
        return view;
    }
    @Override
    public void startGetComic() {
        progressComic.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void errorGetComic() {
        Toast.makeText(context, "Lỗi không thể lấy truyện!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void stopGetComic(String data) {

        comicModels.clear();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
        JsonArray items = jsonObject.getAsJsonObject("data").getAsJsonArray("items");
        for(JsonElement element: items){
            JsonObject d = element.getAsJsonObject();
            String name = d.get("name").getAsString();
            String linkImage = "https://otruyenapi.com/uploads/comics/"+d.get("thumb_url").getAsString();
            String chapter = d.getAsJsonArray("chaptersLatest").get(0).getAsJsonObject().get("chapter_name").getAsString();
            String linkComic = "https://otruyenapi.com/v1/api/truyen-tranh/"+d.get("slug").getAsString();
            comicModels.add(new ComicModel(name, chapter, linkImage, linkComic));

        }
        recyclerView.setAdapter(new ComicAdapter(context, comicModels));
        progressComic.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

    }
}