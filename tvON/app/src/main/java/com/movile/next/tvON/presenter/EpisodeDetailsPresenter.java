package com.movile.next.tvON.presenter;

import com.movile.next.tvON.asynctask.IEpisodeDetails;
import com.movile.next.tvON.model.Episode;
import com.movile.next.tvON.remote.client.EpisodeRemoteClient;
import com.movile.next.tvON.view.IEpisodeDetailsView;

public class EpisodeDetailsPresenter implements IEpisodeDetails {
    private IEpisodeDetailsView mView;
    private String mUrl;

    public EpisodeDetailsPresenter(String url, IEpisodeDetailsView presView){
        this.mView = presView;
        this.mUrl = url;
    }

    public void onEpisodeLoad(Episode episode) {
        mView.onEpisodeLoad(episode);
    }

    public void loadEpisodeDetailsAsyncTask(){
        //EpisodeDetailsAsyncTask epTask = new EpisodeDetailsAsyncTask(this , this);
        //epTask.execute();
    }

    public void loadEpisodeDetailsRemote(){
        //getLoaderManager().initLoader(0, null, new EpisodeDetailsLoaderCallback(this, this)).forceLoad();

    }

    public void loadEpisodeDetailsRetrofit(String show, Long season, Long episode){
        EpisodeRemoteClient epCl = new EpisodeRemoteClient(this.mUrl, this);
        epCl.getEpisodeDetails(show, season, episode);
    }
}
