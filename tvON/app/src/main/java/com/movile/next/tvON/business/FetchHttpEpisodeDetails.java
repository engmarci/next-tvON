package com.movile.next.tvON.business;

import android.content.Context;

import com.movile.next.tvON.model.Episode;

public class FetchHttpEpisodeDetails {
    public Episode get(Context context) {
        Episode episode = null;
/*
        InputStreamReader reader = null;
        try {
            HttpURLConnection connection = configureConnection(context, url);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error loading remote content", e);
        } finally {
// Release InputStreamReader if used
        }
    */


        return episode;
    }
}
