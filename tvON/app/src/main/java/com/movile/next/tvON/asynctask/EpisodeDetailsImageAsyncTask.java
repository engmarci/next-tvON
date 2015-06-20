package com.movile.next.tvON.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

public class EpisodeDetailsImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private IEpisodeDetailsImage act;

    public EpisodeDetailsImageAsyncTask(IEpisodeDetailsImage act){
        this.act =  act;

    }

    protected Bitmap doInBackground(String... params) {
        String url = params[0];

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
        } catch (IOException e) {
            Log.e("", "Error fetching image from " + url, e);
        }

        return bitmap;
    }

    protected void onPostExecute(Bitmap bitmap) {
        this.act.onEpisodeLoadImage(bitmap);
    }

}
