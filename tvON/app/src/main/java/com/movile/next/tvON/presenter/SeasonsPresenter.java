package com.movile.next.tvON.presenter;

import com.movile.next.tvON.callbacks.ISeasons;
import com.movile.next.tvON.model.Season;
import com.movile.next.tvON.remote.client.SeasonsClient;
import com.movile.next.tvON.view.ISeasonsView;

import java.util.List;

public class SeasonsPresenter implements ISeasons {
    private ISeasonsView mView;
    private String mUrl;

    public SeasonsPresenter(String url, ISeasonsView presView){
        this.mView = presView;
        this.mUrl = url;
    }

    public void onSeasonsLoaded(List<Season> seasons) {
        mView.onSeasonsLoad(seasons);
    }

    public void loadSeasonsRetrofit(String show){
        SeasonsClient showSes = new SeasonsClient(this.mUrl, this);
        showSes.getSeasons(show);
    }
}
