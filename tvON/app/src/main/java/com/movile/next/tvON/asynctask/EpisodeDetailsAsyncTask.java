package com.movile.next.tvON.asynctask;


import android.content.Context;
import android.os.AsyncTask;

import com.movile.next.tvON.business.FetchLocalEpisodeDetails;
import com.movile.next.tvON.model.Episode;

public class EpisodeDetailsAsyncTask extends AsyncTask<Void, Void, Episode> {

    private Context cont;
    private IEpisodeDetails act;

    public EpisodeDetailsAsyncTask(Context cont, IEpisodeDetails act){

        this.cont =  cont;
        this.act = act;
    }

    protected Episode doInBackground(Void... params) {

        FetchLocalEpisodeDetails fetchData = new FetchLocalEpisodeDetails();
        return fetchData.get(this.cont);
    }


    protected void onPostExecute(Episode result) {
        this.act.onEpisodeLoad(result);
    }

}
