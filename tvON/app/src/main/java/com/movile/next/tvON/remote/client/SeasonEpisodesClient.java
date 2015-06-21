package com.movile.next.tvON.remote.client;

import android.util.Log;

import com.movile.next.tvON.model.Episode;
import com.movile.next.tvON.callbacks.ISeasonEpisodes;
import com.movile.next.tvON.remote.service.EpisodeRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SeasonEpisodesClient {
    private RestAdapter mAdapter;
    private ISeasonEpisodes mCallback;

    public SeasonEpisodesClient(String endpoint, ISeasonEpisodes callback) {
        mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        mCallback = callback;
    }

    public void getSeasonEpisodes(String show, Long season){
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getSeasonEpisodes(show, season, new Callback<List<Episode>>() {
            @Override
            public void success(List<Episode> episodes, Response response) {
                mCallback.onEpisodesLoaded(episodes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TESTE", "Error fetching episode", error.getCause());
            }
        });
    }
}
