package com.cb.comictv.api;
import android.os.AsyncTask;

import com.cb.comictv.interfaces.GetInfo;

import java.io.IOException;

public class GetInfoComic extends AsyncTask<Void, Void, Void> {
    GetInfo getInfo;
    String linkComic;
    String data;

    public GetInfoComic(GetInfo getInfo, String linkComic) {
        this.getInfo = getInfo;
        this.linkComic = linkComic;
        getInfo.startGetInfo();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        data = null;
        try {
            data = RequestsHandle.GetData(linkComic);
        } catch (IOException e) {

            this.getInfo.errorGetInfo();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (data == null){
            getInfo.errorGetInfo();

        }
        else{
            getInfo.stopGetInfo(data);
        }
    }
}
