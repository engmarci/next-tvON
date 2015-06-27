package com.movile.next.tvON.presenter;

import com.movile.next.tvON.callbacks.IShowList;
import com.movile.next.tvON.model.Show;
import com.movile.next.tvON.remote.client.ShowListClient;
import com.movile.next.tvON.view.IShowListView;

import java.util.List;

public class ShowListPresenter implements IShowList {
    private IShowListView mView;
    private String mUrl;

    public ShowListPresenter(String url, IShowListView presView){
        this.mView = presView;
        this.mUrl = url;
    }

    public void onShowListLoaded(List<Show> showList) {
        mView.onShowListLoad(showList);
    }

    public void loadShowListRetrofit(){
        ShowListClient showList = new ShowListClient(this.mUrl, this);
        showList.getShowList();
    }
}
