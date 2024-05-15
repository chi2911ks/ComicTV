package com.cb.comictv.api;

import android.os.AsyncTask;

import com.cb.comictv.interfaces.GetComic;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetDataComic extends AsyncTask<Void, Void, Void>{
    String data;
    GetComic getComic;

    public GetDataComic(GetComic getComic) {
        this.getComic = getComic;
        getComic.startGetComic();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        data = null;
        try {
            data = RequestsHandle.GetData("https://otruyenapi.com/v1/api/home");
        } catch (IOException e) {

            this.getComic.errorGetComic();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void unused) {
        if (data == null){
            getComic.errorGetComic();

        }
        else{
            getComic.stopGetComic(data);
        }
    }
}
