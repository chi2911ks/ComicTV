package com.cb.comictv.api;

import android.os.AsyncTask;

import com.cb.comictv.interfaces.GetPage;

import java.io.IOException;

public class GetPageComic extends AsyncTask<Void, Void, Void> {
    GetPage getPage;
    String data;
    String linkComic;

    public GetPageComic(GetPage getPage, String linkComic) {
        this.getPage = getPage;
        this.linkComic = linkComic;
        getPage.startGetPage();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        data = null;
        try {
            data = RequestsHandle.GetData(linkComic);
        } catch (IOException e) {

            this.getPage.errorGetPage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (data == null){
            getPage.errorGetPage();

        }
        else{
            getPage.stopGetPage(data);
        }
    }
}
