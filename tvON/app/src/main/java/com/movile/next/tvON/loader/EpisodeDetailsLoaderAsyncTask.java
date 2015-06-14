package com.movile.next.tvON.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.next.tvON.business.FetchHttpEpisodeDetails;
import com.movile.next.tvON.model.Episode;

public class EpisodeDetailsLoaderAsyncTask extends AsyncTaskLoader {
    private Context cont;

    public EpisodeDetailsLoaderAsyncTask(Context cont){
        super(cont);

        this.cont = cont;
    }

    @Override
    public Episode loadInBackground() {
        FetchHttpEpisodeDetails fetchData = new FetchHttpEpisodeDetails();
        return fetchData.get(this.cont);
    }
}
