package com.movile.next.tvON.presenter;

import com.movile.next.tvON.model.Episode;
import com.movile.next.tvON.callbacks.ISeasonEpisodes;
import com.movile.next.tvON.remote.client.SeasonEpisodesClient;
import com.movile.next.tvON.view.ISeasonEpisodesView;

import java.util.List;

public class SeasonEpisodesPresenter implements ISeasonEpisodes {
    private ISeasonEpisodesView mView;
    private String mUrl;

    public SeasonEpisodesPresenter(String url, ISeasonEpisodesView presView){
        this.mView = presView;
        this.mUrl = url;
    }

    public void onEpisodesLoaded(List<Episode> episodes) {
        mView.onEpisodesLoad(episodes);
    }

    public void loadSeasonEpisodesRetrofit(String show, Long season){
        SeasonEpisodesClient seasonCl = new SeasonEpisodesClient(this.mUrl, this);
        seasonCl.getSeasonEpisodes(show, season);
    }
}
