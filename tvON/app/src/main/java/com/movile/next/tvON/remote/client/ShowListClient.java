package com.movile.next.tvON.remote.client;

import android.util.Log;

import com.movile.next.tvON.callbacks.IShowList;
import com.movile.next.tvON.model.Show;
import com.movile.next.tvON.remote.service.EpisodeRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShowListClient {
    private RestAdapter mAdapter;
    private IShowList mCallback;

    public ShowListClient(String endpoint, IShowList callback) {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mCallback = callback;
    }

    public void getShowList(){
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getShowList(new Callback<List<Show>>() {
            @Override
            public void success(List<Show> showList, Response response) {
                mCallback.onShowListLoaded(showList);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TESTE", "Error fetching show list", error.getCause());
            }
        });
    }
}
