package com.movile.next.tvON.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.next.tvON.asynctask.IEpisodeDetails;
import com.movile.next.tvON.model.Episode;

public class EpisodeDetailsLoaderCallback implements LoaderManager.LoaderCallbacks<Episode> {
    private Context cont;
    private IEpisodeDetails act;

    public EpisodeDetailsLoaderCallback(Context cont, IEpisodeDetails act){
        this.cont =  cont;
        this.act = act;
    }

    public Loader<Episode> onCreateLoader(int id, Bundle bundle) {

        return new EpisodeDetailsLoaderAsyncTask(this.cont);
    }
    public void onLoadFinished(Loader<Episode> loader, Episode result) {
        this.act.onEpisodeLoad(result);
    }

    public void onLoaderReset(Loader<Episode> loader) {

    }
}
