package com.movile.next.tvON.business;

        import android.content.Context;
        import android.util.Log;

        import com.movile.next.tvON.R;
        import com.movile.next.tvON.model.Episode;
        import com.movile.next.tvON.model.converter.ModelConverter;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.text.MessageFormat;

public class FetchHttpEpisodeDetails {
    public Episode get(Context context) {
        Episode episode = null;

        InputStreamReader reader = null;

        String url = context.getResources().getString(R.string.api_url_base);
        url = url + context.getResources().getString(R.string.api_url_episode);

        //url = MessageFormat.format(url, "game-of-thrones", 4, 8);
        url = MessageFormat.format(url, "ncis", 8, 10);

        int READ_TIMEOUT = context.getResources().getInteger(R.integer.api_timeout_read);
        int CONNECTION_TIMEOUT = context.getResources().getInteger(R.integer.api_timeout_connect);
        String API_VERSION = context.getResources().getString(R.string.api_version);
        String API_KEY = context.getResources().getString(R.string.api_key);

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("trakt-api-version", API_VERSION);
            connection.setRequestProperty("trakt-api-key", API_KEY);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e(url, "Error loading remote content", e);
        } finally {
// Release InputStreamReader if used
        }

        return episode;
    }
}

