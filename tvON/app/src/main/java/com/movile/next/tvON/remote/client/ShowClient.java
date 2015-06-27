package com.movile.next.tvON.remote.client;

import android.util.Log;

import com.movile.next.tvON.callbacks.IShow;
import com.movile.next.tvON.model.Show;
import com.movile.next.tvON.remote.service.EpisodeRemoteService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShowClient {
    private RestAdapter mAdapter;
    private IShow mCallback;

    public ShowClient(String endpoint, IShow callback) {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mCallback = callback;
    }

    public void getShow(String show){
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getShow(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {
                mCallback.onShowLoaded(show);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TESTE", "Error fetching show", error.getCause());
            }
        });
    }
}
