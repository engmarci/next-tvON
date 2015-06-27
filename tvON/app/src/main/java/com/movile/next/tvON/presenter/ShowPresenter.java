package com.movile.next.tvON.presenter;

import com.movile.next.tvON.callbacks.IShow;
import com.movile.next.tvON.model.Show;
import com.movile.next.tvON.remote.client.ShowClient;
import com.movile.next.tvON.view.IShowView;

public class ShowPresenter implements IShow {
    private IShowView mView;
    private String mUrl;

    public ShowPresenter(String url, IShowView presView){
        this.mView = presView;
        this.mUrl = url;
    }

    public void onShowLoaded(Show show) {
        mView.onShowLoad(show);
    }

    public void loadShowRetrofit(String show){
        ShowClient showSe = new ShowClient(this.mUrl, this);
        showSe.getShow(show);
    }
}
