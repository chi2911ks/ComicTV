package com.cb.comictv.pagecomic;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.cb.comictv.R;
import com.cb.comictv.adapter.chapter.ChapterModel;
import com.cb.comictv.adapter.chapter.SpinnerChapter;
import com.cb.comictv.adapter.page.PageComicAdapter;
import com.cb.comictv.api.GetPageComic;
import com.cb.comictv.api.RequestsHandle;
import com.cb.comictv.interfaces.GetPage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


import java.io.IOException;

public class PageComic extends AppCompatActivity implements GetPage {
    ArrayList<ChapterModel> chapterModels;
    ArrayList<String> listLinks;
    Spinner spinnerChapters;
    RecyclerView recyclerPageComic;
    ImageView buttonPreviousComic;
    ImageView buttonNextComic;
    AlertDialog.Builder builder;
    ProgressBar progressLoad;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_comic);
        builder = new AlertDialog.Builder(this);
        listLinks = new ArrayList<>();
        progressLoad = findViewById(R.id.progress_load_comic);
        buttonNextComic = findViewById(R.id.next_comic);
        buttonPreviousComic = findViewById(R.id.previous_comic);
        spinnerChapters = findViewById(R.id.spinner_chapters);
        recyclerPageComic = findViewById(R.id.recycler_page_comic);
        recyclerPageComic.setLayoutManager(new LinearLayoutManager(this));
        String link_api = getIntent().getSerializableExtra("link_chapter_comic").toString();
        chapterModels = MyComic.chapterModels;
        SpinnerChapter spinnerAdapter = new SpinnerChapter(getApplicationContext(), chapterModels);
        spinnerChapters.setAdapter(spinnerAdapter);
        spinnerChapters.setSelection(MyComic.currentIndex);
        new GetPageComic(this, link_api).execute();

        spinnerChapters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (MyComic.currentIndex != position){
                    MyComic.currentIndex = position;
                    new GetPageComic(PageComic.this, chapterModels.get(position).getLinkApi()).execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        buttonNextComic.setOnClickListener(v -> nextComic());
        buttonPreviousComic.setOnClickListener(v -> previousComic());
    }
    //next là - và previous là + vì sử dụng đảo ngược chapter lớn nhất lên trên
    private void nextComic(){
        if (MyComic.currentIndex == 0) {

            builder.setTitle("Thông báo").setMessage("Đã là chương mới nhất hiện tại!").setCancelable(true).show();
            return;
        }
        startGetPage();
        MyComic.currentIndex -= 1;
        spinnerChapters.setSelection(MyComic.currentIndex);
        new GetPageComic(PageComic.this, chapterModels.get(MyComic.currentIndex).getLinkApi()).execute();
    }
    private void previousComic(){
        if (MyComic.currentIndex == chapterModels.size() - 1) {

            builder.setTitle("Thông báo").setMessage("Chương thứ nhất rồi còn bấm cl à!").setCancelable(true).show();
            return;
        }
        startGetPage();
        MyComic.currentIndex += 1;
        spinnerChapters.setSelection(MyComic.currentIndex);
        new GetPageComic(PageComic.this, chapterModels.get(MyComic.currentIndex).getLinkApi()).execute();
    }
    @Override
    public void startGetPage() {
        progressLoad.setVisibility(View.VISIBLE);
        recyclerPageComic.setVisibility(View.INVISIBLE);
    }

    @Override
    public void stopGetPage(String body) {
        progressLoad.setVisibility(View.INVISIBLE);
        recyclerPageComic.setVisibility(View.VISIBLE);
        listLinks.clear();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        JsonObject data = jsonObject.get("data").getAsJsonObject();
        JsonObject item = data.get("item").getAsJsonObject();
        String domain_cdn = data.get("domain_cdn").getAsString();
        String path = item.get("chapter_path").getAsString();
        JsonArray chapter_image = item.get("chapter_image").getAsJsonArray();
        for(JsonElement element: chapter_image){
            JsonObject d = element.getAsJsonObject();
            String link = domain_cdn+"/"+path+'/'+d.get("image_file").getAsString();
            Log.d("LinkVip", link);
            listLinks.add(link);
        }
        recyclerPageComic.setAdapter(new PageComicAdapter(listLinks, getApplicationContext()));

    }

    @Override
    public void errorGetPage() {

    }


}