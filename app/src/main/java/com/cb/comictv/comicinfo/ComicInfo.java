package com.cb.comictv.comicinfo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cb.comictv.R;
import com.cb.comictv.adapter.chapter.ChapterAdapter;
import com.cb.comictv.adapter.chapter.ChapterModel;
import com.cb.comictv.api.GetInfoComic;

import com.cb.comictv.interfaces.GetInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;


public class ComicInfo extends AppCompatActivity implements GetInfo {
    TextView expandCollapse, comic_content;
    TextView title_comic, other_name, name_author, category_comic, status;
    ImageView imageComic;
    RecyclerView recyclerViewChapters;
    ArrayList<ChapterModel> chapterModels;
    ProgressBar progressInfo;
    LinearLayout infoComic;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_info);
        progressInfo = findViewById(R.id.progress_info);
        infoComic = findViewById(R.id.info_comic);
        expandCollapse = findViewById(R.id.expandCollapse);
        comic_content = findViewById(R.id.comic_content);
        title_comic = findViewById(R.id.title_comic);
        other_name = findViewById(R.id.other_name);
        imageComic = findViewById(R.id.image_comic);
        name_author = findViewById(R.id.name_author);
        category_comic = findViewById(R.id.category_comic);
        status = findViewById(R.id.status);
        chapterModels = new ArrayList<>();
        recyclerViewChapters = findViewById(R.id.recycler_chapter);
        expandCollapse.setOnClickListener(v -> {
            if (expandCollapse.getText().toString().equals("< Thu gọn")) {
                comic_content.setMaxLines(2);
                expandCollapse.setText("Xem thêm >");
            } else {
                comic_content.setMaxLines(Integer.MAX_VALUE);
                expandCollapse.setText("< Thu gọn");
            }
        });
        String linkComic = Objects.requireNonNull(getIntent().getSerializableExtra("linkComic")).toString();
        recyclerViewChapters.setLayoutManager(new LinearLayoutManager(this));
        new GetInfoComic(this, linkComic).execute();
    }

    @Override
    public void startGetInfo() {
        progressInfo.setVisibility(View.VISIBLE);
        infoComic.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void stopGetInfo(String body) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        JsonObject data = jsonObject.get("data").getAsJsonObject();
        String titleHead = data.get("seoOnPage").getAsJsonObject().get("titleHead").getAsString();
        String image = data.get("seoOnPage").getAsJsonObject().get("seoSchema").getAsJsonObject().get("image").getAsString();
        String descriptionHead = data.get("seoOnPage").getAsJsonObject().get("descriptionHead").getAsString();

        JsonObject item = data.get("item").getAsJsonObject();
        String nameComic = item.get("name").getAsString();
        String author = item.get("author").getAsJsonArray().get(0).getAsString();
        String statusComic = item.get("status").getAsString();
        JsonArray category = item.get("category").getAsJsonArray();
        StringBuilder category_str = new StringBuilder();
        for (int i = 0; i < category.size(); i++) {
            category_str.append(category.get(i).getAsJsonObject().get("name").getAsString());
            if (i < category.size() - 1) {
                category_str.append(" - ");
            }
        }
        JsonArray chapters = item.get("chapters").getAsJsonArray().get(0).getAsJsonObject().get("server_data").getAsJsonArray();
        for (int i = chapters.size()-1; i >= 0; i--) {
            JsonObject chapter = chapters.get(i).getAsJsonObject();
            chapterModels.add(new ChapterModel(chapter.get("chapter_name").getAsString(), chapter.get("chapter_title").getAsString(), chapter.get("chapter_api_data").getAsString()));
        }
        title_comic.setText(nameComic);
        other_name.setText("Tên khác: "+titleHead);
        name_author.setText("Tác giả: "+author);
        category_comic.setText("Thể loại: "+category_str);
        comic_content.setText(descriptionHead);
        status.setText("Tình trạng: "+statusComic);
        Glide.with(getApplicationContext()).load(image).into(imageComic);
        recyclerViewChapters.setAdapter(new ChapterAdapter(getApplicationContext(), chapterModels));
        progressInfo.setVisibility(View.INVISIBLE);
        infoComic.setVisibility(View.VISIBLE);
    }

    @Override
    public void errorGetInfo() {

    }
}