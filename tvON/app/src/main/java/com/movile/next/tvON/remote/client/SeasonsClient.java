package com.movile.next.tvON.remote.client;

import android.util.Log;

import com.movile.next.tvON.callbacks.ISeasons;
import com.movile.next.tvON.model.Season;
import com.movile.next.tvON.remote.service.EpisodeRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SeasonsClient {
    private RestAdapter mAdapter;
    private ISeasons mCallback;

    public SeasonsClient(String endpoint, ISeasons callback) {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mCallback = callback;
    }

    public void getSeasons(String show){
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.geSeasons(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                mCallback.onSeasonsLoaded(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TESTE", "Error fetching seasons", error.getCause());
            }
        });
    }
}
