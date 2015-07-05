package com.movile.next.tvON.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.next.tvON.asynctask.IDatabaseLoad;
import com.movile.next.tvON.model.Favorite;

public class DatabaseLoaderCallback implements LoaderManager.LoaderCallbacks<Favorite>{
    private Context cont;
    private IDatabaseLoad act;

    public DatabaseLoaderCallback(Context cont, IDatabaseLoad act){
        this.cont =  cont;
        this.act = act;
    }

    public Loader<Favorite> onCreateLoader(int id, Bundle bundle) {

        return new EpisodeDetailsLoaderAsyncTask(this.cont);
    }
    public void onLoadFinished(Loader<Favorite> loader, Favorite result) {
        this.act.onDatabaseLoad(result);
    }

    public void onLoaderReset(Loader<Favorite> loader) {

    }
}
