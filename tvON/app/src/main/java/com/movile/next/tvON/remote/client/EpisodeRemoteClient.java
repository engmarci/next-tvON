package com.movile.next.tvON.remote.client;

import android.util.Log;

import com.movile.next.tvON.asynctask.IEpisodeDetails;
import com.movile.next.tvON.model.Episode;
import com.movile.next.tvON.remote.service.EpisodeRemoteService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EpisodeRemoteClient {
    private RestAdapter mAdapter;
    private IEpisodeDetails mCallback;

    public EpisodeRemoteClient(String endpoint, IEpisodeDetails callback) {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mCallback = callback;
    }

    public void getEpisodeDetails(String show, Long season, Long episode){
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mCallback.onEpisodeLoad(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", "Error fetching episode", error.getCause());
            }
        });
    }
}
